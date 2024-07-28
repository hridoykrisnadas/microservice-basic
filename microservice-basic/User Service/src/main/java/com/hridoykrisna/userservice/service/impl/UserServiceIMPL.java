package com.hridoykrisna.userservice.service.impl;

import com.hridoykrisna.userservice.dto.BookDTO;
import com.hridoykrisna.userservice.dto.Response;
import com.hridoykrisna.userservice.dto.UserDTO;
import com.hridoykrisna.userservice.model.User;
import com.hridoykrisna.userservice.repository.UserRepository;
import com.hridoykrisna.userservice.service.UserService;
import com.hridoykrisna.userservice.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    @Override
    public Response insertUserInfo(UserDTO userDTO) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        if (user != null)
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "User Created Successfully", user);
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response updateUserInfo(long userId, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByIdAndIsActiveTrue(userId);
        if (userOptional.isEmpty())
            return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "User Not Found");
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        User user = modelMapper.map(userDTO, User.class);
        user.setUserId(userId);
        user = userRepository.save(user);
        if (user != null)
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "User Update Successfully", user);
        return ResponseBuilder.getFailureMessage(HttpStatus.BAD_REQUEST, "Bad Request");
    }

    @Override
    public Response getAllUserInfo() {
        List<User> userList = userRepository.findAllAndIsActiveTrue();
        List<UserDTO> userDTOList = getUserDTOList(userList);
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Data Fetched Successfully,", userDTOList);
    }

    @Override
    public Response getUserInfo(long userId) {
        Optional<User> userOptional = userRepository.findByIdAndIsActiveTrue(userId);
        if (userOptional.isEmpty())
            return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "User Not Found");
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        UserDTO userDTO= modelMapper.map(userOptional.get(), UserDTO.class);

        Response response = this.restTemplate.getForObject("http://Book-Service/book/get-book-by-user/"+userId, Response.class);
        assert response != null;
        List<BookDTO> bookDTOList = (List<BookDTO>) response.getContent();
        userDTO.setBookDTOS(bookDTOList);
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "User info Fetched", userDTO);
    }

    @Override
    public Response deleteUser(long userId) {
        Optional<User> userOptional = userRepository.findByIdAndIsActiveTrue(userId);
        if (userOptional.isEmpty())
            return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "User Not Found");
        User user = userOptional.get();
        user.setActive(false);
        userRepository.save(user);
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "User Delete Successfully");
    }

    private List<UserDTO> getUserDTOList(List<User> productList) {
        List<UserDTO> userDTOList = new ArrayList<>();
        productList.forEach(product -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            UserDTO userDTO = modelMapper.map(product, UserDTO.class);
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }
}

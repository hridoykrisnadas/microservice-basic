package com.hridoykrisna.userservice.service;

import com.hridoykrisna.userservice.dto.Response;
import com.hridoykrisna.userservice.dto.UserDTO;
import jakarta.validation.Valid;

public interface UserService {
    public Response insertUserInfo(UserDTO userDTO);

    Response updateUserInfo(long userId, UserDTO userDTO);

    Response getAllUserInfo();

    Response getUserInfo(long userId);

    Response deleteUser(long userId);
}

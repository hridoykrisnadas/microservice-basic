package com.hridoykrisna.userservice.controller;

import com.hridoykrisna.userservice.dto.Response;
import com.hridoykrisna.userservice.dto.UserDTO;
import com.hridoykrisna.userservice.service.UserService;
import com.hridoykrisna.userservice.util.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/insert-user")
    public Response insertUserInfo(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBuilder.getFailureMessage(bindingResult, "Field Value Error");
        }
        return userService.insertUserInfo(userDTO);
    }
    @PostMapping("/update-user/{userId}")
    public Response updateUserInfo(@Valid @PathVariable("userId") long userId, @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBuilder.getFailureMessage(bindingResult, "Field Value Error");
        }
        return userService.updateUserInfo(userId, userDTO);
    }

    @GetMapping("/get-all-user")
    public Response getAllUserInfo(){
        return userService.getAllUserInfo();
    }
    @GetMapping("/get-user/{userId}")
    public Response getUserInfo(@PathVariable("userId") long userId){
        return userService.getUserInfo(userId);
    }

    @DeleteMapping("/delete-user/{userId}")
    public Response deleteUserId(@PathVariable("userId") long userId){
        return userService.deleteUser(userId);
    }
}

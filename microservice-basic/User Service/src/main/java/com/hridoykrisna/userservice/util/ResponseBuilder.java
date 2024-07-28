package com.hridoykrisna.userservice.util;

import com.hridoykrisna.userservice.dto.ErrorResponseDto;
import com.hridoykrisna.userservice.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseBuilder {
    private ResponseBuilder() {
    }

    private static List<ErrorResponseDto> getCustomError(BindingResult bindingResult) {
        List<ErrorResponseDto> errorResponseDtoList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().
                    field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            errorResponseDtoList.add(errorResponseDto);
        });
        return errorResponseDtoList;
    }

    public static Response getFailureMessage(BindingResult result, String message) {
        return Response.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorResponseDTO(getCustomError(result))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getFailureMessage(HttpStatus status, String message) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message, Object content) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message, Object content, int numberOfElement) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .numberOfElement(numberOfElement)
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message, Object content, int numberOfElement, int rowCount) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .numberOfElement(numberOfElement)
                .rowCount(rowCount)
                .timestamp(new Date().getTime())
                .build();
    }
}

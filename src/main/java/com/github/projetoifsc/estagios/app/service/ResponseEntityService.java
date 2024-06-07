package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.response.wrapper.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseEntityService {

    public <T> ResponseEntity<SuccessResponse<T>> successResponse(T data) {
        return ResponseEntity.ok(new SuccessResponse<>(data));
    }

    public <T> ResponseEntity<SuccessResponse<T>> createdResponse(T data) {
        return new ResponseEntity<>(
                new SuccessResponse<>(data),
                HttpStatus.CREATED
        );
    }

}

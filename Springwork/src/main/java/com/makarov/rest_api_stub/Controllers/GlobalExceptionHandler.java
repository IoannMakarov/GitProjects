package com.makarov.rest_api_stub.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Объявляю глобальный обработчик ошибок для всех контроллеров

//public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class) //указываю, что метод обрабатывает исключения типа MethodArgumentNotValidException.
//    @ResponseStatus(HttpStatus.BAD_REQUEST) //указываю HTTP-статус, который будет возвращаться
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>(); //Map для хранения ошибок
//        //Перебираю все ошибки валидации и добавляю их в карту
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((org.springframework.validation.FieldError) error).getField(); //получаю имя поля, в котором возникла ошибка
//            String errorMessage = error.getDefaultMessage(); //получаю сообщения об ошибке
//            errors.put(fieldName, errorMessage); //добавляю ошибки в карту.
//        });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); //Возвращаю ответ с картой ошибок и статусом 400
//    }
//}

public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
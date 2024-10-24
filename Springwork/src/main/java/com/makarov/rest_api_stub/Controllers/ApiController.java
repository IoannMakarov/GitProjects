package com.makarov.rest_api_stub.Controllers;

import com.makarov.rest_api_stub.Modules.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController //обозначаю класс как контроллер для обработки HTTP запросов
@RequestMapping("/api") //сопоставляю HTTP-запросы с методами контроллера.

public class ApiController {
    @GetMapping("/get")
    //аннотация Spring, которая указывает, что метод getMethod будет обрабатывать GET-запросы по пути /get
    public ResponseEntity<String> getMethod() throws InterruptedException {
        String response = "{\"message\": \"This is a GET request\"}";
        // Имитация задержки
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000)); //генерация задержки в диапазоне от 1-2 секунд
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    //    @PostMapping("/post") //аннотация Spring, которая указывает, что метод postMethod будет обрабатывать POST-запросы по пути /post
//    public ResponseEntity<User> postMethod1(@Valid @RequestBody User parameters) throws InterruptedException {
//        // JSON-данные из тела POST-запроса преобразуются в объект класса User с помощью аннотации @RequestBody и библиотеки Jackson.
//        // Имитация задержки
//        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
//                User user = new User(parameters.getLogin(),parameters.getPassword());
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
    @PostMapping("/post")
    //аннотация Spring, которая указывает, что метод postMethod будет обрабатывать POST-запросы по пути /post
    public ResponseEntity<Map<String, String>> postMethod(@RequestBody Map<String, String> parameters) throws InterruptedException {
        // JSON-данные из тела POST-запроса преобразуются в объект класса User с помощью аннотации @RequestBody и библиотеки Jackson.
        // Имитация задержки
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        // Проверка наличия ровно двух полей login и password
        if (parameters.size() != 2 || !parameters.containsKey("login") || !parameters.containsKey("password")) {
            throw new IllegalArgumentException("Request body must contain exactly two fields: login and password");
        }
        if (parameters.get("login") == "" || parameters.get("password") == "") {
            throw new IllegalArgumentException("In the request body, the fields: login or password must not be empty");
        }
        User user = new User(parameters.get("login"), parameters.get("password"));
        Map<String, String> Excepmap = new LinkedHashMap<>();
        Excepmap.put("login", user.getLogin());
        Excepmap.put("password", user.getPassword());
        Excepmap.put("data", user.getData());
        return new ResponseEntity<>(Excepmap, HttpStatus.OK);
    }
}
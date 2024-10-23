package com.makarov.rest_api_stub;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@RestController //обозначаю класс как контроллер для обработки HTTP запросов
@RequestMapping("/api") //сопоставляю HTTP-запросы с методами контроллера.

public class ApiController {
    @GetMapping("/get") //аннотация Spring, которая указывает, что метод getMethod будет обрабатывать GET-запросы по пути /get
    public String getMethod() throws InterruptedException {
        // Имитация задержки
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000)); //генерация задержки в диапазоне от 1-2 секунд
        return "{\"message\": \"This is a GET request\"}"; //возвращаю строку в формате json (статический JSON-объект с одним полем message)
    }
    @PostMapping("/post") //аннотация Spring, которая указывает, что метод postMethod будет обрабатывать POST-запросы по пути /post
    public String postMethod(@RequestBody UserCredentials credentials) throws InterruptedException {
        // JSON-данные из тела POST-запроса преобразуются в объект класса UserCredentials с помощью аннотации @RequestBody и библиотеки Jackson.
        // Имитация задержки
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        String response = String.format(
                "{\"login\": \"%s\", \"password\": \"%s\", \"date\": \"%s\"}", //возвращаю строку в формате json динамически
                credentials.getLogin(),
                credentials.getPassword(),
                LocalDateTime.now()
        );
        return response;
    }
}

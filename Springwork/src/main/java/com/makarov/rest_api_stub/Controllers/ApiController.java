package com.makarov.rest_api_stub.Controllers;

import com.makarov.rest_api_stub.Modules.JDBCApp;
import com.makarov.rest_api_stub.Modules.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController //обозначаю класс как контроллер для обработки HTTP запросов
@RequestMapping("/api") //сопоставляю HTTP-запросы с методами контроллера.

public class ApiController {
    @GetMapping("/get")
    //аннотация Spring, которая указывает, что метод getMethod будет обрабатывать GET-запросы по пути /get
    public ResponseEntity<String> getMethod(@RequestParam String login) throws InterruptedException, SQLException {
        // Имитация задержки
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));

        // Вызов метода getUserByLogin с переданным параметром login
        JDBCApp jdbc = new JDBCApp();
        User us_get = jdbc.fetchUserByLogin(login);
        if (us_get == null) {
            // Если пользователь не найден
            throw new UserNotFoundException("User not found");
        }
        String response = us_get.toString();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    @PostMapping("/post")
    //аннотация Spring, которая указывает, что метод postMethod будет обрабатывать POST-запросы по пути /post
    public ResponseEntity<String> postMethod(@RequestBody Map<String, String> parameters) throws InterruptedException, SQLException {
        // JSON-данные из тела POST-запроса преобразуются в объект класса User с помощью аннотации @RequestBody и библиотеки Jackson.
        // Имитация задержки
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        // Проверка наличия ровно двух полей login и password
        if (parameters.size() != 3 ||
                !parameters.containsKey("login") ||
                !parameters.containsKey("password") ||
                !parameters.containsKey("email") )
        throw new IllegalArgumentException("Invalid data format");

        if (parameters.get("login") == "" || parameters.get("password") == ""|| parameters.get("email") == "") {
            throw new IllegalArgumentException("Fields must not be empty");
        }
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(Timestamp.valueOf("2023-01-01 00:00:00").getTime(), Timestamp.valueOf("2023-12-31 23:59:59").getTime());
        Timestamp randomTimestamp = new Timestamp(randomMillisSinceEpoch);
        User user = new User(parameters.get("login"), parameters.get("password"),parameters.get("email"),randomTimestamp);
        JDBCApp jdbc = new JDBCApp();
        int updateCount = jdbc.updateUserRecord(user);
        Map<String, String> Excepmap = new LinkedHashMap<>();
        Excepmap.put("login", user.getLogin());
        Excepmap.put("password", user.getPassword());
        Excepmap.put("email", user.getEmail());
        Excepmap.put("date",randomTimestamp.toString());

        String resultMessage = "Количество обновленных строк: " + updateCount;
        System.out.println(resultMessage);

        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }
}
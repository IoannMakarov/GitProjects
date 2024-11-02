package com.makarov.rest_api_stub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication //указываю, что класс RestApiStubApplication является основным классом Spring Boot приложения и включает автоконфигурацию, сканирование компонентов и настройку конфигурации.
    public class Rest_Api_Stub_Application {

        public static void main(String[] args) throws SQLException {
            SpringApplication.run(Rest_Api_Stub_Application.class, args);
        }
    }


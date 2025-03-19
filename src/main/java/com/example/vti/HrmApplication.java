package com.example.vti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class HrmApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HrmApplication.class, args);

        // Get all beans
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("Application Bean Names:");

        Arrays.stream(beanNames)
                .filter(name -> {
                    try {
                        String packageName = context.getBean(name).getClass().getPackageName();
                        return packageName.startsWith("com.example.vti"); // Change to your package name
                    } catch (Exception e) {
                        return false;
                    }
                })
                .forEach(System.out::println);
    }
}


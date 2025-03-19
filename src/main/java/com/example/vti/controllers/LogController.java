package com.example.vti.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${api.version}/log")
public class LogController {

    @GetMapping
    public String logExample() {
        log.info("Thông báo INFO: Ghi log ra console và file");
        log.warn("Cảnh báo WARNING: Đây là một cảnh báo!");
        log.error("Lỗi ERROR: Có lỗi xảy ra!");

        return "Log đã được ghi!";
    }
}


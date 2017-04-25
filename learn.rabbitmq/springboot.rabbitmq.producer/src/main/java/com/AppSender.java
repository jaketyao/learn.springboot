package com;

import com.example.CustomMessageSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yaojie on 2017/4/25.
 */
@SpringBootApplication
@Controller
public class AppSender {

    @Autowired
    CustomMessageSender customMessageSender;

    @GetMapping(value = "/test/{str}")
    public ResponseEntity<String> produce(@PathVariable String str)
    {
        for (int i = 1; i <=10; i++)
        {
            customMessageSender.sendMessage(i);
        }

        return new ResponseEntity<String>("Message sent or queue",
                HttpStatus.OK);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppSender.class, args);
    }
}

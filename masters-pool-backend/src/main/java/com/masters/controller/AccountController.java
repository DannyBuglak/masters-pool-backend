package com.masters.controller;

import com.masters.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController {
    /*
    * Controller for User Account processes - logging in, logging out, authentication, etc.
    * */

    @Autowired
    private AccountService accountService;

    @GetMapping("/v1/hello-project")
    public String helloProject() {
        log.info("Hello Project GET Endpoint hit");
        accountService.helloProject();
        return "Hello Project";
    }
}

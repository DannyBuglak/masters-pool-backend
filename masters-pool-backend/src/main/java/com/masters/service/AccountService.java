package com.masters.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {
    /*
     * Service for User Account processes - logging in, logging out, authentication, etc.
     * */

    public void helloProject() {
        log.info("Info log in hello Project in Service class");
        log.debug("Debug log in hello Project in Service class");
        log.error("Error log in hello Project in Service class");
    }
}

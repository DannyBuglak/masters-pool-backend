package com.masters.service;

import ch.qos.logback.classic.Logger;
import com.masters.controller.AccountController;
import com.masters.testUtil.LogEntry;
import com.masters.testUtil.MemoryAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountServiceTest {

    private AccountService accountService;

    private MemoryAppender memoryAppender;
    private static final Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(AccountService.class);

    @BeforeEach
    void setup() {
        memoryAppender = new MemoryAppender();
        memoryAppender.start();
        logger.addAppender(memoryAppender);

        accountService = new AccountService();
    }

    @Test
    void helloProject_ShouldCallLogMethods() {
        // Arrange

        // Act
        accountService.helloProject();

        // Assert
        List<LogEntry> logEntries = memoryAppender.getLogEntries();

        boolean infoLogged = logEntries.stream().anyMatch(
                event -> event.level().equals("INFO")
                        && event.message().contains("Info log in hello Project in Service class"));

        boolean debugLogged = logEntries.stream().anyMatch(
                event -> event.level().equals("DEBUG")
                        && event.message().contains("Debug log in hello Project in Service class"));

        boolean errorLogged = logEntries.stream().anyMatch(
                event -> event.level().equals("ERROR")
                        && event.message().contains("Error log in hello Project in Service class"));

        assertTrue(infoLogged);
        assertTrue(debugLogged);
        assertTrue(errorLogged);
    }

}

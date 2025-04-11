package com.masters.controller;

import com.masters.service.AccountService;
import com.masters.testUtil.LogEntry;
import com.masters.testUtil.MemoryAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ch.qos.logback.classic.Logger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private MemoryAppender memoryAppender;
    private static final Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(AccountController.class);

    @BeforeEach
    public void setup() {
        memoryAppender = new MemoryAppender();
        memoryAppender.start();
        logger.addAppender(memoryAppender);

        // Set up mock behavior
        doNothing().when(accountService).helloProject();
    }

    @Test
    public void helloProject_ShouldReturnHelloProjectMessage() throws Exception {
        // Verify actions
        mockMvc.perform(get("/v1/hello-project"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Project"));

        verify(accountService, times(1)).helloProject();

        // Verify Logs
        List<LogEntry> logEntries = memoryAppender.getLogEntries();

        String expectedLog = "Hello Project GET Endpoint hit";
        boolean logFound = logEntries.stream()
                .anyMatch(logEntry ->
                        logEntry.message().contains(expectedLog) && logEntry.level().equals("INFO"));
        assertTrue(logFound);
    }

}

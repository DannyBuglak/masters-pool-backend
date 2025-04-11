package com.masters.testUtil;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

public class MemoryAppender extends AppenderBase<ILoggingEvent> {

    private final List<LogEntry> logEntries = new ArrayList<>();

    // Capture log messages
    @Override
    protected void append(ILoggingEvent eventObject) {
        // Capture Logs with Log Message and Logging Level for testing
        LogEntry logEntry = new LogEntry(eventObject.getFormattedMessage(), eventObject.getLevel().toString());
        logEntries.add(logEntry);
    }

    // Getter to retrieve logs
    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    // Clear captured logs
    public void clear() {
        logEntries.clear();
    }

}

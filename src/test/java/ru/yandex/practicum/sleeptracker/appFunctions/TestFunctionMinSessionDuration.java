package ru.yandex.practicum.sleeptracker.appFunctions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.List;

public class TestFunctionMinSessionDuration {
    public List<SleepingSession> sessions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        sessions.add(new SleepingSession("20.12.25 23:59;21.12.25 00:09;GOOD"));
        sessions.add(new SleepingSession("21.12.25 23:59;22.12.25 01:00;BAD"));
        sessions.add(new SleepingSession("22.12.25 23:59;23.12.25 00:09;NORMAL"));
        sessions.add(new SleepingSession("23.12.25 23:59;24.12.25 02:00;GOOD"));
    }

    @Test
    public void shouldMinSession9Minutes() {
        FunctionMinSessionDuration f = new FunctionMinSessionDuration();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(10L, result.getValue());
    }

    @Test
    public void shouldMinSession1Minute() {
        sessions.add(new SleepingSession("24.12.25 23:59;25.12.25 00:00;GOOD"));
        FunctionMinSessionDuration f = new FunctionMinSessionDuration();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(1L, result.getValue());
    }
}

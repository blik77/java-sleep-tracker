package ru.yandex.practicum.sleeptracker.appFunctions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.List;

public class TestFunctionCountSleepSessions {
    public List<SleepingSession> sessions = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        sessions.add(new SleepingSession("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sessions.add(new SleepingSession("02.10.25 23:50;03.10.25 06:40;NORMAL"));
        sessions.add(new SleepingSession("03.10.25 14:10;03.10.25 15:00;NORMAL"));
        sessions.add(new SleepingSession("03.10.25 23:40;04.10.25 08:00;BAD"));
    }

    @Test
    public void shouldCountZero() {
        sessions.clear();
        FunctionCountSleepSessions f = new FunctionCountSleepSessions();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(0, result.getValue());
    }

    @Test
    public void shouldCountFour() {
        FunctionCountSleepSessions f = new FunctionCountSleepSessions();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(4, result.getValue());
    }
}

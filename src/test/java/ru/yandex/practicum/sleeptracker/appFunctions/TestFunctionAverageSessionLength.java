package ru.yandex.practicum.sleeptracker.appFunctions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.List;

public class TestFunctionAverageSessionLength {
    public List<SleepingSession> sessions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        sessions.add(new SleepingSession("20.12.25 23:59;21.12.25 00:09;GOOD"));
        sessions.add(new SleepingSession("22.12.25 00:00;22.12.25 01:40;BAD"));
        sessions.add(new SleepingSession("23.12.25 00:01;23.12.25 00:09;NORMAL"));
        sessions.add(new SleepingSession("24.12.25 00:00;24.12.25 02:05;GOOD"));
    }

    @Test
    public void shouldAverageLength60Minutes() {
        FunctionAverageSessionLength f = new FunctionAverageSessionLength();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(60L, result.getValue());
    }

    @Test
    public void shouldAverageLength2Hours() {
        sessions.add(new SleepingSession("25.12.25 22:33;26.12.25 04:34;GOOD"));
        FunctionAverageSessionLength f = new FunctionAverageSessionLength();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(120L, result.getValue());
    }
}

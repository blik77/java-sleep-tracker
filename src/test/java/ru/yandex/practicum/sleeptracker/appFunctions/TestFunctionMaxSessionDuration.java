package ru.yandex.practicum.sleeptracker.appFunctions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.List;

public class TestFunctionMaxSessionDuration {
    public List<SleepingSession> sessions = new ArrayList<SleepingSession>();
    @BeforeEach
    public void setUp() {
        sessions.add(new SleepingSession("20.12.25 23:59;21.12.25 00:09;GOOD"));
        sessions.add(new SleepingSession("22.12.25 00:00;22.12.25 01:00;BAD"));
        sessions.add(new SleepingSession("23.12.25 00:01;23.12.25 00:09;NORMAL"));
        sessions.add(new SleepingSession("24.12.25 00:00;24.12.25 02:00;GOOD"));
    }

    @Test
    public void shouldMaxSession2Hours() {
        FunctionMaxSessionDuration f = new FunctionMaxSessionDuration();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(120L, result.getValue());
    }

    @Test
    public void shouldMaxSession8Hours27Minutes() {
        sessions.add(new SleepingSession("25.12.25 22:33;26.12.25 07:00;GOOD"));
        FunctionMaxSessionDuration f = new FunctionMaxSessionDuration();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(8L*60 + 27L, result.getValue());
    }
}

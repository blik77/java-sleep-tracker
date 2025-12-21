package ru.yandex.practicum.sleeptracker.appFunctions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.List;

public class TestFunctionCountSessionsBadSleepQuality {
    public List<SleepingSession> sessions = new ArrayList<SleepingSession>();
    @BeforeEach
    public void setUp() {
        sessions.add(new SleepingSession("20.12.25 23:59;21.12.25 00:09;GOOD"));
        sessions.add(new SleepingSession("22.12.25 00:00;22.12.25 01:40;BAD"));
        sessions.add(new SleepingSession("23.12.25 00:01;23.12.25 00:09;NORMAL"));
        sessions.add(new SleepingSession("24.12.25 00:00;24.12.25 02:05;GOOD"));
    }

    @Test
    public void shouldBadSleepQuality1() {
        FunctionCountSessionsBadSleepQuality f = new FunctionCountSessionsBadSleepQuality();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(1L, result.getValue());
    }

    @Test
    public void shouldBadSleepQuality2() {
        sessions.add(new SleepingSession("25.12.25 22:33;26.12.25 04:34;BAD"));
        FunctionCountSessionsBadSleepQuality f = new FunctionCountSessionsBadSleepQuality();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(2L, result.getValue());
    }
}

package ru.yandex.practicum.sleeptracker.appFunctions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.UserChronotype;

import java.util.ArrayList;
import java.util.List;

public class TestFunctionClassifyingUser {
    public List<SleepingSession> sessions = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        sessions.add(new SleepingSession("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sessions.add(new SleepingSession("02.10.25 23:50;03.10.25 06:40;NORMAL"));
        sessions.add(new SleepingSession("03.10.25 14:10;03.10.25 15:00;NORMAL"));
        sessions.add(new SleepingSession("03.10.25 23:40;04.10.25 08:00;BAD"));
    }

    @Test
    public void shouldBePigeon() {
        FunctionClassifyingUser f = new FunctionClassifyingUser();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(UserChronotype.PIGEON, result.getValue());
    }

    @Test
    public void shouldBeOwl() {
        sessions.add(new SleepingSession("06.10.25 00:10;07.10.25 10:20;GOOD"));
        sessions.add(new SleepingSession("07.10.25 01:10;08.10.25 11:20;GOOD"));
        sessions.add(new SleepingSession("08.10.25 03:40;09.10.25 13:00;GOOD"));
        sessions.add(new SleepingSession("09.10.25 03:40;10.10.25 13:00;GOOD"));
        FunctionClassifyingUser f = new FunctionClassifyingUser();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(UserChronotype.OWL, result.getValue());
    }

    @Test
    public void shouldBeLark() {
        sessions.add(new SleepingSession("06.10.25 21:10;07.10.25 06:20;GOOD"));
        sessions.add(new SleepingSession("07.10.25 21:10;08.10.25 06:20;GOOD"));
        sessions.add(new SleepingSession("08.10.25 20:40;09.10.25 05:00;GOOD"));
        sessions.add(new SleepingSession("09.10.25 20:45;10.10.25 06:00;GOOD"));
        FunctionClassifyingUser f = new FunctionClassifyingUser();
        SleepAnalysisResult result = f.apply(sessions);

        Assertions.assertEquals(UserChronotype.LARK, result.getValue());
    }
}

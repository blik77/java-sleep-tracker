package ru.yandex.practicum.sleeptracker.appFunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionCountSleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long count = 0;
        if (!sleepingSessions.isEmpty()) {
            LocalDate firstData;
            LocalDate lastData = sleepingSessions.getLast().getFinishSleep().toLocalDate();

            if (sleepingSessions.getFirst().getStartSleep().toLocalTime().isAfter(LocalTime.of(12, 0))) {
                firstData = sleepingSessions.getFirst().getStartSleep().toLocalDate().plusDays(1);
            } else {
                firstData = sleepingSessions.getFirst().getStartSleep().toLocalDate();
            }

            int countAllNights = Period.between(firstData, lastData).getDays() + 1;

            Predicate<SleepingSession> isSleepingNight = s ->
                s.getStartSleep().toLocalDate().isBefore(s.getFinishSleep().toLocalDate())
                || s.getStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0));

            long sleepNights = sleepingSessions.stream().filter(isSleepingNight).count();

            count = countAllNights - sleepNights;
        }
        return new SleepAnalysisResult("Количество бессонных ночей", count);
    }
}

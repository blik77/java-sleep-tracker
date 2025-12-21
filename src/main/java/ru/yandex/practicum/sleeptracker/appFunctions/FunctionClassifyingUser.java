package ru.yandex.practicum.sleeptracker.appFunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.UserChronotype;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionClassifyingUser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Predicate<SleepingSession> isSleepingNight = s ->
            s.getStartSleep().toLocalDate().isBefore(s.getFinishSleep().toLocalDate())
            || s.getStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0));

        Predicate<SleepingSession> isOwl = s -> (
                s.getStartSleep().toLocalTime().isAfter(LocalTime.of(23, 0))
                || s.getStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0))
            ) && (s.getFinishSleep().toLocalTime().isAfter(LocalTime.of(9, 0)));

        Predicate<SleepingSession> isLark = s -> (
            s.getStartSleep().toLocalTime().isBefore(LocalTime.of(22, 0))
            && s.getStartSleep().toLocalTime().isAfter(LocalTime.of(6, 0))
            && s.getFinishSleep().toLocalTime().isBefore(LocalTime.of(7, 0))
        );

        List<SleepingSession> sleepNights = sleepingSessions.stream().filter(isSleepingNight).toList();

        long countOwl = sleepNights.stream().filter(isOwl).count();
        long countLark = sleepNights.stream().filter(isLark).count();
        long countPigeon = sleepNights.size() - countOwl - countLark;

        UserChronotype userTpe = UserChronotype.PIGEON;
        if (countOwl > countLark && countOwl > countPigeon) {
            userTpe = UserChronotype.OWL;
        } else if (countLark > countOwl && countLark > countPigeon) {
            userTpe = UserChronotype.LARK;
        }

        return new SleepAnalysisResult("Ваш тип", userTpe);
    }
}

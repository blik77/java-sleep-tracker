package ru.yandex.practicum.sleeptracker.appFunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.UserChronotype;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Map.entry;

public class FunctionClassifyingUser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Predicate<SleepingSession> isOwl = s -> (
                s.getStartSleep().toLocalTime().isAfter(LocalTime.of(23, 0))
                || s.getStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0))
            ) && (s.getFinishSleep().toLocalTime().isAfter(LocalTime.of(9, 0)));

        Predicate<SleepingSession> isLark = s -> (
            s.getStartSleep().toLocalTime().isBefore(LocalTime.of(22, 0))
            && s.getStartSleep().toLocalTime().isAfter(LocalTime.of(6, 0))
            && s.getFinishSleep().toLocalTime().isBefore(LocalTime.of(7, 0))
        );

        Map<UserChronotype, Integer> counter = new HashMap<>(Map.ofEntries(
            entry(UserChronotype.OWL, 0),
            entry(UserChronotype.LARK, 0),
            entry(UserChronotype.PIGEON, 0)
        ));
        sleepingSessions.stream().filter(FunctionCountSleeplessNights.isSleepingNight)
        .forEach(s -> {
            if (isOwl.test(s)) {
                counter.put(UserChronotype.OWL, counter.get(UserChronotype.OWL) + 1);
            } else if (isLark.test(s)) {
                counter.put(UserChronotype.LARK, counter.get(UserChronotype.LARK) + 1);
            } else {
                counter.put(UserChronotype.PIGEON, counter.get(UserChronotype.PIGEON) + 1);
            }
        });

        UserChronotype userTpe = UserChronotype.PIGEON;
        if (counter.get(UserChronotype.OWL) > counter.get(UserChronotype.LARK)
            && counter.get(UserChronotype.OWL) > counter.get(UserChronotype.PIGEON)) {
            userTpe = UserChronotype.OWL;
        } else if (counter.get(UserChronotype.LARK) > counter.get(UserChronotype.OWL)
                && counter.get(UserChronotype.LARK) > counter.get(UserChronotype.PIGEON)) {
            userTpe = UserChronotype.LARK;
        }

        return new SleepAnalysisResult("Ваш тип", userTpe);
    }
}

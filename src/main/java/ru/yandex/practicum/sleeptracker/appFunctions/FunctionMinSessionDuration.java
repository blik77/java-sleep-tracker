package ru.yandex.practicum.sleeptracker.appFunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class FunctionMinSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long duration = sleepingSessions.stream()
                .map(SleepingSession::getDurationSleep).min(Long::compare).orElse(0L);

        return new SleepAnalysisResult("Минимальная продолжительность сессии (в минутах)", duration);
    }
}

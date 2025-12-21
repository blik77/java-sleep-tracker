package ru.yandex.practicum.sleeptracker.appFunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class FunctionAverageSessionLength implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long duration = sleepingSessions.stream()
                .map(SleepingSession::getDurationSleep).reduce(0L, Long::sum) / sleepingSessions.size();

        return new SleepAnalysisResult("Средняя продолжительность сессии (в минутах)", duration);
    }
}

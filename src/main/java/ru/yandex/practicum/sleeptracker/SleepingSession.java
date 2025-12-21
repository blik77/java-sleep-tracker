package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
    private final LocalDateTime startSleep;
    private final LocalDateTime finishSleep;
    private final QualitySleep qualitySleep;

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepingSession(String line) {
        String[] split = line.split(";");
        this.startSleep = LocalDateTime.parse(split[0], FORMAT_DATE);
        this.finishSleep = LocalDateTime.parse(split[1], FORMAT_DATE);
        this.qualitySleep = QualitySleep.valueOf(split[2]);
    }

    @Override
    public String toString() {
        return String.format("Вы спали с %s по %s - %s", startSleep.format(FORMAT_DATE), finishSleep.format(FORMAT_DATE), qualitySleep);
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public LocalDateTime getFinishSleep() {
        return finishSleep;
    }

    public QualitySleep getQualitySleep() {
        return qualitySleep;
    }

    public long getDurationSleep() {
        return Duration.between(startSleep, finishSleep).toMinutes();
    }
}

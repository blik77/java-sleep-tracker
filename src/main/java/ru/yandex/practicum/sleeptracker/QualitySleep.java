package ru.yandex.practicum.sleeptracker;

public enum QualitySleep {
    GOOD, NORMAL, BAD;

    @Override
    public String toString() {
        return switch (this) {
            case GOOD -> "ХОРОШО";
            case NORMAL -> "НОРМАЛЬНО";
            case BAD -> "ПЛОХО";
        };
    }
}

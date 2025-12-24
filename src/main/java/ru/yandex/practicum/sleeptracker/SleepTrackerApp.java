package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.appFunctions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>>
            appFunctions = Arrays.asList(
            new FunctionCountSleepSessions(),
            new FunctionMinSessionDuration(),
            new FunctionMaxSessionDuration(),
            new FunctionAverageSessionLength(),
            new FunctionCountSessionsBadSleepQuality(),
            new FunctionCountSleeplessNights(),
            new FunctionClassifyingUser()
    );

    public static void main(String[] args) {
        String path;
        if (args.length == 0) {
            System.out.println("Необходимо передать в командной строке путь к файлу с логом сна.");
            System.out.println("Будет использован стандартный файл.");
            path = "src/main/resources/sleep_log.txt";
        } else {
            path = args[0];
        }
        System.out.println("Чтение файла: " + path);

        try (BufferedReader br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            List<SleepingSession> sessions = br.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .map(SleepingSession::new)
                    .collect(Collectors.toList());

            appFunctions.stream().map(f -> f.apply(sessions)).forEach(System.out::println);
        } catch (NoSuchFileException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении!" + e.getMessage());
        }
    }
}
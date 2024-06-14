package pl.pwr.ite.aizo2.service.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.IntSummaryStatistics;

/**
 * Utility class used to calculate and print result of operation
 */
public class TimeUtils {

    public static float printTimeElapsed(Instant start, Instant finish, String prompt) {
        float elapsed = calculateTimeElapsed(start, finish);
        System.out.printf("%s zajęło %.3fms\n", prompt, elapsed);
        return elapsed;
    }

    /**
     *
     * @param start reference time of {@link Instant} type
     * @return the amount of seconds elapsed from start to current time in milliseconds
     */
    private static float calculateTimeElapsed(Instant start, Instant finish) {
        return Duration.between(start, finish).toMillis();
    }
}

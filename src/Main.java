import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.now();
        int weeksAhead = 27;

        IntStream
                .range(0, weeksAhead * 7)
                .boxed()
                .map(startDate::plusDays)
                .collect(Collectors.groupingBy(localDate -> localDate.get(WeekFields.ISO.weekOfWeekBasedYear())))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->

                    System.out.println(entry.getValue()
                            .stream()
                            .map(localDate -> {
                                String pattern = "d";

                                if (localDate.getDayOfMonth() == 1) {
                                    pattern = "d/M";
                                }


                                return localDate.format(DateTimeFormatter.ofPattern(pattern));

                            })
                            .collect(Collectors.joining(",")))
                );
    }
}

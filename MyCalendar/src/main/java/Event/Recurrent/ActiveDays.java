package Event.Recurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActiveDays {
    private final List<WeekDay> activeDays;
    public ActiveDays(WeekDay... activeDays) {
        this.activeDays = Arrays.stream(activeDays).distinct().collect(Collectors.toList());
    }

    public List<WeekDay> getActiveDays() {
        return activeDays;
    }
}

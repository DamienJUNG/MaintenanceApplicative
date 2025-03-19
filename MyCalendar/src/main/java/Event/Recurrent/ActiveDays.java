package Event.Recurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActiveDays {
    List<WeekDay> activeDays;
    public ActiveDays(WeekDay... activeDays) {
        this.activeDays = Arrays.stream(activeDays).distinct().collect(Collectors.toList());
    }

    public List<WeekDay> getActiveDays() {
        return activeDays;
    }

    public void addActiveDay(WeekDay activeDay) {
        if(!activeDays.contains(activeDay)) {
            activeDays.add(activeDay);
        }
    }

    public void removeActiveDay(WeekDay activeDay) {
        activeDays.remove(activeDay);
    }
}

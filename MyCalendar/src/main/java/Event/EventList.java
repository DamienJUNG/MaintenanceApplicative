package Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventList {
    private final List<Event> events;
    public EventList() {
        events = new ArrayList<>();
    }
    public EventList(Event... events) {
        this.events = new ArrayList<>(Arrays.asList(events));
    }
    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEventById(int id) {

    }

    public String displayEvents() {
        StringBuilder sb = new StringBuilder();
        for (Event event : events) {
            sb.append(event.description()).append("\n");
        }
        return sb.toString();
    }

    public int size() {
        return events.size();
    }

    public Event get(int index) {
        return events.get(index);
    }
}

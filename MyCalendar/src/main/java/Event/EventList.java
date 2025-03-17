package Event;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    private final List<Event> events;
    public EventList() {
        events = new ArrayList<>();
    }
    public EventList(List<Event> events) {
        this.events = new ArrayList<>(events);
    }
    public void addEvent(Event event) {
        events.add(event);
    }
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(int index, Event event) {
        events.set(index, event);
    }
}

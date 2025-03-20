package Event;

import Event.Periodique.Periodique;

import java.time.LocalDateTime;
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
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                events.remove(i);
                return;
            }
        }
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

    public EventList eventsDansPeriode(LocalDateTime start, LocalDateTime end) {
        EventList eventList = new EventList();
        for (Event event : events) {
            EventList occurrences = event.occurencesInPeriod(start, end);
            for (int i = 0; i < occurrences.size(); i++) {
                eventList.addEvent(occurrences.get(i));
            }
        }
        return eventList;
    }

    public boolean checkConlifts(Event event) {
        for (Event e : events) {
            if(e.checkConflit(event)){
                return false;
            }
        }
        return true;
    }
}

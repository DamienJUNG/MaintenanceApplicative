package Event;

import java.time.LocalDateTime;

public class DateDebut {
    LocalDateTime dateDebut;
    public DateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    @Override
    public String toString() {
        return dateDebut.getDayOfMonth()+" "+dateDebut.getMonth()+" "+dateDebut.getYear()+" à "+dateDebut.getHour()+"h"+dateDebut.getMinute();
    }
}

package Event.Action.EventHandlerAction;

import CalendarManager.CalendarManager;
import Event.Action.CalendarAction.*;
import User.User;

import java.util.Scanner;

public class DisplayEventAction extends EventAction {
    private CalendarActionList calendarActionList;

    public DisplayEventAction(CalendarManager calendar) {
        super(calendar);

        actionDescription = "Voir les événements";

        calendarActionList = new CalendarActionList(calendar);
        calendarActionList.addAction(new DisplayEveryEventAction());
        calendarActionList.addAction(new DisplayMonthEventAction());
        calendarActionList.addAction(new DisplayWeekEventAction());
        calendarActionList.addAction(new DisplayDayEventAction());
        calendarActionList.addAction(new CalendarReturnAction());
    }

    @Override
    public boolean handle(User user) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(calendarActionList.display());
        System.out.print("Quelle est votre choix"+user.getUsername()+" ?");

        int command = Integer.parseInt(scanner.nextLine());
        return calendarActionList.handle(command);
    }
}

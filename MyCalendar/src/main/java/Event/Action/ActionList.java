package Event.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ActionList<T extends Action, R> {
    protected final List<T> actionList;
    protected Function<T, R> handler; // Fonction pour exécuter handle()

    public ActionList(Function<T, R> handler) {
        this.actionList = new ArrayList<>();
        this.handler = handler;
    }

    public void addAction(T action) {
        this.actionList.add(action);
    }

    public R handle(int index) {
        if(actionList.isEmpty() || index > this.actionList.size() - 1) {
            return null;
        }
        return handler.apply(actionList.get(index));
    }

    public String display() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < actionList.size(); i++) {
            stringBuilder.append(actionList.get(i).display(i));
            if (i != actionList.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}

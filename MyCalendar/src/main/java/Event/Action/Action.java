package Event.Action;

public abstract class Action {
    protected String actionDescription;

    public String display(int index) {
        return index+" - "+actionDescription;
    }
}

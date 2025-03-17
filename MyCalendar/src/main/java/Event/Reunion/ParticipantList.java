package Event.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipantList {
    private final List<Participant> participants;
    public ParticipantList() {
        participants = new ArrayList<>();
    }
    public ParticipantList(String participants) {
        this.participants = new ArrayList<>();
        for (String participant : participants.split(",")) {
            this.participants.add(new Participant(participant));
        }
    }
    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
    public Participant getParticipants(int index, Participant participant) {
        return participants.get(index);
    }
    public int nbParticipants() {
        return participants.size();
    }
    public void setParticipants(int index, Participant participant) {
        participants.set(index, participant);
    }
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    @Override
    public String toString() {
        return Arrays.toString(participants.toArray());
    }
}

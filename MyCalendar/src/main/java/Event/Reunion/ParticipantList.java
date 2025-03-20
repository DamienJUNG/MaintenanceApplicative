package Event.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

public class ParticipantList {
    private final List<Participant> participants;
    public ParticipantList() {
        participants = new ArrayList<>();
    }
    public ParticipantList(String participants) {
        this.participants = new ArrayList<>();
        for (String participant : participants.split(",")) {
            this.participants.add(new Participant(participant.trim()));
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
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < participants.size(); i++) {
            String participant = participants.get(i).getName();
            if(i==participants.size()-1){
                builder.append(participant);
            }
            else if(i==participants.size()-2) {
                builder.append(participant).append(" et ");
            }
            else {
                builder.append(participant).append(", ");
            }
        }
        return builder.toString();
    }
}

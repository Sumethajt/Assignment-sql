package entities;

import java.util.ArrayList;
import java.util.List;

public class AdoptionEvent {
    private List<IAdoptable> participants;

    public AdoptionEvent() {
        this.participants = new ArrayList<>();
    }

    // Registers a participant (shelter or adopter)
    public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
        System.out.println("Participant registered for the adoption event.");
    }

    // Hosts the event by calling adopt() on each participant
    public void hostEvent() {
        System.out.println("Hosting the adoption event...");
        if (participants.isEmpty()) {
            System.out.println("No participants in this event.");
            return;
        }
        for (IAdoptable participant : participants) {
            participant.adopt();
        }
    }

    public List<IAdoptable> getParticipants() {
        return participants;
    }
}

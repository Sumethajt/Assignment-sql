package entities;

import java.util.ArrayList;
import java.util.List;

public class PetShelter {
    private List<Pet> availablePets;

    public PetShelter() {
        this.availablePets = new ArrayList<>();
    }

    public void addPet(Pet pet) {
        availablePets.add(pet);
    }

    public void removePet(Pet pet) {
        availablePets.remove(pet);
    }

    public void listAvailablePets() {
        if (availablePets.isEmpty()) {
            System.out.println("No pets available in the shelter.");
            return;
        }
        for (Pet pet : availablePets) {
            System.out.println(pet);
        }
    }

    public List<Pet> getAvailablePets() {
        return availablePets;
    }
}

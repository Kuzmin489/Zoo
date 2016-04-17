package lv.autentica.dto.animal;

import lv.autentica.domain.animal.AnimalFeeds;

public class AnimalFeedDTO {
    private Long id;
    private Long animalId;
    private Long workerId;

    public AnimalFeedDTO(AnimalFeeds animalFeed) {
        this.animalId = animalFeed.getAnimal().getId();
        this.workerId = animalFeed.getWorker().getId();
        this.animalId = animalFeed.getId();

    }

    public AnimalFeedDTO() {
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

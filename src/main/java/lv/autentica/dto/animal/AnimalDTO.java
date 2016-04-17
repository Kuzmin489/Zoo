package lv.autentica.dto.animal;

import lv.autentica.domain.animal.Animal;


public class AnimalDTO {
    private Long id;
    private String name;
    private Long typeId;
    private String sex;
    private Double age;
    private Double weight;
    private String features;
    private String comments;

    public AnimalDTO() {
    }

    public AnimalDTO(Animal animal) {
        this.name = animal.getName();
        this.typeId = animal.getAnimalType().getId();
        this.sex = animal.getSex();
        this.age = animal.getAge();
        this.weight = animal.getWeight();
        this.features = animal.getFeatures();
        this.comments = animal.getComments();
        this.id = animal.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

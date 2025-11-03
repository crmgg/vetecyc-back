package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class RaceEntity {

    private UUID id;
    private PetTypeEntity petType;
    private String name;

    public RaceEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setPetType(PetTypeEntity.createDefault());
        setName(TextHelper.getDefault());
    }

    public RaceEntity(final UUID id) {
        setId(id);
        setPetType(PetTypeEntity.createDefault());
        setName(TextHelper.getDefault());
    }

    public RaceEntity(final UUID id, final PetTypeEntity petType, final String name) {
        setId(id);
        setPetType(petType);
        setName(name);
    }

    public static RaceEntity createDefault() {
        return new RaceEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public PetTypeEntity getPetType() {
        return petType;
    }

    public void setPetType(final PetTypeEntity petType) {
        this.petType = petType == null ? PetTypeEntity.createDefault() : petType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
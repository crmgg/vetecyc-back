package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class PetTypeEntity {

    private UUID id;
    private String name;

    public PetTypeEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public PetTypeEntity(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
    }

    public PetTypeEntity(final UUID id, final String name) {
        setId(id);
        setName(name);
    }

    public static PetTypeEntity createDefault() {
        return new PetTypeEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
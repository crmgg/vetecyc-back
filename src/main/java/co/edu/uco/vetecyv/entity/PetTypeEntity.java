package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class PetTypeEntity extends Entity {

    private String name;

    public PetTypeEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public PetTypeEntity(final UUID id) {
        super(id);
        setName(TextHelper.getDefault());
    }

    public PetTypeEntity(final UUID id, final String name) {
        super(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
    public static PetTypeEntity createDefault() {
        return new GenderEntity();
    }

}

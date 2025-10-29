package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class RaceEntity extends Entity{

    private PetTypeEntity petType;
    private String name;

    public RaceEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setpetType(PetTypeEntity.createDefault());
        setName(TextHelper.getDefault());
    }

    public RaceEntity(final UUID id) {
        super(id);
        setpetType(PetTypeEntity.createDefault());
        setName(TextHelper.getDefault());
    }

    public RaceEntity(final UUID id, final PetTypeEntity petTypeEntity,
                      final String name) {
        super(id);
        setpetType(petType);
        setName(name);
    }


    public PetTypeEntity getpetType() {
        return petType;
    }

    public void setpetType(final PetTypeEntity petType) {
        this.petType = petType;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
    public static RaceEntity createDefault() {
        return new RaceEntity();
    }

}




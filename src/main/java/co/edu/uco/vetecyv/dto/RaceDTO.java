package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class RaceDTO {

    private UUID id;
    private PetTypeDTO petType;
    private String name;

    public RaceDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setPetType(PetTypeDTO.getDefaultValue());
        setName(TextHelper.getDefault());
    }

    public RaceDTO(final UUID id) {
        setId(id);
        setPetType(PetTypeDTO.getDefaultValue());
        setName(TextHelper.getDefault());
    }

    public RaceDTO(final UUID id, final PetTypeDTO petType, final String name) {
        setId(id);
        setPetType(petType);
        setName(name);
    }

    static RaceDTO getDefaultValue() {
        return new RaceDTO();
    }

    static RaceDTO getDefaultValue(final RaceDTO race) {
        return ObjectHelper.getDefault(race, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public PetTypeDTO getPetType() {
        return petType;
    }

    public void setPetType(final PetTypeDTO petType) {
        this.petType = ObjectHelper.getDefault(petType, PetTypeDTO.getDefaultValue());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
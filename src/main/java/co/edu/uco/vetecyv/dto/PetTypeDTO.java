package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.PetTypeDTO;

import java.util.UUID;

public class PetTypeDTO extends DTO {

    private String name;

    public PetTypeDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public PetTypeDTO(final UUID id) {
        super(id);
        setName(TextHelper.getDefault());
    }

    public PetTypeDTO(final UUID id, final String name) {
        super(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
    public static PetTypeDTO createDefault() {
        return new PetTypeDTO();
    }

}

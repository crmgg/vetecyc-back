package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.PetTypeDTO;
import co.edu.uco.vetecyv.dto.RaceDTO;

import java.util.UUID;

public class RaceDTO extends DTO {

    private PetTypeDTO petType;
    private String name;

    public RaceDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setpetType(PetTypeDTO.createDefault());
        setName(TextHelper.getDefault());
    }

    public RaceDTO(final UUID id) {
        super(id);
        setpetType(PetTypeDTO.createDefault());
        setName(TextHelper.getDefault());
    }

    public RaceDTO(final UUID id, final PetTypeDTO petTypeDTO,
                      final String name) {
        super(id);
        setpetType(petType);
        setName(name);
    }


    public PetTypeDTO getpetType() {
        return petType;
    }

    public void setpetType(final PetTypeDTO petType) {
        this.petType = petType;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
    public static RaceDTO createDefault() {
        return new RaceDTO();
    }

}

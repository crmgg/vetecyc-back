package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.GenderDTO;

import java.util.UUID;

public class GenderDTO extends DTO {

    private String name;

    public GenderDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public GenderDTO(final UUID id) {
        super(id);
        setName(TextHelper.getDefault());
    }

    public GenderDTO(final UUID id, final String name) {
        super(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
    public static GenderDTO createDefault() {
        return new GenderDTO();
    }

}

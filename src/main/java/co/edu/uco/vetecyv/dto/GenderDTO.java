package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class GenderDTO {

    private UUID id;
    private String name;

    public GenderDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public GenderDTO(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
    }

    public GenderDTO(final UUID id, final String name) {
        setId(id);
        setName(name);
    }

    static GenderDTO getDefaultValue() {
        return new GenderDTO();
    }

    static GenderDTO getDefaultValue(final GenderDTO gender) {
        return ObjectHelper.getDefault(gender, getDefaultValue());
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
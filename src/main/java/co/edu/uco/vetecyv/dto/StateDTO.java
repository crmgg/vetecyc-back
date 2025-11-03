package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class StateDTO {

    private UUID id;
    private String name;

    public StateDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public StateDTO(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
    }

    public StateDTO(final UUID id, final String name) {
        setId(id);
        setName(name);
    }


    static StateDTO getDefaultValue() {
        return new StateDTO();
    }

    static StateDTO getDefaultValue(final StateDTO state) {
        return ObjectHelper.getDefault(state, getDefaultValue());
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
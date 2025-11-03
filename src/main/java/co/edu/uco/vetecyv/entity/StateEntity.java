package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class StateEntity {

    private UUID id;
    private String name;

    public StateEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public StateEntity(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
    }

    public StateEntity(final UUID id, final String name) {
        setId(id);
        setName(name);
    }

    public static StateEntity createDefault() {
        return new StateEntity();
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
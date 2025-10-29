package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class StateEntity extends Entity {

    private String name;

    public StateEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public StateEntity(final UUID id) {
        super(id);
        setName(TextHelper.getDefault());
    }

    public StateEntity(final UUID id, final String name) {
        super(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
    public static GenderEntity createDefault() {
        return new GenderEntity();
    }

}

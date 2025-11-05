package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class GenderDomain extends Domain {

    private UUID id;
    private String name;

    GenderDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public GenderDomain(final UUID id) {
        super(id);
        setId(id);
        setName(TextHelper.getDefault());
    }

    public GenderDomain(final UUID id, final String name) {
        super(id);
        setId(id);
        setName(name);
    }

    protected static GenderDomain getDefaultValue() {
        return new GenderDomain();
    }

    static GenderDomain getDefaultValue(final GenderDomain gender) {
        return ObjectHelper.getDefault(gender, getDefaultValue());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
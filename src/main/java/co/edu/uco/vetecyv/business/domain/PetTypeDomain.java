package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class PetTypeDomain extends Domain {

    private String name;

    PetTypeDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public PetTypeDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setName(TextHelper.getDefault());
    }

    public PetTypeDomain(final UUID id, final String name) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setName(name);
    }

    protected static PetTypeDomain getDefaultValue() {
        return new PetTypeDomain();
    }

    static PetTypeDomain getDefaultValue(final PetTypeDomain petType) {
        return ObjectHelper.getDefault(petType, getDefaultValue());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
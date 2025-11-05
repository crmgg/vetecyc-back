package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class RaceDomain extends Domain {

    private PetTypeDomain petType;
    private String name;

    RaceDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setPetType(PetTypeDomain.getDefaultValue());
        setName(TextHelper.getDefault());
    }

    public RaceDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setPetType(PetTypeDomain.getDefaultValue());
        setName(TextHelper.getDefault());
    }

    public RaceDomain(final UUID id, final PetTypeDomain petType, final String name) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setPetType(petType);
        setName(name);
    }

    protected static RaceDomain getDefaultValue() {
        return new RaceDomain();
    }

    static RaceDomain getDefaultValue(final RaceDomain race) {
        return ObjectHelper.getDefault(race, getDefaultValue());
    }

    public PetTypeDomain getPetType() {
        return petType;
    }

    public void setPetType(final PetTypeDomain petType) {
        this.petType = ObjectHelper.getDefault(petType, PetTypeDomain.getDefaultValue());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
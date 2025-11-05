package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class SpecialityDomain extends Domain {

    private String code;
    private String name;

    SpecialityDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
    }

    public SpecialityDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
    }

    public SpecialityDomain(final UUID id, final String code, final String name) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setCode(code);
        setName(name);
    }

    protected static SpecialityDomain getDefaultValue() {
        return new SpecialityDomain();
    }

    static SpecialityDomain getDefaultValue(final SpecialityDomain speciality) {
        return ObjectHelper.getDefault(speciality, getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
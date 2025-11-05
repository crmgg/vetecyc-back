package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class SpecialityEntity {

    private UUID id;
    private String code;
    private String name;

    public SpecialityEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
    }

    public SpecialityEntity(final UUID id) {
        setId(id);
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
    }

    public SpecialityEntity(final UUID id, final String code, final String name) {
        setId(id);
        setCode(code);
        setName(name);
    }

    public static SpecialityEntity createDefault() {
        return new SpecialityEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
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
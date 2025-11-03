package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class SpecialityEntity {

    private UUID id;
    private Integer code;
    private Date dateTime;
    private String name;

    public SpecialityEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setDateTime(new Date());
    }

    public SpecialityEntity(final UUID id) {
        setId(id);
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setDateTime(new Date());
    }

    public SpecialityEntity(final UUID id, final Integer code, final String name, final Date dateTime) {
        setId(id);
        setCode(code);
        setName(name);
        setDateTime(dateTime);
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

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code == null ? Integer.valueOf(0) : code;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(final Date dateTime) {
        this.dateTime = dateTime == null ? new Date() : dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
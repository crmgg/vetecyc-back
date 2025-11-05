package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class SpecialityDTO {

    private UUID id;
    private String code;
    private Date dateTime;
    private String name;

    public SpecialityDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
    }

    public SpecialityDTO(final UUID id, String code, String name) {
        setId(id);
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
    }

    public SpecialityDTO(final UUID id, final String code, final String name, final Date dateTime) {
        setId(id);
        setCode(code);
        setName(name);
        setDateTime(dateTime);
    }

    static SpecialityDTO getDefaultValue() {
        return new SpecialityDTO();
    }

    static SpecialityDTO getDefaultValue(final SpecialityDTO speciality) {
        return ObjectHelper.getDefault(speciality, getDefaultValue());
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(final Date dateTime) {
        this.dateTime = DateHelper.getDefault(dateTime);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }
}
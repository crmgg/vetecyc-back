package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class AgendaDTO {

    private UUID id;
    private SpecialityDoctorDTO specialityDoctor;
    private String code;
    private Date dateTime;
    private Date endDateTime;

    public AgendaDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(SpecialityDoctorDTO.getDefaultValue());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDTO(final UUID id) {
        setId(id);
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(SpecialityDoctorDTO.getDefaultValue());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDTO(final UUID id, final SpecialityDoctorDTO specialityDoctor,
                     final Date dateTime, final Date endDateTime) {
        setId(id);
        setSpecialityDoctor(specialityDoctor);
        setDateTime(dateTime);
        setEndDateTime(endDateTime);
    }

    static AgendaDTO getDefaultValue() {
        return new AgendaDTO();
    }

    static AgendaDTO getDefaultValue(final AgendaDTO agenda) {
        return ObjectHelper.getDefault(agenda, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public SpecialityDoctorDTO getSpecialityDoctor() {
        return specialityDoctor;
    }

    public void setSpecialityDoctor(final SpecialityDoctorDTO specialityDoctor) {
        this.specialityDoctor = ObjectHelper.getDefault(specialityDoctor, SpecialityDoctorDTO.getDefaultValue());
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

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final Date endDateTime) {
        this.endDateTime = DateHelper.getDefault(endDateTime);
    }
}
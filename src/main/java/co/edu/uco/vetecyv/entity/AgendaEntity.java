package co.edu.uco.vetecyv.entity;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

public final class AgendaEntity {

    private UUID id;
    private SpecialityDoctorEntity specialityDoctor;
    private String code;
    private Date dateTime;
    private Date endDateTime;

    public AgendaEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(SpecialityDoctorEntity.createDefault());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaEntity(final UUID id) {
        setId(id);
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(SpecialityDoctorEntity.createDefault());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaEntity(final UUID id, final String code, final SpecialityDoctorEntity specialityDoctor,
                        final Date dateTime, final Date endDateTime) {
        setId(id);
        setCode(code);
        setSpecialityDoctor(specialityDoctor);
        setDateTime(dateTime);
        setEndDateTime(endDateTime);
    }

    public static AgendaEntity createDefault() {
        return new AgendaEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public SpecialityDoctorEntity getSpecialityDoctor() {
        return specialityDoctor;
    }

    public void setSpecialityDoctor(final SpecialityDoctorEntity specialityDoctor) {
        this.specialityDoctor = specialityDoctor == null ? SpecialityDoctorEntity.createDefault() : specialityDoctor;
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
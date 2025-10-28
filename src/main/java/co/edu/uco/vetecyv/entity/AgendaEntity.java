package co.edu.uco.vetecyv.entity;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.SpecialityDoctorEntity;

public class AgendaEntity extends Entity {

    private SpecialityDoctorEntity specialityDoctor;
    private Integer code;
    private Date dateTime;
    private Date endDateTime;

    public AgendaEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCode(Integer.valueOf(0));
        setSpecialityDoctor(SpecialityDoctorEntity.createDefault());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaEntity(final UUID id) {
        super(id);
        setCode(Integer.valueOf(0));
        setSpecialityDoctor(SpecialityDoctorEntity.createDefault());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaEntity(final UUID id, final SpecialityDoctorEntity specialityDoctor,
                        final Date dateTime, final Date endDateTime) {
        super(id);
        setSpecialityDoctor(specialityDoctor);
        setDateTime(dateTime);
        setEndDateTime(endDateTime);
    }

    public SpecialityDoctorEntity getSpecialityDoctor() {
        return specialityDoctor;
    }

    public void setSpecialityDoctor(SpecialityDoctorEntity specialityDoctor) {
        this.specialityDoctor = specialityDoctor;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
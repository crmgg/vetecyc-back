package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public class AgendaDTO extends DTO {

    private SpecialityDoctorDTO specialityDoctor;
    private Integer code;
    private Date dateTime;
    private Date endDateTime;

    public AgendaDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCode(Integer.valueOf(0));
        setSpecialityDoctor(SpecialityDoctorDTO.createDefault());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDTO(final UUID id) {
        super(id);
        setCode(Integer.valueOf(0));
        setSpecialityDoctor(SpecialityDoctorDTO.createDefault());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDTO(final UUID id, final SpecialityDoctorDTO specialityDoctor,
                        final Date dateTime, final Date endDateTime) {
        super(id);
        setSpecialityDoctor(specialityDoctor);
        setDateTime(dateTime);
        setEndDateTime(endDateTime);
    }

    public static AgendaDTO createDefault() {
        return null;
    }

    public SpecialityDoctorDTO getSpecialityDoctor() {
        return specialityDoctor;
    }

    public void setSpecialityDoctor(SpecialityDoctorDTO specialityDoctor) {
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


package co.edu.uco.vetecyv.buisness.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.vetecyv.buisness.domain.Domain;
import co.edu.uco.vetecyv.buisness.domain.SpecialityDoctorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;

public class AgendaDomain extends Domain {

    private SpecialityDoctorDomain specialityDoctor;
    private String code;
    private LocalDateTime dateTime;
    private LocalDateTime endDateTime;

    public AgendaDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(new SpecialityDoctorDomain());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDomain(final UUID id) {
        super(id);
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(new SpecialityDoctorDomain());
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDomain(final UUID id, final String code) {
        super(id);
        this.code = TextHelper.getDefaultWithTrim(code);
        setDateTime(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public SpecialityDoctorDomain getSpecialityDoctor() {
        return specialityDoctor;
    }

    public void setSpecialityDoctor(SpecialityDoctorDomain specialityDoctor) {
        this.specialityDoctor = ObjectHelper.getDefault(specialityDoctor, new SpecialityDoctorDomain());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = DateHelper.getDefault(dateTime);
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = DateHelper.getDefault(endDateTime);
    }
}

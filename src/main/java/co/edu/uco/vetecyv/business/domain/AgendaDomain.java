// java
package co.edu.uco.vetecyv.business.domain;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;

public class AgendaDomain extends Domain {

    private SpecialityDoctorDomain specialityDoctor;
    private String code;
    private Date dateTime;
    private Date endDateTime;

    public AgendaDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(new SpecialityDoctorDomain());
        setDate(new Date());
        setEndDateTime(new Date());
    }

    public AgendaDomain(final UUID id) {
        super(id);
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(new SpecialityDoctorDomain());
        setDate(new Date());
        setEndDateTime(new Date());
    }

    public AgendaDomain(final UUID id, final String code) {
        super(id);
        this.code = TextHelper.getDefaultWithTrim(code);
        setDate(new Date());
        setEndDateTime(new Date());
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

    public Date getDate() {
        return dateTime;
    }

    public void setDate(Date date) {
        this.dateTime = ObjectHelper.getDefault(date, new Date());
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = ObjectHelper.getDefault(endDateTime, new Date());
    }
}
package co.edu.uco.vetecyv.business.domain;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;

public final class AgendaDomain extends Domain {

    private SpecialityDoctorDomain specialityDoctor;
    private String code;
    private Date dateTime;
    private Date endDateTime;

    AgendaDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(SpecialityDoctorDomain.getDefaultValue());
        setDate(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setCode(TextHelper.getDefault());
        setSpecialityDoctor(SpecialityDoctorDomain.getDefaultValue());
        setDate(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AgendaDomain(final UUID id, final String code, final SpecialityDoctorDomain specialityDoctor, final Date date,
            final Date endDateTime) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setCode(code);
        setSpecialityDoctor(specialityDoctor);
        setDate(date);
        setEndDateTime(endDateTime);
    }

    protected static AgendaDomain getDefaultValue() {
        return new AgendaDomain();
    }

    static AgendaDomain getDefaultValue(final AgendaDomain agenda) {
        return ObjectHelper.getDefault(agenda, getDefaultValue());
    }

    public SpecialityDoctorDomain getSpecialityDoctor() {
        return specialityDoctor;
    }

    public void setSpecialityDoctor(final SpecialityDoctorDomain specialityDoctor) {
        this.specialityDoctor = ObjectHelper.getDefault(specialityDoctor, SpecialityDoctorDomain.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public Date getDate() {
        return dateTime;
    }

    public void setDate(final Date date) {
        this.dateTime = ObjectHelper.getDefault(date, DateHelper.getDefault());
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final Date endDateTime) {
        this.endDateTime = ObjectHelper.getDefault(endDateTime, DateHelper.getDefault());
    }
}
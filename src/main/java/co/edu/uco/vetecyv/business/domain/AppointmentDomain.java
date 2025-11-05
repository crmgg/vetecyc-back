package co.edu.uco.vetecyv.business.domain;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

public final class AppointmentDomain extends Domain {

    private AgendaDomain agenda;
    private PetDomain pet;
    private StateDomain state;
    private DetailDomain detail;
    private String code;
    private Date dateTimeStart;
    private Date endDateTime;

    public AppointmentDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAgenda(AgendaDomain.getDefaultValue());
        setPet(PetDomain.getDefaultValue());
        setState(StateDomain.getDefaultValue());
        setDetail(DetailDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTimeStart(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setAgenda(AgendaDomain.getDefaultValue());
        setPet(PetDomain.getDefaultValue());
        setState(StateDomain.getDefaultValue());
        setDetail(DetailDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTimeStart(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentDomain(final UUID id, final AgendaDomain agenda, final PetDomain pet, final StateDomain state, final String code,
                             final Date dateTimeStart, final Date endDateTime) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setAgenda(agenda);
        setPet(pet);
        setState(state);
        setDetail(DetailDomain.getDefaultValue());
        setCode(code);
        setDateTimeStart(dateTimeStart);
        setEndDateTime(endDateTime);
    }

    public static AppointmentDomain getDefaultValue() {
        return new AppointmentDomain();
    }

    public DetailDomain getDetail() {
        return detail;
    }

    public void setAgenda(final AgendaDomain agenda) {
        this.agenda = ObjectHelper.getDefault(agenda, AgendaDomain.getDefaultValue());
    }

    public AgendaDomain getAgenda() {
        return agenda;
    }

    public PetDomain getPet() {
        return pet;
    }

    public void setPet(final PetDomain pet) {
        this.pet = ObjectHelper.getDefault(pet, PetDomain.getDefaultValue());
    }

    public StateDomain getState() {
        return state;
    }

    public void setState(final StateDomain state) {
        this.state = ObjectHelper.getDefault(state, StateDomain.getDefaultValue());
    }

    public void setDetail(final DetailDomain detail) {
        this.detail = ObjectHelper.getDefault(detail, DetailDomain.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public Date getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(final Date dateTimeStart) {
        this.dateTimeStart = ObjectHelper.getDefault(dateTimeStart, DateHelper.getDefault());
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final Date endDateTime) {
        this.endDateTime = ObjectHelper.getDefault(endDateTime, DateHelper.getDefault());
    }
}
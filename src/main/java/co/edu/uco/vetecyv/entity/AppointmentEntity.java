package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class AppointmentEntity extends Entity {

    private AgendaEntity agendaEntity;
    private PetEntity petEntity;
    private StateEntity stateEntity;
    private Integer code;
    private Date dateTimeStare;
    private Date endDateTime;

    public AppointmentEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAgendaEntity(AgendaEntity.createDefault());
        setPetEntity(PetEntity.createDefault());
        setStateEntity(StateEntity.createDefault());
        setCode(Integer.valueOf(0));
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentEntity(final UUID id) {
        super(id);
        setAgenda(AgendaEntity.createDefault());
        setPet(PetEntity.createDefault());
        setState(StateEntity.createDefault());
        setCode(Integer.valueOf(0));
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentEntity(final UUID id, final AgendaEntity agenda, final PetEntity pet, final StateEntity state,
                             final Integer code, final Date dateTimeStare, final Date endDateTime) {
        super(id);
        setAgendaEntity(agenda);
        setPetEntity(pet);
        setCode(code);
        setStateEntity(state);
        setDateTimeStare(dateTimeStare);
        setEndDateTime(endDateTime);
    }

    public static AppointmentEntity createDefault() {
        return new AppointmentEntity();
    }

    public AgendaEntity getAgendaEntity() {
        return agendaEntity;
    }

    public void setAgendaEntity(AgendaEntity agendaEntity) {
        this.agendaEntity = agendaEntity;
    }

    // alias usado en constructores
    public AgendaEntity getAgenda() {
        return getAgendaEntity();
    }

    public void setAgenda(AgendaEntity agenda) {
        setAgendaEntity(agenda);
    }

    public PetEntity getPetEntity() {
        return petEntity;
    }

    public void setPetEntity(PetEntity petEntity) {
        this.petEntity = petEntity;
    }

    // alias usado en constructores
    public PetEntity getPet() {
        return getPetEntity();
    }

    public void setPet(PetEntity pet) {
        setPetEntity(pet);
    }

    public StateEntity getStateEntity() {
        return stateEntity;
    }

    public void setStateEntity(StateEntity stateEntity) {
        this.stateEntity = stateEntity;
    }

    // alias usado en constructores
    public StateEntity getState() {
        return getStateEntity();
    }

    public void setState(StateEntity state) {
        setStateEntity(state);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDateTimeStare() {
        return dateTimeStare;
    }

    public void setDateTimeStare(Date dateTimeStare) {
        this.dateTimeStare = dateTimeStare;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}

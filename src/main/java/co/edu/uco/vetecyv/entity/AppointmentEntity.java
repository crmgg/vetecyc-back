package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class AppointmentEntity {

    private UUID id;
    private AgendaEntity agendaEntity;
    private PetEntity petEntity;
    private StateEntity stateEntity;
    private String code;
    private Date dateTimeStart;
    private Date endDateTime;

    public AppointmentEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAgendaEntity(AgendaEntity.createDefault());
        setPetEntity(PetEntity.createDefault());
        setStateEntity(StateEntity.createDefault());
        setCode(TextHelper.getDefault());
        setDateTimeStart(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentEntity(final UUID id) {
        setId(id);
        setAgendaEntity(AgendaEntity.createDefault());
        setPetEntity(PetEntity.createDefault());
        setStateEntity(StateEntity.createDefault());
        setCode(TextHelper.getDefault());
        setDateTimeStart(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentEntity(final UUID id, final AgendaEntity agenda, final PetEntity pet, final StateEntity state,
                             final String code, final Date dateTimeStart, final Date endDateTime) {
        setId(id);
        setAgendaEntity(agenda);
        setPetEntity(pet);
        setStateEntity(state);
        setCode(code);
        setDateTimeStart(dateTimeStart);
        setEndDateTime(endDateTime);
    }

    public static AppointmentEntity createDefault() {
        return new AppointmentEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public AgendaEntity getAgendaEntity() {
        return agendaEntity;
    }

    public void setAgendaEntity(final AgendaEntity agendaEntity) {
        this.agendaEntity = agendaEntity == null ? AgendaEntity.createDefault() : agendaEntity;
    }

    public AgendaEntity getAgenda() {
        return getAgendaEntity();
    }

    public void setAgenda(final AgendaEntity agenda) {
        setAgendaEntity(agenda);
    }

    public PetEntity getPetEntity() {
        return petEntity;
    }

    public void setPetEntity(final PetEntity petEntity) {
        this.petEntity = petEntity == null ? PetEntity.createDefault() : petEntity;
    }

    public PetEntity getPet() {
        return getPetEntity();
    }

    public void setPet(final PetEntity pet) {
        setPetEntity(pet);
    }

    public StateEntity getStateEntity() {
        return stateEntity;
    }

    public void setStateEntity(final StateEntity stateEntity) {
        this.stateEntity = stateEntity == null ? StateEntity.createDefault() : stateEntity;
    }

    public StateEntity getState() {
        return getStateEntity();
    }

    public void setState(final StateEntity state) {
        setStateEntity(state);
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Date getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(final Date dateTimeStare) {
        this.dateTimeStart = DateHelper.getDefault(dateTimeStare);
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final Date endDateTime) {
        this.endDateTime = DateHelper.getDefault(endDateTime);
    }
}
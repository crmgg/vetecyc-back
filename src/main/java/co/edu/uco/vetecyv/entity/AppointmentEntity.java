package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

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
        setAgendaEntity(AgendaEntity.createDefault);
        setPetEntity(PetEntity.creatDefault());
        setStateEntity(StateEntity.creatDefault());
        setCode(TextHelper.getDefault());
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentEntity(final UUID id) {
        super(id);
        setAgenda(AgendaEntity.createDefault);
        setPet(PetEntity.creatDefault());
        setState(StateEntity.creatDefault());
        setCode(TextHelper.getDefault());
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentEntity(final UUID id, final AgendaEntity agenda, final PetEntity pet, final StateEntity state,
                             final Integer code, final Date dateTimeStare, final Date endDateTime) {
        super(id);
        setAgendaEntity(agenda);
        setPet(pet);
        setCode(code);
        setState(State);
        setDateTimeStare(dateTimeStare);
        setEndDateTime(endDateTime);
    }

    public AgendaEntity getAgendaEntity() {
        return agendaEntity;
    }

    public void setAgendaEntity(AgendaEntity agendaEntity) {
        this.agendaEntity = agendaEntity;
    }

    public PetEntity getPetEntity() {
        return petEntity;
    }

    public void setPetEntity


}

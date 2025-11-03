package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class AppointmentDTO {

    private UUID id;
    private AgendaDTO agenda;
    private PetDTO pet;
    private StateDTO state;
    private Integer code;
    private Date dateTimeStare;
    private Date endDateTime;

    public AppointmentDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAgenda(AgendaDTO.getDefaultValue());
        setPet(PetDTO.getDefaultValue());
        setState(StateDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentDTO(final UUID id) {
        setId(id);
        setAgenda(AgendaDTO.getDefaultValue());
        setPet(PetDTO.getDefaultValue());
        setState(StateDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentDTO(final UUID id, final AgendaDTO agenda, final PetDTO pet, final StateDTO state,
                          final Integer code, final Date dateTimeStare, final Date endDateTime) {
        setId(id);
        setAgenda(agenda);
        setPet(pet);
        setState(state);
        setCode(code);
        setDateTimeStare(dateTimeStare);
        setEndDateTime(endDateTime);
    }

    static AppointmentDTO getDefaultValue() {
        return new AppointmentDTO();
    }

    static AppointmentDTO getDefaultValue(final AppointmentDTO appointment) {
        return ObjectHelper.getDefault(appointment, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public AgendaDTO getAgenda() {
        return agenda;
    }

    public void setAgenda(final AgendaDTO agenda) {
        this.agenda = ObjectHelper.getDefault(agenda, AgendaDTO.getDefaultValue());
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(final PetDTO pet) {
        this.pet = ObjectHelper.getDefault(pet, PetDTO.getDefaultValue());
    }

    public StateDTO getState() {
        return state;
    }

    public void setState(final StateDTO state) {
        this.state = ObjectHelper.getDefault(state, StateDTO.getDefaultValue());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = ObjectHelper.getDefault(code, Integer.valueOf(0));
    }

    public Date getDateTimeStare() {
        return dateTimeStare;
    }

    public void setDateTimeStare(final Date dateTimeStare) {
        this.dateTimeStare = DateHelper.getDefault(dateTimeStare);
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final Date endDateTime) {
        this.endDateTime = DateHelper.getDefault(endDateTime);
    }
}
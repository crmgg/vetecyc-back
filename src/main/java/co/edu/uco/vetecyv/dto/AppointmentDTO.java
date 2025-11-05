package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

import java.util.Date;
import java.util.UUID;

public final class AppointmentDTO {

    private UUID id;
    private AgendaDTO agenda;
    private PetDTO pet;
    private StateDTO state;
    private String code;
    private Date dateTimeStart;
    private Date endDateTime;

    public AppointmentDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAgenda(AgendaDTO.getDefaultValue());
        setPet(PetDTO.getDefaultValue());
        setState(StateDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTimeStart(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentDTO(final UUID id) {
        setId(id);
        setAgenda(AgendaDTO.getDefaultValue());
        setPet(PetDTO.getDefaultValue());
        setState(StateDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTimeStart(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentDTO(final UUID id, final AgendaDTO agenda, final PetDTO pet, final StateDTO state,
                          final String code, final Date dateTimeStart, final Date endDateTime) {
        setId(id);
        setAgenda(agenda);
        setPet(pet);
        setState(state);
        setCode(code);
        setDateTimeStart(dateTimeStart);
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
        this.dateTimeStart = DateHelper.getDefault(dateTimeStart);
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final Date endDateTime) {
        this.endDateTime = DateHelper.getDefault(endDateTime);
    }
}
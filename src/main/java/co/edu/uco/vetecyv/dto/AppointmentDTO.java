package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.AgendaDTO;
import co.edu.uco.vetecyv.dto.PetDTO;
import co.edu.uco.vetecyv.dto.AppointmentDTO;
import co.edu.uco.vetecyv.dto.StateDTO;

import java.util.Date;
import java.util.UUID;

public class AppointmentDTO extends DTO{

    private AgendaDTO agendaDTO;
    private PetDTO petDTO;
    private StateDTO stateDTO;
    private Integer code;
    private Date dateTimeStare;
    private Date endDateTime;

    public AppointmentDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAgendaDTO(AgendaDTO.createDefault());
        setPetDTO(PetDTO.createDefault());
        setStateDTO(StateDTO.createDefault());
        setCode(Integer.valueOf(0));
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }


    public AppointmentDTO(final UUID id) {
        super(id);
        setAgenda(AgendaDTO.createDefault());
        setPet(PetDTO.createDefault());
        setState(StateDTO.createDefault());
        setCode(Integer.valueOf(0));
        setDateTimeStare(DateHelper.getDefault());
        setEndDateTime(DateHelper.getDefault());
    }

    public AppointmentDTO(final UUID id, final AgendaDTO agenda, final PetDTO pet, final StateDTO state,
                             final Integer code, final Date dateTimeStare, final Date endDateTime) {
        super(id);
        setAgendaEntity(agenda);
        setPetEntity(pet);
        setCode(code);
        setStateEntity(state);
        setDateTimeStare(dateTimeStare);
        setEndDateTime(endDateTime);
    }

    public static AppointmentDTO createDefault() {
        return new AppointmentDTO();
    }

    private void setAgendaDTO(AgendaDTO aDefault) {
    }

    public AgendaDTO getAgendaEntity() {
        return agendaDTO;
    }

    public void setAgendaEntity(AgendaDTO agendaDTO) {
        this.agendaDTO = agendaDTO;
    }

    // alias usado en constructores
    public AgendaDTO getAgenda() {
        return getAgendaEntity();
    }

    public void setAgenda(AgendaDTO agenda) {
        setAgendaEntity(agenda);
    }

    public PetDTO getPetDTO() {
        return petDTO;
    }

    public void setPetEntity(PetDTO petDTO) {
        this.petDTO = petDTO;
    }

    // alias usado en constructores
    public PetDTO getPet() {
        return getPetDTO();
    }

    public void setPet(PetDTO pet) {
        setPetDTO(pet);
    }

    private void setPetDTO(PetDTO pet) {
    }

    public StateDTO getStateEntity() {
        return stateDTO;
    }

    public void setStateEntity(StateDTO stateDTO) {
        this.stateDTO = stateDTO;
    }

    // alias usado en constructores
    public StateDTO getState() {
        return getStateDTO();
    }

    private StateDTO getStateDTO() {
        return stateDTO;
    }

    public void setState(StateDTO state) {
        setStateDTO(state);
    }

    private void setStateDTO(StateDTO state) {
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

package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.math.BigDecimal;
import java.util.UUID;

public final class ConsultationDTO {

    private UUID id;
    private AppointmentDTO appointment;
    private DetailDTO detail;
    private Integer code;
    private BigDecimal consultationPrice;

    public ConsultationDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAppointment(AppointmentDTO.getDefaultValue());
        setDetail(DetailDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationDTO(final UUID id) {
        setId(id);
        setAppointment(AppointmentDTO.getDefaultValue());
        setDetail(DetailDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationDTO(final UUID id, final AppointmentDTO appointment, final DetailDTO detail,
                           final Integer code, final BigDecimal consultationPrice) {
        setId(id);
        setAppointment(appointment);
        setDetail(detail);
        setCode(code);
        setConsultationPrice(consultationPrice);
    }

    static ConsultationDTO getDefaultValue() {
        return new ConsultationDTO();
    }

    static ConsultationDTO getDefaultValue(final ConsultationDTO consultation) {
        return ObjectHelper.getDefault(consultation, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public AppointmentDTO getAppointment() {
        return appointment;
    }

    public void setAppointment(final AppointmentDTO appointment) {
        this.appointment = ObjectHelper.getDefault(appointment, AppointmentDTO.getDefaultValue());
    }

    public DetailDTO getDetail() {
        return detail;
    }

    public void setDetail(final DetailDTO detail) {
        this.detail = ObjectHelper.getDefault(detail, DetailDTO.getDefaultValue());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = ObjectHelper.getDefault(code, Integer.valueOf(0));
    }

    public BigDecimal getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(final BigDecimal consultationPrice) {
        this.consultationPrice = ObjectHelper.getDefault(consultationPrice, BigDecimal.ZERO);
    }
}
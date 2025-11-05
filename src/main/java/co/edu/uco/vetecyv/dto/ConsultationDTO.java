package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.NumericHelper;

import java.util.UUID;

public final class ConsultationDTO {

    private UUID id;
    private AppointmentDTO appointment;
    private DetailDTO detail;
    private String code;
    private Double consultationPrice;

    public ConsultationDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAppointment(AppointmentDTO.getDefaultValue());
        setDetail(DetailDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setConsultationPrice(NumericHelper.getDefaultWithZero());
    }

    public ConsultationDTO(final UUID id) {
        setId(id);
        setAppointment(AppointmentDTO.getDefaultValue());
        setDetail(DetailDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setConsultationPrice(NumericHelper.getDefaultWithZero());
    }

    public ConsultationDTO(final UUID id, final AppointmentDTO appointment, final DetailDTO detail,
                           final String code, final Double consultationPrice) {
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

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public Double getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(final Double consultationPrice) {
        this.consultationPrice = NumericHelper.getDefaultWithZero(consultationPrice);
    }
}
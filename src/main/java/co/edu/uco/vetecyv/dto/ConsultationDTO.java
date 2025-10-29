package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.math.BigDecimal;
import java.util.UUID;

public class ConsultationDTO extends DTO{

    private AppointmentDTO appointment;
    private DetailDTO detail;
    private Integer code;
    private BigDecimal consultationPrice;

    public ConsultationDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAppointment(AppointmentDTO.createDefault());
        setDetail(DetailDTO.createDefault());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationDTO(final UUID id) {
        super(id);
        setAppointment(AppointmentDTO.createDefault());
        setDetail(DetailDTO.createDefault());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationDTO(final UUID id, final AppointmentDTO appointment, final DetailDTO detail,
                           final Integer code, final BigDecimal consultationPrice) {
        super(id);
        setAppointment(appointment);
        setDetail(detail);
        setCode(code);
        setConsultationPrice(consultationPrice);
    }

    public static ConsultationDTO createDefault() {
        return new ConsultationDTO();
    }

    public AppointmentDTO getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentDTO appointment) {
        this.appointment = appointment;
    }

    public DetailDTO getDetail() {
        return detail;
    }

    public void setDetail(DetailDTO detail) {
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BigDecimal getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(BigDecimal consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

}
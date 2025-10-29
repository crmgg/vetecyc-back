package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.math.BigDecimal;
import java.util.UUID;

public class ConsultationEntity extends Entity {

    private AppointmentEntity appointment;
    private DetailEntity detail;
    private Integer code;
    private BigDecimal consultationPrice;

    public ConsultationEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAppointment(AppointmentEntity.createDefault());
        setDetail((DetailEntity) DetailEntity.createDefault());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationEntity(final UUID id) {
        super(id);
        setAppointment(AppointmentEntity.createDefault());
        setDetail((DetailEntity) DetailEntity.createDefault());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationEntity(final UUID id, final AppointmentEntity appointment, final DetailEntity detail,
                              final Integer code, final BigDecimal consultationPrice) {
        super(id);
        setAppointment(appointment);
        setDetail(detail);
        setCode(code);
        setConsultationPrice(consultationPrice);
    }

    public static ConsultationEntity createDefault() {
        return new ConsultationEntity();
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(DetailEntity detail) {
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

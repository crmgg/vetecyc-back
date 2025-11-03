package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.math.BigDecimal;
import java.util.UUID;

public final class ConsultationEntity {

    private UUID id;
    private AppointmentEntity appointment;
    private DetailEntity detail;
    private Integer code;
    private BigDecimal consultationPrice;

    public ConsultationEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAppointment(AppointmentEntity.createDefault());
        setDetail(DetailEntity.createDefault());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationEntity(final UUID id) {
        setId(id);
        setAppointment(AppointmentEntity.createDefault());
        setDetail(DetailEntity.createDefault());
        setCode(Integer.valueOf(0));
        setConsultationPrice(BigDecimal.ZERO);
    }

    public ConsultationEntity(final UUID id, final AppointmentEntity appointment, final DetailEntity detail,
                              final Integer code, final BigDecimal consultationPrice) {
        setId(id);
        setAppointment(appointment);
        setDetail(detail);
        setCode(code);
        setConsultationPrice(consultationPrice);
    }

    public static ConsultationEntity createDefault() {
        return new ConsultationEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(final AppointmentEntity appointment) {
        this.appointment = appointment == null ? AppointmentEntity.createDefault() : appointment;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(final DetailEntity detail) {
        this.detail = detail == null ? DetailEntity.createDefault() : detail;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code == null ? Integer.valueOf(0) : code;
    }

    public BigDecimal getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(final BigDecimal consultationPrice) {
        this.consultationPrice = consultationPrice == null ? BigDecimal.ZERO : consultationPrice;
    }
}
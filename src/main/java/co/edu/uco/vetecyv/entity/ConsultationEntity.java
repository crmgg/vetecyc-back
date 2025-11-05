package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.NumericHelper;

import java.util.UUID;

public final class ConsultationEntity {

    private UUID id;
    private AppointmentEntity appointment;
    private DetailEntity detail;
    private String code;
    private Double consultationPrice;

    public ConsultationEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAppointment(AppointmentEntity.createDefault());
        setDetail(DetailEntity.createDefault());
        setCode(TextHelper.getDefault());
        setConsultationPrice(NumericHelper.getDefaultWithZero());
    }

    public ConsultationEntity(final UUID id) {
        setId(id);
        setAppointment(AppointmentEntity.createDefault());
        setDetail(DetailEntity.createDefault());
        setCode(TextHelper.getDefault());
        setConsultationPrice(NumericHelper.getDefaultWithZero());
    }

    public ConsultationEntity(final UUID id, final AppointmentEntity appointment, final DetailEntity detail,
                              final String code, final Double consultationPrice) {
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

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Double getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(final Double consultationPrice) {
        this.consultationPrice = NumericHelper.getDefaultWithZero(consultationPrice);
    }
}
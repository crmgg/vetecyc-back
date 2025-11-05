package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.NumericHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

public final class ConsultationDomain extends Domain {

    private AppointmentDomain appointment;
    private DetailDomain detail;
    private String code;
    private Double consultationPrice;

    ConsultationDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAppointment(AppointmentDomain.getDefaultValue());
        setDetail(DetailDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setConsultationPrice(NumericHelper.getDefaultWithZero());
    }

    public ConsultationDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setAppointment(AppointmentDomain.getDefaultValue());
        setDetail(DetailDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setConsultationPrice(NumericHelper.getDefaultWithZero());
    }

    public ConsultationDomain(final UUID id, final AppointmentDomain appointment, final DetailDomain detail, final String code, final Double consultationPrice) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setAppointment(appointment);
        setDetail(detail);
        setCode(code);
        setConsultationPrice(consultationPrice);
    }

    protected static ConsultationDomain getDefaultValue() {
        return new ConsultationDomain();
    }

    static ConsultationDomain getDefaultValue(final ConsultationDomain consultation) {
        return ObjectHelper.getDefault(consultation, getDefaultValue());
    }

    public AppointmentDomain getAppointment() {
        return appointment;
    }

    public void setAppointment(final AppointmentDomain appointment) {
        this.appointment = ObjectHelper.getDefault(appointment, AppointmentDomain.getDefaultValue());
    }

    public DetailDomain getDetail() {
        return detail;
    }

    public void setDetail(final DetailDomain detail) {
        this.detail = ObjectHelper.getDefault(detail, DetailDomain.getDefaultValue());
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
        this.consultationPrice = ObjectHelper.getDefault(consultationPrice, NumericHelper.getDefaultWithZero());
    }
}
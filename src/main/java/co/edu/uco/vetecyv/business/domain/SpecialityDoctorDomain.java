package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class SpecialityDoctorDomain extends Domain {

    private DoctorDomain doctor;
    private SpecialityDomain speciality;
    private String code;

    SpecialityDoctorDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setDoctor(DoctorDomain.getDefaultValue());
        setSpeciality(SpecialityDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
    }

    public SpecialityDoctorDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setDoctor(DoctorDomain.getDefaultValue());
        setSpeciality(SpecialityDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
    }

    public SpecialityDoctorDomain(final UUID id, final DoctorDomain doctor, final SpecialityDomain speciality, final String code) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setDoctor(doctor);
        setSpeciality(speciality);
        setCode(code);
    }

    protected static SpecialityDoctorDomain getDefaultValue() {
        return new SpecialityDoctorDomain();
    }

    static SpecialityDoctorDomain getDefaultValue(final SpecialityDoctorDomain entity) {
        return ObjectHelper.getDefault(entity, getDefaultValue());
    }

    public DoctorDomain getDoctor() {
        return doctor;
    }

    public void setDoctor(final DoctorDomain doctor) {
        this.doctor = ObjectHelper.getDefault(doctor, DoctorDomain.getDefaultValue());
    }

    public SpecialityDomain getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final SpecialityDomain speciality) {
        this.speciality = ObjectHelper.getDefault(speciality, SpecialityDomain.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }
}
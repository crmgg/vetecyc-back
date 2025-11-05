package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class SpecialityDoctorEntity {

    private UUID id;
    private DoctorEntity doctor;
    private SpecialityEntity speciality;
    private String code;

    public SpecialityDoctorEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setDoctor(DoctorEntity.createDefault());
        setSpeciality(SpecialityEntity.createDefault());
        setCode(TextHelper.getDefault());
    }

    public SpecialityDoctorEntity(final UUID id) {
        setId(id);
        setDoctor(DoctorEntity.createDefault());
        setSpeciality(SpecialityEntity.createDefault());
        setCode(TextHelper.getDefault());
    }

    public SpecialityDoctorEntity(final UUID id, final DoctorEntity doctor,
                                  final SpecialityEntity speciality, final String code) {
        setId(id);
        setDoctor(doctor);
        setSpeciality(speciality);
        setCode(code);
    }

    public static SpecialityDoctorEntity createDefault() {
        return new SpecialityDoctorEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(final DoctorEntity doctor) {
        this.doctor = doctor == null ? DoctorEntity.createDefault() : doctor;
    }

    public SpecialityEntity getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final SpecialityEntity speciality) {
        this.speciality = speciality == null ? SpecialityEntity.createDefault() : speciality;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }
}
package co.edu.uco.vetecyv.entity;


import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class SpecialityDoctorEntity extends Entity {

    private DoctorEntity doctor;
    private SpecialityEntity speciality;
    private Integer code;


    public SpecialityDoctorEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setDoctor(DoctorEntity.createDefault());
        setSpeciality(SpecialityEntity.createDefault());
        setCode(Integer.valueOf(0));

    }

    public SpecialityDoctorEntity(final UUID id) {
        super(id);
        setDoctor(DoctorEntity.createDefault());
        setSpeciality(SpecialityEntity.createDefault());
        setCode(Integer.valueOf(0));
    }

    public SpecialityDoctorEntity(final UUID id , final DoctorEntity doctor,
                                  final SpecialityEntity speciality, final Integer code) {
        super(id);
        setDoctor(doctor);
        setSpeciality(speciality);
        setCode(code);
    }

    public static SpecialityDoctorEntity createDefault() {
        return new SpecialityDoctorEntity();
    }


    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(final DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public SpecialityEntity getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final SpecialityEntity speciality) {
        this.speciality = speciality;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

}

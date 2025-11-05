package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

import java.util.UUID;

public final class SpecialityDoctorDTO {

    private UUID id;
    private DoctorDTO doctor;
    private SpecialityDTO speciality;
    private String code;

    public SpecialityDoctorDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setDoctor(DoctorDTO.getDefaultValue());
        setSpeciality(SpecialityDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
    }

    public SpecialityDoctorDTO(final UUID id) {
        setId(id);
        setDoctor(DoctorDTO.getDefaultValue());
        setSpeciality(SpecialityDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
    }

    public SpecialityDoctorDTO(final UUID id, final DoctorDTO doctor,
                                final SpecialityDTO speciality, final String code) {
        setId(id);
        setDoctor(doctor);
        setSpeciality(speciality);
        setCode(code);
    }

    static SpecialityDoctorDTO getDefaultValue() {
        return new SpecialityDoctorDTO();
    }

    static SpecialityDoctorDTO getDefaultValue(final SpecialityDoctorDTO specialityDoctor) {
        return ObjectHelper.getDefault(specialityDoctor, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(final DoctorDTO doctor) {
        this.doctor = ObjectHelper.getDefault(doctor, DoctorDTO.getDefaultValue());
    }

    public SpecialityDTO getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final SpecialityDTO speciality) {
        this.speciality = ObjectHelper.getDefault(speciality, SpecialityDTO.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }
}
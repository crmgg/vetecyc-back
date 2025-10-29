package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.DoctorDTO;
import co.edu.uco.vetecyv.dto.SpecialityDoctorDTO;
import co.edu.uco.vetecyv.dto.SpecialityDTO;

import java.util.UUID;

public class SpecialityDoctorDTO extends DTO {

    private DoctorDTO doctor;
    private SpecialityDTO speciality;
    private Integer code;


    public SpecialityDoctorDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setDoctor(DoctorDTO.createDefault());
        setSpeciality(SpecialityDTO.createDefault());
        setCode(Integer.valueOf(0));

    }

    public SpecialityDoctorDTO(final UUID id) {
        super(id);
        setDoctor(DoctorDTO.createDefault());
        setSpeciality(SpecialityDTO.createDefault());
        setCode(Integer.valueOf(0));
    }

    public SpecialityDoctorDTO(final UUID id , final DoctorDTO doctor,
                                  final SpecialityDTO speciality, final Integer code) {
        super(id);
        setDoctor(doctor);
        setSpeciality(speciality);
        setCode(code);
    }

    public static SpecialityDoctorDTO createDefault() {
        return new SpecialityDoctorDTO();
    }


    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(final DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public SpecialityDTO getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final SpecialityDTO speciality) {
        this.speciality = speciality;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

}

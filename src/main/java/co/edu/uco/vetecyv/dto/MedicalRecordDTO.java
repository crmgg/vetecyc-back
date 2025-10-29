package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.MedicalRecordEntity;
import co.edu.uco.vetecyv.dto.PetDTO;

import java.util.Date;
import java.util.UUID;

public class MedicalRecordDTO extends DTO {

    private PetDTO pet;
    private Integer code;
    private Date creationDate;

    public MedicalRecordDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setPet(PetDTO.createDefault());
        setCode(Integer.valueOf(0));
        setCreationDate(new Date());
    }

    public MedicalRecordDTO(final UUID id){
        super(id);
        setPet(PetDTO.createDefault());
        setCode(Integer.valueOf(0));
        setCreationDate(new Date());
    }

    public MedicalRecordDTO(final UUID id, final PetDTO pet,
                               final Integer code, final Date creationDate) {
        super(id);
        setPet(pet);
        setCode(code);
        setCreationDate(creationDate);
    }

    public static MedicalRecordDTO createDefault() {
        return new MedicalRecordDTO();
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}

package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.PetDTOAssembler.getPetDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.MedicalRecordDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.MedicalRecordDTO;

public final class MedicalRecordDTOAssembler implements DTOAssembler<MedicalRecordDTO, MedicalRecordDomain> {

    private static final DTOAssembler<MedicalRecordDTO, MedicalRecordDomain> instance = new MedicalRecordDTOAssembler();

    private MedicalRecordDTOAssembler() {
    }

    public static DTOAssembler<MedicalRecordDTO, MedicalRecordDomain> getMedicalRecordDTOAssembler() {
        return instance;
    }

    @Override
    public MedicalRecordDTO toDTO(final MedicalRecordDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new MedicalRecordDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var petDto = getPetDTOAssembler().toDTO(domainTmp.getPet());

        return new MedicalRecordDTO(
                domainTmp.getId(),
                petDto,
                domainTmp.getCode(),
                domainTmp.getCreationDate()
        );
    }

    @Override
    public MedicalRecordDomain toDomain(final MedicalRecordDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new MedicalRecordDTO());
        var petDomain = getPetDTOAssembler().toDomain(dtoTmp.getPet());

        return new MedicalRecordDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                petDomain,
                dtoTmp.getCode(),
                dtoTmp.getCreationDate()
        );
    }

    @Override
    public List<MedicalRecordDTO> toDTO(final List<MedicalRecordDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<MedicalRecordDomain>());
        var dtoList = new ArrayList<MedicalRecordDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
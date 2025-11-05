package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.PetTypeDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.PetTypeDTO;

public final class PetTypeDTOAssembler implements DTOAssembler<PetTypeDTO, PetTypeDomain> {

    private static final DTOAssembler<PetTypeDTO, PetTypeDomain> instance = new PetTypeDTOAssembler();

    private PetTypeDTOAssembler() {
    }

    public static DTOAssembler<PetTypeDTO, PetTypeDomain> getPetTypeDTOAssembler() {
        return instance;
    }

    @Override
    public PetTypeDTO toDTO(final PetTypeDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new PetTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new PetTypeDTO(
                domainTmp.getId(),
                domainTmp.getName()
        );
    }

    @Override
    public PetTypeDomain toDomain(final PetTypeDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new PetTypeDTO());
        return new PetTypeDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                dtoTmp.getName()
        );
    }

    @Override
    public List<PetTypeDTO> toDTO(final List<PetTypeDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<PetTypeDomain>());
        var dtoList = new ArrayList<PetTypeDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.SpecialityDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.SpecialityDTO;

public final class SpecialityDTOAssembler implements DTOAssembler<SpecialityDTO, SpecialityDomain> {

    private static final DTOAssembler<SpecialityDTO, SpecialityDomain> instance = new SpecialityDTOAssembler();

    private SpecialityDTOAssembler() {
    }

    public static DTOAssembler<SpecialityDTO, SpecialityDomain> getSpecialityDTOAssembler() {
        return instance;
    }

    @Override
    public SpecialityDTO toDTO(final SpecialityDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new SpecialityDomain(UUIDHelper.getUUIDHelper().getDefault()));

        return new SpecialityDTO(
                domainTmp.getId(),
                domainTmp.getCode(),
                domainTmp.getName()
        );
    }

    @Override
    public SpecialityDomain toDomain(final SpecialityDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new SpecialityDTO());
        return new SpecialityDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                dtoTmp.getName(),
                dtoTmp.getCode()
        );
    }

    @Override
    public List<SpecialityDTO> toDTO(final List<SpecialityDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<SpecialityDomain>());
        var dtoList = new ArrayList<SpecialityDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
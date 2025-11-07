package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.GenderDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.GenderDTO;

public final class GenderDTOAssembler implements DTOAssembler<GenderDTO, GenderDomain> {

    private static final DTOAssembler<GenderDTO, GenderDomain> instance = new GenderDTOAssembler();

    private GenderDTOAssembler() {
    }

    public static DTOAssembler<GenderDTO, GenderDomain> getGenderDTOAssembler() {
        return instance;
    }

    @Override
    public GenderDTO toDTO(final GenderDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new GenderDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new GenderDTO(
                domainTmp.getId(),
                domainTmp.getName()
        );
    }

    @Override
    public GenderDomain toDomain(final GenderDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new GenderDTO());
        return new GenderDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                dtoTmp.getName()
        );
    }

    @Override
    public List<GenderDTO> toDTO(final List<GenderDomain> domainList) {
        var genderDtoList = new ArrayList<GenderDTO>();

        for (var genderDomain : domainList) {
            genderDtoList.add(toDTO(genderDomain));
        }
        return genderDtoList;
    }
}
package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.PetTypeDTOAssembler.getPetTypeDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.RaceDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.RaceDTO;

public final class RaceDTOAssembler implements DTOAssembler<RaceDTO, RaceDomain> {

    private static final DTOAssembler<RaceDTO, RaceDomain> instance = new RaceDTOAssembler();

    private RaceDTOAssembler() {
    }

    public static DTOAssembler<RaceDTO, RaceDomain> getRaceDTOAssembler() {
        return instance;
    }

    @Override
    public RaceDTO toDTO(final RaceDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new RaceDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var petTypeDto = getPetTypeDTOAssembler().toDTO(domainTmp.getPetType());

        return new RaceDTO(
                domainTmp.getId(),
                petTypeDto,
                domainTmp.getName()
        );
    }

    @Override
    public RaceDomain toDomain(final RaceDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new RaceDTO());
        var petTypeDomain = getPetTypeDTOAssembler().toDomain(dtoTmp.getPetType());

        return new RaceDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                petTypeDomain,
                dtoTmp.getName()
        );
    }

    @Override
    public List<RaceDTO> toDTO(final List<RaceDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<RaceDomain>());
        var dtoList = new ArrayList<RaceDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
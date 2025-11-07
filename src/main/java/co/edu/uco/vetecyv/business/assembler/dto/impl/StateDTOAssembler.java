package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.StateDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.StateDTO;

public final class StateDTOAssembler implements DTOAssembler<StateDTO, StateDomain> {

    private static final DTOAssembler<StateDTO, StateDomain> instance = new StateDTOAssembler();

    private StateDTOAssembler() {
    }

    public static DTOAssembler<StateDTO, StateDomain> getStateDTOAssembler() {
        return instance;
    }

    @Override
    public StateDTO toDTO(final StateDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new StateDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new StateDTO(
                domainTmp.getId(),
                domainTmp.getName()
        );
    }

    @Override
    public StateDomain toDomain(final StateDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new StateDTO());
        return new StateDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                dtoTmp.getName()
        );
    }

    @Override
    public List<StateDTO> toDTO(final List<StateDomain> domainList) {
        var stateDtoList = new ArrayList<StateDTO>();

        for (var stateDomain : domainList) {
            stateDtoList.add(toDTO(stateDomain));
        }
        return stateDtoList;
    }
}
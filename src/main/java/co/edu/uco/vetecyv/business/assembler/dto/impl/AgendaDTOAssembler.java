package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.AgendaDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.AgendaDTO;

public final class AgendaDTOAssembler implements DTOAssembler<AgendaDTO, AgendaDomain> {

    private static final DTOAssembler<AgendaDTO, AgendaDomain> instance = new AgendaDTOAssembler();

    private AgendaDTOAssembler() {
    }

    public static DTOAssembler<AgendaDTO, AgendaDomain> getAgendaDTOAssembler() {
        return instance;
    }

    @Override
    public AgendaDTO toDTO(final AgendaDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new AgendaDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new AgendaDTO(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                domainTmp.getCode(),
                SpecialityDoctorDTOAssembler.getSpecialityDoctorDTOAssembler().toDTO(domainTmp.getSpecialityDoctor()),
                domainTmp.getDate(),
                domainTmp.getEndDateTime()
        );
    }

    @Override
    public AgendaDomain toDomain(final AgendaDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new AgendaDTO());
        return new AgendaDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                dtoTmp.getCode(),
                SpecialityDoctorDTOAssembler.getSpecialityDoctorDTOAssembler().toDomain(dtoTmp.getSpecialityDoctor()),
                dtoTmp.getDateTime(),
                dtoTmp.getEndDateTime()
        );
    }

    @Override
    public List<AgendaDTO> toDTO(final List<AgendaDomain> domainList) {
        var dtoList = new ArrayList<AgendaDTO>();
        for (var domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
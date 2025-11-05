package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.AgendaDTOAssembler.getAgendaDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.PetDTOAssembler.getPetDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.StateDTOAssembler.getStateDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.AppointmentDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.AppointmentDTO;

public final class AppointmentDTOAssembler implements DTOAssembler<AppointmentDTO, AppointmentDomain> {

    private static final DTOAssembler<AppointmentDTO, AppointmentDomain> instance = new AppointmentDTOAssembler();

    private AppointmentDTOAssembler() {
    }

    public static DTOAssembler<AppointmentDTO, AppointmentDomain> getAppointmentDTOAssembler() {
        return instance;
    }

    @Override
    public AppointmentDTO toDTO(final AppointmentDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new AppointmentDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var agendaDto = getAgendaDTOAssembler().toDTO(domainTmp.getAgenda());
        var petDto = getPetDTOAssembler().toDTO(domainTmp.getPet());
        var stateDto = getStateDTOAssembler().toDTO(domainTmp.getState());

        return new AppointmentDTO(
                domainTmp.getId(),
                agendaDto,
                petDto,
                stateDto,
                domainTmp.getCode(),
                domainTmp.getDateTimeStart(),
                domainTmp.getEndDateTime()
        );
    }

    @Override
    public AppointmentDomain toDomain(final AppointmentDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new AppointmentDTO());
        var agendaDomain = getAgendaDTOAssembler().toDomain(dtoTmp.getAgenda());
        var petDomain = getPetDTOAssembler().toDomain(dtoTmp.getPet());
        var stateDomain = getStateDTOAssembler().toDomain(dtoTmp.getState());

        return new AppointmentDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                agendaDomain,
                petDomain,
                stateDomain,
                dtoTmp.getCode(),
                dtoTmp.getDateTimeStart(),
                dtoTmp.getEndDateTime()
        );
    }

    @Override
    public List<AppointmentDTO> toDTO(final List<AppointmentDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<AppointmentDomain>());
        var dtoList = new ArrayList<AppointmentDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
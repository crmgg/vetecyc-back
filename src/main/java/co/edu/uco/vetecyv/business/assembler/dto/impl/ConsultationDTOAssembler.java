package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.AppointmentDTOAssembler.getAppointmentDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.DetailDTOAssembler.getDetailDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.ConsultationDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.ConsultationDTO;

public final class ConsultationDTOAssembler implements DTOAssembler<ConsultationDTO, ConsultationDomain> {

    private static final DTOAssembler<ConsultationDTO, ConsultationDomain> instance = new ConsultationDTOAssembler();

    private ConsultationDTOAssembler() {
    }

    public static DTOAssembler<ConsultationDTO, ConsultationDomain> getConsultationDTOAssembler() {
        return instance;
    }

    @Override
    public ConsultationDTO toDTO(final ConsultationDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new ConsultationDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var appointmentDto = getAppointmentDTOAssembler().toDTO(domainTmp.getAppointment());
        var detailDto = getDetailDTOAssembler().toDTO(domainTmp.getDetail());

        return new ConsultationDTO(
                domainTmp.getId(),
                appointmentDto,
                detailDto,
                domainTmp.getCode(),
                domainTmp.getConsultationPrice()
        );
    }

    @Override
    public ConsultationDomain toDomain(final ConsultationDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new ConsultationDTO());
        var appointmentDomain = getAppointmentDTOAssembler().toDomain(dtoTmp.getAppointment());
        var detailDomain = getDetailDTOAssembler().toDomain(dtoTmp.getDetail());

        return new ConsultationDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                appointmentDomain,
                detailDomain,
                dtoTmp.getCode(),
                dtoTmp.getConsultationPrice()
        );
    }

    @Override
    public List<ConsultationDTO> toDTO(final List<ConsultationDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<ConsultationDomain>());
        var dtoList = new ArrayList<ConsultationDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
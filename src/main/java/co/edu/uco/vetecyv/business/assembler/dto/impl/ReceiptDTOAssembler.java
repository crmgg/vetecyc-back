package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.AdministratorDTOAssembler.getAdministratorDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.ConsultationDTOAssembler.getConsultationDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.ReceiptDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.ReceiptDTO;

public final class ReceiptDTOAssembler implements DTOAssembler<ReceiptDTO, ReceiptDomain> {

    private static final DTOAssembler<ReceiptDTO, ReceiptDomain> instance = new ReceiptDTOAssembler();

    private ReceiptDTOAssembler() {
    }

    public static DTOAssembler<ReceiptDTO, ReceiptDomain> getReceiptDTOAssembler() {
        return instance;
    }

    @Override
    public ReceiptDTO toDTO(final ReceiptDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new ReceiptDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var administratorDto = getAdministratorDTOAssembler().toDTO(domainTmp.getAdministrator());
        var consultationDto = getConsultationDTOAssembler().toDTO(domainTmp.getConsultation());

        return new ReceiptDTO(
                domainTmp.getId(),
                administratorDto,
                consultationDto,
                domainTmp.getCode(),
                domainTmp.getDateTime(),
                domainTmp.getTotalCoast()
        );
    }

    @Override
    public ReceiptDomain toDomain(final ReceiptDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new ReceiptDTO());
        var administratorDomain = getAdministratorDTOAssembler().toDomain(dtoTmp.getAdministrator());
        var consultationDomain = getConsultationDTOAssembler().toDomain(dtoTmp.getConsultation());

        return new ReceiptDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                administratorDomain,
                consultationDomain,
                dtoTmp.getCode(),
                dtoTmp.getDateTime(),
                dtoTmp.getTotalCoast()
        );
    }

    @Override
    public List<ReceiptDTO> toDTO(final List<ReceiptDomain> domainList) {
        var receiptDtoList = new ArrayList<ReceiptDTO>();

        for (var receiptDomain : domainList) {
            receiptDtoList.add(toDTO(receiptDomain));
        }
        return receiptDtoList;
    }
}
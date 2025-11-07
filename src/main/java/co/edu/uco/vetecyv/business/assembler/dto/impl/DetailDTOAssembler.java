package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.ConsultationDTOAssembler.getConsultationDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.MedicalRecordDTOAssembler.getMedicalRecordDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.DetailDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.DetailDTO;

public final class DetailDTOAssembler implements DTOAssembler<DetailDTO, DetailDomain> {

    private static final DTOAssembler<DetailDTO, DetailDomain> instance = new DetailDTOAssembler();

    private DetailDTOAssembler() {
    }

    public static DTOAssembler<DetailDTO, DetailDomain> getDetailDTOAssembler() {
        return instance;
    }

    @Override
    public DetailDTO toDTO(final DetailDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new DetailDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var consultationDto = getConsultationDTOAssembler().toDTO(domainTmp.getConsultation());
        var medicalRecordDto = getMedicalRecordDTOAssembler().toDTO(domainTmp.getMedicalRecord());

        return new DetailDTO(
                domainTmp.getId(),
                consultationDto,
                medicalRecordDto,
                domainTmp.getCode(),
                domainTmp.getAnnotation()
        );
    }

    @Override
    public DetailDomain toDomain(final DetailDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new DetailDTO());
        var consultationDomain = getConsultationDTOAssembler().toDomain(dtoTmp.getConsultation());
        var medicalRecordDomain = getMedicalRecordDTOAssembler().toDomain(dtoTmp.getMedicalRecord());

        return new DetailDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                consultationDomain,
                medicalRecordDomain,
                dtoTmp.getCode(),
                dtoTmp.getAnnotations()
        );
    }

    @Override
    public List<DetailDTO> toDTO(final List<DetailDomain> domainList) {
        var detaildtoList = new ArrayList<DetailDTO>();

        for (var detailDomain : domainList) {
            detaildtoList.add(toDTO(detailDomain));
        }
        return detaildtoList;
    }
}
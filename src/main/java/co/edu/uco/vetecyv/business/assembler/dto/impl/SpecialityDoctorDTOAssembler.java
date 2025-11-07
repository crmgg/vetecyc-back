package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.DoctorDTOAssembler.getDoctorDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.SpecialityDTOAssembler.getSpecialityDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.SpecialityDoctorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.SpecialityDoctorDTO;

public final class SpecialityDoctorDTOAssembler implements DTOAssembler<SpecialityDoctorDTO, SpecialityDoctorDomain> {

    private static final DTOAssembler<SpecialityDoctorDTO, SpecialityDoctorDomain> instance = new SpecialityDoctorDTOAssembler();

    private SpecialityDoctorDTOAssembler() {
    }

    public static DTOAssembler<SpecialityDoctorDTO, SpecialityDoctorDomain> getSpecialityDoctorDTOAssembler() {
        return instance;
    }

    @Override
    public SpecialityDoctorDTO toDTO(final SpecialityDoctorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new SpecialityDoctorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var doctorDto = getDoctorDTOAssembler().toDTO(domainTmp.getDoctor());
        var specialityDto = getSpecialityDTOAssembler().toDTO(domainTmp.getSpeciality());

        return new SpecialityDoctorDTO(
                domainTmp.getId(),
                doctorDto,
                specialityDto,
                domainTmp.getCode()
        );
    }

    @Override
    public SpecialityDoctorDomain toDomain(final SpecialityDoctorDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new SpecialityDoctorDTO());
        var doctorDomain = getDoctorDTOAssembler().toDomain(dtoTmp.getDoctor());
        var specialityDomain = getSpecialityDTOAssembler().toDomain(dtoTmp.getSpeciality());

        return new SpecialityDoctorDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                doctorDomain,
                specialityDomain,
                dtoTmp.getCode()
        );
    }

    @Override
    public List<SpecialityDoctorDTO> toDTO(final List<SpecialityDoctorDomain> domainList) {
        var specialityDoctorDtoList = new ArrayList<SpecialityDoctorDTO>();

        for (var specialityDoctorDomain : domainList) {
            specialityDoctorDtoList.add(toDTO(specialityDoctorDomain));
        }
        return specialityDoctorDtoList;
    }
}
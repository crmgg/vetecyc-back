package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.vetecyv.business.assembler.dto.impl.TutorDTOAssembler.getTutorDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.GenderDTOAssembler.getGenderDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.dto.impl.RaceDTOAssembler.getRaceDTOAssembler;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.PetDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.PetDTO;

public final class PetDTOAssembler implements DTOAssembler<PetDTO, PetDomain> {

    private static final DTOAssembler<PetDTO, PetDomain> instance = new PetDTOAssembler();

    private PetDTOAssembler() {
    }

    public static DTOAssembler<PetDTO, PetDomain> getPetDTOAssembler() {
        return instance;
    }

    @Override
    public PetDTO toDTO(final PetDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new PetDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var tutorDto = getTutorDTOAssembler().toDTO(domainTmp.getTutor());
        var genderDto = getGenderDTOAssembler().toDTO(domainTmp.getGender());
        var raceDto = getRaceDTOAssembler().toDTO(domainTmp.getRace());

        return new PetDTO(
                domainTmp.getId(),
                tutorDto,
                genderDto,
                raceDto,
                domainTmp.getCode(),
                domainTmp.getName(),
                domainTmp.getSize(),
                domainTmp.getDateBirth(),
                domainTmp.isState(),
                domainTmp.getColor()
        );
    }

    @Override
    public PetDomain toDomain(final PetDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new PetDTO());
        var tutorDomain = getTutorDTOAssembler().toDomain(dtoTmp.getTutor());
        var genderDomain = getGenderDTOAssembler().toDomain(dtoTmp.getGender());
        var raceDomain = getRaceDTOAssembler().toDomain(dtoTmp.getRace());

        return new PetDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                tutorDomain,
                genderDomain,
                raceDomain,
                dtoTmp.getCode(),
                dtoTmp.getName(),
                dtoTmp.getSize(),
                dtoTmp.getDateBirth(),
                dtoTmp.getState(),
                dtoTmp.getColor()
        );
    }

    @Override
    public List<PetDTO> toDTO(final List<PetDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<PetDomain>());
        var dtoList = new ArrayList<PetDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
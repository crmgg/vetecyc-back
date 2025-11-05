package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.TutorDTO;

public final class TutorDTOAssembler implements DTOAssembler<TutorDTO, TutorDomain> {

    private static final DTOAssembler<TutorDTO, TutorDomain> instance = new TutorDTOAssembler();

    private TutorDTOAssembler() {
    }

    public static DTOAssembler<TutorDTO, TutorDomain> getTutorDTOAssembler() {
        return instance;
    }

    @Override
    public TutorDTO toDTO(final TutorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new TutorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new TutorDTO(
                domainTmp.getIdentityDocument(),
                domainTmp.getName(),
                domainTmp.getFirstLastName(),
                domainTmp.getSecondLastName(),
                domainTmp.getEmail(),
                domainTmp.getPhoneNumber(),
                domainTmp.getPassword(),
                domainTmp.isEmailConfirmation(),
                domainTmp.isPhoneConfirmation(),
                domainTmp.isAccountState()
        );
    }

    @Override
    public TutorDomain toDomain(final TutorDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new TutorDTO());
        return new TutorDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()), dtoTmp.getIdentityDocument(),
                dtoTmp.getName(),
                dtoTmp.getFirstLastName(),
                dtoTmp.getSecondLastName(),
                dtoTmp.getEmail(),
                dtoTmp.getPhoneNumber(),
                dtoTmp.getPassword(),
                dtoTmp.isEmailConfirmed(),
                dtoTmp.isPhoneConfirmed(),
                dtoTmp.isAccountState()
        );
    }

    @Override
    public List<TutorDTO> toDTO(final List<TutorDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<TutorDomain>());
        var dtoList = new ArrayList<TutorDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
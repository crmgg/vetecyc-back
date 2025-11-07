package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.AdministratorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.AdministratorDTO;

public final class AdministratorDTOAssembler implements DTOAssembler<AdministratorDTO, AdministratorDomain> {

    private static final DTOAssembler<AdministratorDTO, AdministratorDomain> instance = new AdministratorDTOAssembler();

    private AdministratorDTOAssembler() {
    }

    public static DTOAssembler<AdministratorDTO, AdministratorDomain> getAdministratorDTOAssembler() {
        return instance;
    }

    @Override
    public AdministratorDTO toDTO(final AdministratorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new AdministratorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new AdministratorDTO(
                domainTmp.getId(),
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
    public AdministratorDomain toDomain(final AdministratorDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new AdministratorDTO());
        return new AdministratorDomain(
                dtoTmp.getId(),
                dtoTmp.getIdentityDocument(),
                dtoTmp.getName(),
                dtoTmp.getFirstLastName(),
                dtoTmp.getSecondLastName(),
                dtoTmp.getEmail(),
                dtoTmp.getPhoneNumber(),
                dtoTmp.getPassword(),
                dtoTmp.isEmailConfirmation(),
                dtoTmp.isPhoneConfirmation(),
                dtoTmp.isAccountState()
        );
    }

    @Override
    public List<AdministratorDTO> toDTO(final List<AdministratorDomain> domainList) {
        var administratordtoList = new ArrayList<AdministratorDTO>();

        for (var administratorDomain : domainList) {
            administratordtoList.add(toDTO(administratorDomain));
        }
        return administratordtoList;
    }
}
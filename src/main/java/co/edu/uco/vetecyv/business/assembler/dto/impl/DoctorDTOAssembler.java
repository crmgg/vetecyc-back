package co.edu.uco.vetecyv.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.dto.DTOAssembler;
import co.edu.uco.vetecyv.business.domain.DoctorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.DoctorDTO;

public final class DoctorDTOAssembler implements DTOAssembler<DoctorDTO, DoctorDomain> {

    private static final DTOAssembler<DoctorDTO, DoctorDomain> instance = new DoctorDTOAssembler();

    private DoctorDTOAssembler() {
    }

    public static DTOAssembler<DoctorDTO, DoctorDomain> getDoctorDTOAssembler() {
        return instance;
    }

    @Override
    public DoctorDTO toDTO(final DoctorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new DoctorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new DoctorDTO(
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
    public DoctorDomain toDomain(final DoctorDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, new DoctorDTO());
        return new DoctorDomain(
                UUIDHelper.getUUIDHelper().getDefault(dtoTmp.getId()),
                dtoTmp.getIdentityDocument(),
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
    public List<DoctorDTO> toDTO(final List<DoctorDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<DoctorDomain>());
        var dtoList = new ArrayList<DoctorDTO>();

        for (var domain : safeList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}
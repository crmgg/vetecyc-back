package co.edu.uco.vetecyv.business.assembler.entity.impl;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.AdministratorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.AdministratorEntity;

import java.util.ArrayList;
import java.util.List;

public final class AdministratorEntityAssembler implements EntityAssembler<AdministratorEntity, AdministratorDomain> {

    private static final EntityAssembler<AdministratorEntity, AdministratorDomain> instance = new AdministratorEntityAssembler();

    private AdministratorEntityAssembler() {

    }

    public static EntityAssembler<AdministratorEntity, AdministratorDomain> getAdministratorEntityAssembler() {
        return instance;
    }

    @Override
    public AdministratorEntity toEntity(final AdministratorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new AdministratorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new AdministratorEntity(
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
    public AdministratorDomain toDomain(final AdministratorEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new AdministratorEntity());
        return new AdministratorDomain(
                entityTmp.getId(),
                entityTmp.getIdentityDocument(),
                entityTmp.getName(),
                entityTmp.getFirstLastName(),
                entityTmp.getSecondLastName(),
                entityTmp.getEmail(),
                entityTmp.getPhoneNumber(),
                entityTmp.getPassword(),
                entityTmp.isEmailConfirmation(),
                entityTmp.isPhoneConfirmation(),
                entityTmp.isAccountState()
        );
    }

    @Override
    public List<AdministratorDomain> toDomainList(final List<AdministratorEntity> entityList) {
        var administratorDomainList = new ArrayList<AdministratorDomain>();

        for (var administratorEntity : entityList) {
            administratorDomainList.add(toDomain(administratorEntity));
        }
        return administratorDomainList;
    }
}
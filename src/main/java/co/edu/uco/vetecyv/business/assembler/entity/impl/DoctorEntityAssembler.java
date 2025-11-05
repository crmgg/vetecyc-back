package co.edu.uco.vetecyv.business.assembler.entity.impl;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.DoctorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.DoctorEntity;

import java.util.ArrayList;
import java.util.List;

public final class DoctorEntityAssembler implements EntityAssembler<DoctorEntity, DoctorDomain> {

    private static final EntityAssembler<DoctorEntity, DoctorDomain> instance = new DoctorEntityAssembler();

    private DoctorEntityAssembler() {
    }

    public static EntityAssembler<DoctorEntity, DoctorDomain> getDoctorEntityAssembler() {
        return instance;
    }

    @Override
    public DoctorEntity toEntity(final DoctorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new DoctorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new DoctorEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
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
    public DoctorDomain toDomain(final DoctorEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new DoctorEntity());
        return new DoctorDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
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
    public List<DoctorEntity> toEntityList(final List<DoctorDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<DoctorDomain>());
        var entityList = new ArrayList<DoctorEntity>();

        for (var domain : safeList) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }

    @Override
    public List<DoctorDomain> toDomainList(final List<DoctorEntity> entityList) {
        var safeList = ObjectHelper.getDefault(entityList, new ArrayList<DoctorEntity>());
        var domainList = new ArrayList<DoctorDomain>();

        for (var entity : safeList) {
            domainList.add(toDomain(entity));
        }
        return domainList;
    }
}
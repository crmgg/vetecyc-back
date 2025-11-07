package co.edu.uco.vetecyv.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.TutorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.TutorEntity;

public final class TutorEntityAssembler implements EntityAssembler<TutorEntity, TutorDomain> {

    private static final EntityAssembler<TutorEntity, TutorDomain> instance = new TutorEntityAssembler();

    private TutorEntityAssembler() {
    }

    public static EntityAssembler<TutorEntity, TutorDomain> getTutorEntityAssembler() {
        return instance;
    }

    @Override
    public TutorEntity toEntity(final TutorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new TutorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new TutorEntity(
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
    public TutorDomain toDomain(final TutorEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new TutorEntity());
        return new TutorDomain(
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
    public List<TutorDomain> toDomainList(final List<TutorEntity> entityList) {
        var tutorDomainList = new ArrayList<TutorDomain>();

        for (var tutorEntity : entityList) {
            tutorDomainList.add(toDomain(tutorEntity));
        }
        return tutorDomainList;
    }
}
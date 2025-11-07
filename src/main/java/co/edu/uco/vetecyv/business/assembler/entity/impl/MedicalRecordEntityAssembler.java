package co.edu.uco.vetecyv.business.assembler.entity.impl;

import static co.edu.uco.vetecyv.business.assembler.entity.impl.PetEntityAssembler.getPetEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.MedicalRecordDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.MedicalRecordEntity;

public final class MedicalRecordEntityAssembler implements EntityAssembler<MedicalRecordEntity, MedicalRecordDomain> {

    private static final EntityAssembler<MedicalRecordEntity, MedicalRecordDomain> instance = new MedicalRecordEntityAssembler();

    private MedicalRecordEntityAssembler() {
    }

    public static EntityAssembler<MedicalRecordEntity, MedicalRecordDomain> getMedicalRecordEntityAssembler() {
        return instance;
    }

    @Override
    public MedicalRecordEntity toEntity(final MedicalRecordDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new MedicalRecordDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var petEntity = getPetEntityAssembler().toEntity(domainTmp.getPet());

        return new MedicalRecordEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                petEntity,
                domainTmp.getCode(),
                domainTmp.getCreationDate()
        );
    }

    @Override
    public MedicalRecordDomain toDomain(final MedicalRecordEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new MedicalRecordEntity());
        var petDomain = getPetEntityAssembler().toDomain(entityTmp.getPet());

        return new MedicalRecordDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                petDomain,
                entityTmp.getCode(),
                entityTmp.getCreationDate()
        );
    }

    @Override
    public List<MedicalRecordDomain> toDomainList(final List<MedicalRecordEntity> entityList) {
        var medicalRecordDomainList = new ArrayList<MedicalRecordDomain>();

        for (var medicalRecordEntity : entityList) {
            medicalRecordDomainList.add(toDomain(medicalRecordEntity));
        }
        return medicalRecordDomainList;
    }
}
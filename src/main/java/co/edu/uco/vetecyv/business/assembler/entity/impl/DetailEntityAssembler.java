package co.edu.uco.vetecyv.business.assembler.entity.impl;

import static co.edu.uco.vetecyv.business.assembler.entity.impl.ConsultationEntityAssembler.getConsultationEntityAssembler;
import static co.edu.uco.vetecyv.business.assembler.entity.impl.MedicalRecordEntityAssembler.getMedicalRecordEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.DetailDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.DetailEntity;

public final class DetailEntityAssembler implements EntityAssembler<DetailEntity, DetailDomain> {

    private static final EntityAssembler<DetailEntity, DetailDomain> instance = new DetailEntityAssembler();

    private DetailEntityAssembler() {
    }

    public static EntityAssembler<DetailEntity, DetailDomain> getDetailEntityAssembler() {
        return instance;
    }

    @Override
    public DetailEntity toEntity(final DetailDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new DetailDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var consultationEntity = getConsultationEntityAssembler().toEntity(domainTmp.getConsultation());
        var medicalRecordEntity = getMedicalRecordEntityAssembler().toEntity(domainTmp.getMedicalRecord());

        return new DetailEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                consultationEntity,
                medicalRecordEntity,
                domainTmp.getCode(),
                domainTmp.getAnnotation()
        );
    }

    @Override
    public DetailDomain toDomain(final DetailEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new DetailEntity());
        var consultationDomain = getConsultationEntityAssembler().toDomain(entityTmp.getConsultation());
        var medicalRecordDomain = getMedicalRecordEntityAssembler().toDomain(entityTmp.getMedicalRecord());

        return new DetailDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                consultationDomain,
                medicalRecordDomain,
                entityTmp.getCode(),
                entityTmp.getAnnotations()
        );
    }

    @Override
    public List<DetailEntity> toEntityList(final List<DetailDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<DetailDomain>());
        var entityList = new ArrayList<DetailEntity>();

        for (var domain : safeList) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }

    @Override
    public List<DetailDomain> toDomainList(final List<DetailEntity> entityList) {
        var safeList = ObjectHelper.getDefault(entityList, new ArrayList<DetailEntity>());
        var domainList = new ArrayList<DetailDomain>();

        for (var entity : safeList) {
            domainList.add(toDomain(entity));
        }
        return domainList;
    }
}
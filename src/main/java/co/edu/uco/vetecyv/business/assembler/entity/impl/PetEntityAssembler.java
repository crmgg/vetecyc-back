package co.edu.uco.vetecyv.business.assembler.entity.impl;

import static co.edu.uco.vetecyv.business.assembler.entity.impl.GenderEntityAssembler.getGenderEntityAssembler;
import static co.edu.uco.vetecyv.business.assembler.entity.impl.RaceEntityAssembler.getRaceEntityAssembler;
import static co.edu.uco.vetecyv.business.assembler.entity.impl.TutorEntityAssembler.getTutorDTOAssembler;
import static co.edu.uco.vetecyv.business.assembler.entity.impl.TutorEntityAssembler.getTutorEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.PetDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.PetEntity;

public final class PetEntityAssembler implements EntityAssembler<PetEntity, PetDomain> {

    private static final EntityAssembler<PetEntity, PetDomain> instance = new PetEntityAssembler();

    private PetEntityAssembler() {
    }

    public static EntityAssembler<PetEntity, PetDomain> getPetEntityAssembler() {
        return instance;
    }

    @Override
    public PetEntity toEntity(final PetDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new PetDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var tutorEntity = getTutorDTOAssembler().toEntity(domainTmp.getTutor());
        var genderEntity = getGenderEntityAssembler().toEntity(domainTmp.getGender());
        var raceEntity = getRaceEntityAssembler().toEntity(domainTmp.getRace());

        return new PetEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                tutorEntity,
                genderEntity,
                raceEntity,
                domainTmp.getCode(),
                domainTmp.getName(),
                domainTmp.getSize(),
                domainTmp.getDateBirth(),
                domainTmp.isState(),
                domainTmp.getColor()
        );
    }

    @Override
    public PetDomain toDomain(final PetEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new PetEntity());
        var tutorDomain = getTutorDTOAssembler().toDomain(entityTmp.getTutor());
        var genderDomain = getGenderEntityAssembler().toDomain(entityTmp.getGender());
        var raceDomain = getRaceEntityAssembler().toDomain(entityTmp.getRace());

        return new PetDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                tutorDomain,
                genderDomain,
                raceDomain,
                entityTmp.getCode(),
                entityTmp.getName(),
                entityTmp.getSize(),
                entityTmp.getDateBirth(),
                entityTmp.getState(),
                entityTmp.getColor()
        );
    }

    @Override
    public List<PetEntity> toEntityList(final List<PetDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<PetDomain>());
        var entityList = new ArrayList<PetEntity>();

        for (var domain : safeList) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }

    @Override
    public List<PetDomain> toDomainList(final List<PetEntity> entityList) {
        var safeList = ObjectHelper.getDefault(entityList, new ArrayList<PetEntity>());
        var domainList = new ArrayList<PetDomain>();

        for (var entity : safeList) {
            domainList.add(toDomain(entity));
        }
        return domainList;
    }
}
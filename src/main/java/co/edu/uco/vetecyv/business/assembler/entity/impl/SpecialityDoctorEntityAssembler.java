package co.edu.uco.vetecyv.business.assembler.entity.impl;

import static co.edu.uco.vetecyv.business.assembler.entity.impl.DoctorEntityAssembler.getDoctorEntityAssembler;
import static co.edu.uco.vetecyv.business.assembler.entity.impl.SpecialityEntityAssembler.getSpecialityEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.SpecialityDoctorDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.SpecialityDoctorEntity;

public final class SpecialityDoctorEntityAssembler implements EntityAssembler<SpecialityDoctorEntity, SpecialityDoctorDomain> {

    private static final EntityAssembler<SpecialityDoctorEntity, SpecialityDoctorDomain> instance = new SpecialityDoctorEntityAssembler();

    private SpecialityDoctorEntityAssembler() {
    }

    public static EntityAssembler<SpecialityDoctorEntity, SpecialityDoctorDomain> getSpecialityDoctorEntityAssembler() {
        return instance;
    }

    @Override
    public SpecialityDoctorEntity toEntity(final SpecialityDoctorDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new SpecialityDoctorDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var doctorEntity = getDoctorEntityAssembler().toEntity(domainTmp.getDoctor());
        var specialityEntity = getSpecialityEntityAssembler().toEntity(domainTmp.getSpeciality());

        return new SpecialityDoctorEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                doctorEntity,
                specialityEntity,
                domainTmp.getCode()
        );
    }

    @Override
    public SpecialityDoctorDomain toDomain(final SpecialityDoctorEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new SpecialityDoctorEntity());
        var doctorDomain = getDoctorEntityAssembler().toDomain(entityTmp.getDoctor());
        var specialityDomain = getSpecialityEntityAssembler().toDomain(entityTmp.getSpeciality());

        return new SpecialityDoctorDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                doctorDomain,
                specialityDomain,
                entityTmp.getCode()
        );
    }

    @Override
    public List<SpecialityDoctorDomain> toDomainList(final List<SpecialityDoctorEntity> entityList) {
        var specialityDoctorDomainList = new ArrayList<SpecialityDoctorDomain>();

        for (var specialityDoctorEntity : entityList) {
            specialityDoctorDomainList.add(toDomain(specialityDoctorEntity));
        }
        return specialityDoctorDomainList;
    }
}
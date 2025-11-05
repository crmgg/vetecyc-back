  package co.edu.uco.vetecyv.business.assembler.entity.impl;

  import static co.edu.uco.vetecyv.business.assembler.entity.impl.AgendaEntityAssembler.getAgendaEntityAssembler;
  import static co.edu.uco.vetecyv.business.assembler.entity.impl.PetEntityAssembler.getPetEntityAssembler;
  import static co.edu.uco.vetecyv.business.assembler.entity.impl.StateEntityAssembler.getStateEntityAssembler;

  import java.util.ArrayList;
  import java.util.List;

  import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
  import co.edu.uco.vetecyv.business.domain.AppointmentDomain;
  import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
  import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
  import co.edu.uco.vetecyv.entity.AppointmentEntity;

  public final class AppointmentEntityAssembler implements EntityAssembler<AppointmentEntity, AppointmentDomain> {

      private static final EntityAssembler<AppointmentEntity, AppointmentDomain> instance = new AppointmentEntityAssembler();

      private AppointmentEntityAssembler() {
      }

      public static EntityAssembler<AppointmentEntity, AppointmentDomain> getAppointmentEntityAssembler() {
          return instance;
      }

      @Override
      public AppointmentEntity toEntity(final AppointmentDomain domain) {
          var domainTmp = ObjectHelper.getDefault(domain, new AppointmentDomain(UUIDHelper.getUUIDHelper().getDefault()));
          var agendaEntity = getAgendaEntityAssembler().toEntity(domainTmp.getAgenda());
          var petEntity = getPetEntityAssembler().toEntity(domainTmp.getPet());
          var stateEntity = getStateEntityAssembler().toEntity(domainTmp.getState());

          return new AppointmentEntity(
                  UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                  agendaEntity,
                  petEntity,
                  stateEntity,
                  domainTmp.getCode(),
                  domainTmp.getDateTimeStart(),
                  domainTmp.getEndDateTime()
          );
      }

      @Override
      public AppointmentDomain toDomain(final AppointmentEntity entity) {
          var entityTmp = ObjectHelper.getDefault(entity, new AppointmentEntity());
          var agendaDomain = getAgendaEntityAssembler().toDomain(entityTmp.getAgenda());
          var petDomain = getPetEntityAssembler().toDomain(entityTmp.getPet());
          var stateDomain = getStateEntityAssembler().toDomain(entityTmp.getState());

          return new AppointmentDomain(
                  UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                  agendaDomain,
                  petDomain,
                  stateDomain,
                  entityTmp.getCode(),
                  entityTmp.getDateTimeStart(),
                  entityTmp.getEndDateTime()
          );
      }

      @Override
      public List<AppointmentEntity> toEntityList(final List<AppointmentDomain> domainList) {
          var safeList = ObjectHelper.getDefault(domainList, new ArrayList<AppointmentDomain>());
          var entityList = new ArrayList<AppointmentEntity>();

          for (var domain : safeList) {
              entityList.add(toEntity(domain));
          }
          return entityList;
      }

      @Override
      public List<AppointmentDomain> toDomainList(final List<AppointmentEntity> entityList) {
          var safeList = ObjectHelper.getDefault(entityList, new ArrayList<AppointmentEntity>());
          var domainList = new ArrayList<AppointmentDomain>();

          for (var entity : safeList) {
              domainList.add(toDomain(entity));
          }
          return domainList;
      }
  }
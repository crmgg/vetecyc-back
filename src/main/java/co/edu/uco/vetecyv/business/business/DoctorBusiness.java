package co.edu.uco.vetecyv.business.business;

import co.edu.uco.vetecyv.business.domain.DoctorDomain;

import java.util.List;
import java.util.UUID;


public interface DoctorBusiness {

    void registerNewTutorInformation (DoctorDomain doctorDomain);

    void dropTutorInformation (UUID id);

    void updateTutorInformation (UUID id, DoctorDomain doctorDomain);

    List<DoctorDomain> findAllDoctors ();

    List <DoctorDomain> finDoctorsByFilter (DoctorDomain doctorFilters);

    DoctorDomain findSpecificDoctor(UUID id);

    void confirmMobileNumber(UUID id, int confirmationCode);

    void confirmEmail (UUID id, int confirmationCode);

    void accountState (UUID id, int accountStateCode);

    void sendMobileNumberConfirmation (UUID id);

    void sendEmailConfirmation (UUID id);

    void sendAccountState(UUID id);

}
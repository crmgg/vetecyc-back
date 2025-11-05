package co.edu.uco.vetecyv.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.business.domain.DoctorDomain;

public interface DoctorBusiness {

    void registerNewDoctorInformation(DoctorDomain doctorDomain);

    void dropDoctorInformation(UUID id);

    void updateDoctorInformation(UUID id, DoctorDomain doctorDomain);

    List<DoctorDomain> findAllDoctors();

    List<DoctorDomain> findDoctorsByFilter(DoctorDomain doctorFilters);

    DoctorDomain findDoctorById(UUID id);

    void confirmMobileNumber(UUID id, int confirmationCode);

    void confirmEmail(UUID id, int confirmationCode);

    void accountState(UUID id, int accountStateCode);

    void sendMobileNumberConfirmation(UUID id);

    void sendEmailConfirmation(UUID id);

    void sendAccountState(UUID id);
}
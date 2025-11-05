package co.edu.uco.vetecyv.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.business.domain.TutorDomain;

public interface TutorBusiness {

    void registerNewTutorInformation(TutorDomain tutorDomain);

    void dropTutorInformation(UUID id);

    void updateTutorInformation(UUID id, TutorDomain tutorDomain);

    List<TutorDomain> findAllTutors();

    List<TutorDomain> findTutorsByFilter(TutorDomain tutorFilters);

    TutorDomain findTutorById(UUID id);

    void confirmMobileNumber(UUID id, int confirmationCode);

    void confirmEmail(UUID id, int confirmationCode);

    void accountState(UUID id, int accountStateCode);

}
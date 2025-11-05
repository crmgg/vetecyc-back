package co.edu.uco.vetecyv.business.facade;

import co.edu.uco.vetecyv.dto.DoctorDTO;

import java.util.List;
import java.util.UUID;

public interface DoctorFacade {

    void registerNewDoctorInformation(DoctorDTO doctorDTO);

    void dropDoctorInformation(UUID id);

    void updateDoctorInformation(UUID id, DoctorDTO doctorDTO);

    List<DoctorDTO> findAllDoctors();

    List<DoctorDTO> findDoctorsByFilter(DoctorDTO filter);

    DoctorDTO findDoctorById(UUID id);

    void confirmPhoneNumber(UUID id, int confirmationCode);

    void confirmEmail(UUID id, int confirmationCode);

    void confirmAccountStatus(UUID id, int confirmationCode);

    void sendPhoneNumberConfirmationCode(UUID id);

    void sendEmailConfirmationCode(UUID id);

    void sendAccountStatusConfirmationCode(UUID id);

}

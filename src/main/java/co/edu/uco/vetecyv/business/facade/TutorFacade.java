package co.edu.uco.vetecyv.business.facade;

    import co.edu.uco.vetecyv.dto.TutorDTO;

    import java.util.List;
    import java.util.UUID;

    public interface TutorFacade {

        void registerNewInformation(TutorDTO tutorDTO);

        void dropTutorInformation(UUID id);

        void updateTutorInformation(UUID id);

        List<TutorDTO> findAllTutors();

        List<TutorDTO> findTutorsByFilter(TutorDTO filter);

        TutorDTO findTutorById(UUID id);

        void confirmPhoneNumber(UUID id, int confirmationCode);

        void confirmEmail(UUID id, int confirmationCode);

        void confirmAccountStatus(UUID id, int confirmationCode);

        void sendPhoneNumberConfirmationCode(UUID id);

        void sendEmailConfirmationCode(UUID id);

        void sendAccountStatusConfirmationCode(UUID id);

    }
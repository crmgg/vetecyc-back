package co.edu.uco.vetecyv.data.dao.factory;

import java.sql.Connection;

import co.edu.uco.vetecyv.data.dao.entity.*;
import co.edu.uco.vetecyv.entity.*;

public abstract class DAOFactory {

    protected Connection connection;

    protected FactoryEnum factory = FactoryEnum.POSTGRESQL;

    public static DAOFactory getFactory (){return null;}

    public abstract AdministratorDAO getAdministratorDAO();
    public abstract AgendaDAO getAgendaDAO();
    public abstract AppointmentDAO getAppointmentDAO();
    public abstract ConsultationDAO getConsultationDAO();
    public abstract DetailDAO getDetailDAO();
    public abstract DoctorDAO getDoctorDAO();
    public abstract GenderDAO getGenderDAO();
    public abstract MedicalRecordDAO getMedicalRecordDAO();
    public abstract PetDAO getPetDAO();
    public abstract PetTypeDAO getPetTypeDAO();
    public abstract RaceDAO getRaceDAO();
    public abstract ReceiptDAO getReceiptDAO();
    public abstract SpecialityDAO getSpecialityDAO();
    public abstract SpecialityDoctorDAO getSpecialityDoctorDAO();
    public abstract StateDAO getStateDAO();
    public abstract TutorDAO getTutorDAO();

    protected abstract void openConnection();

    protected final void initTransaction(){}

    protected final void commitTransaction(){}

    protected final void rollbackTransaction(){}

    protected final void closeConnection(){}



}

package example.dao;


import example.entity.Doctor;
import example.entity.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.print.Doc;
import java.util.List;

public class PatientDao {
    private SessionFactory sessionFactory;

    public PatientDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void save(Patient patient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(patient);
        transaction.commit();
        session.close();
    }
    public List<Patient> getAllPatients() {
        Session session = sessionFactory.openSession();
        List<Patient> patients = session.createQuery("from Patient", Patient.class).list();
        session.close();
        return patients;
    }


    public Patient getPatientById(int id) {
        Session session = sessionFactory.openSession();
        Patient patient = session.get(Patient.class, id);
        session.close();
        return patient;
    }


    public void updatePatient(Patient patient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(patient);
        transaction.commit();
        session.close();
    }

    public void deletePatient(Patient patient){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(patient);
        transaction.commit();
        session.close();
    }


}

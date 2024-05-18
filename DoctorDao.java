package example.dao;


import example.entity.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DoctorDao {
    private SessionFactory sessionFactory;

    public DoctorDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void save(Doctor doctor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(doctor);
        transaction.commit();
        session.close();
    }

    public List<Doctor> getAllDoctors() {
        Session session = sessionFactory.openSession();
        List<Doctor> doctors = session.createQuery("from Doctor", Doctor.class).list();
        session.close();
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        Session session = sessionFactory.openSession();
        Doctor doctor = session.get(Doctor.class, id);
        session.close();
        return doctor;
    }

    public void updateDoctor(Doctor doctor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(doctor);
        transaction.commit();
        session.close();
    }

    public void deleteDoctor(Doctor doctor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(doctor);
        transaction.commit();
        session.close();
    }
}
package example;

import example.dao.DoctorDao;
import example.entity.Doctor;
import example.entity.Patient;
import example.dao.PatientDao;
import example.utility.HibernateUtil;
import org.hibernate.SessionFactory;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Initialize Hibernate session factory
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Create a PatientDao instance
    private static PatientDao patientDao = new PatientDao();

    private static DoctorDao doctorDao = new DoctorDao();
    // Create a Scanner object to take user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        while (true) {
            System.out.println("Hospital Management System: ");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    managePatients();
                    break;
                case 2:
                    manageDoctors();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void managePatients() {
        System.out.println("Manage Patients");
        System.out.println("1. Add Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. View Patient By ID");
        System.out.println("4. Update Patient");
        System.out.println("5. Delete Patient");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addPatient();
                break;
            case 2:
                viewAllPatients();
                break;
            case 3:
                viewPatientById();
                break;
            case 4:
                updatePatient();
                break;
            case 5:
                deletePatient();
                break;
            case 6:
                System.out.println("Exiting...");
                sessionFactory.close();
                return;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    private static void addPatient() {
        System.out.println("Enter patient details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Contact: ");
        String contact = scanner.nextLine();

        // Create and save a new patient
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setContact(contact);
        patientDao.save(patient);
        System.out.println("Patient added successfully.");
    }

    private static void viewAllPatients() {
        List<Patient> patients = patientDao.getAllPatients();
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private static void viewPatientById() {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Patient patient = patientDao.getPatientById(id);
        if (patient != null) {
            System.out.println(patient);
        } else {
            System.out.println("Patient not found with ID: " + id);
        }
    }

    private static void updatePatient() {
        System.out.print("Enter patient ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Patient patient = patientDao.getPatientById(id);
        if (patient != null) {
            System.out.println("Enter new details for patient with ID " + id + ":");
            System.out.print("Enter new name (current: " + patient.getName() + "): ");
            String name = scanner.nextLine();
            System.out.print("Enter new age (current: " + patient.getAge() + "): ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter new gender (current: " + patient.getGender() + "): ");
            String gender = scanner.nextLine();
            System.out.print("Enter new contact (current: " + patient.getContact() + "): ");
            String contact = scanner.nextLine();

            patient.setName(name);
            patient.setAge(age);
            patient.setGender(gender);
            patient.setContact(contact);

            patientDao.updatePatient(patient);
            System.out.println("Patient details updated successfully.");
        } else {
            System.out.println("Patient not found with ID: " + id);
        }
    }

    private static void deletePatient() {
        System.out.print("Enter patient ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Patient patient = patientDao.getPatientById(id);
        if (patient != null) {
            patientDao.deletePatient(patient);
            System.out.println("Patient with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Patient not found with ID: " + id);
        }
    }

    private static void manageDoctors() {
        while (true) {
            System.out.println("Manage Doctors");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. View Doctor By ID");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    viewAllDoctors();
                    break;
                case 3:
                    viewDoctorById();
                    break;
                case 4:
                    updateDoctor();
                    break;
                case 5:
                    deleteDoctor();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter doctor specialization: ");
        String specialization = scanner.nextLine();

        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialization(specialization);

        doctorDao.save(doctor);
        System.out.println("Doctor added successfully.");
    }

    private static void viewAllDoctors() {
        List<Doctor> doctors = doctorDao.getAllDoctors();
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    private static void viewDoctorById() {
        System.out.print("Enter doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Doctor doctor = doctorDao.getDoctorById(id);
        if (doctor != null) {
            System.out.println(doctor);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void updateDoctor() {
        System.out.print("Enter doctor ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Doctor doctor = doctorDao.getDoctorById(id);
        if (doctor != null) {
            System.out.print("Enter new name (current: " + doctor.getName() + "): ");
            String name = scanner.nextLine();
            System.out.print("Enter new specialization (current: " + doctor.getSpecialization() + "): ");
            String specialization = scanner.nextLine();

            doctor.setName(name);
            doctor.setSpecialization(specialization);

            doctorDao.updateDoctor(doctor);
            System.out.println("Doctor updated successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void deleteDoctor() {
        System.out.print("Enter doctor ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Doctor doctor = doctorDao.getDoctorById(id);
        if (doctor != null) {
            doctorDao.deleteDoctor(doctor);
            System.out.println("Doctor deleted successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

}
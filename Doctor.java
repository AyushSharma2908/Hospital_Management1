package example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name = "specialization")
    private String specialization;


    public Doctor(){
    }

    public Doctor(String name,String specialization){
        this.name=name;
        this.specialization=specialization;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }


    @Override
    public String toString() {
        return "Doctor [id=" + id + ", name=" + name + ", specialization=" + specialization + "]";
    }
}

package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name="driverInformation")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DriverInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surName;
    @Column(name="tckmlkNo")
    private String tckmlkNo;
    @Column(name="yas")
    private int yas;
    @Column(name="telNo")
    private String telNo;
    @Column(name="Eposta")
    private String email;


   @ManyToOne
   @JoinColumn(name="car_id")
    private Car car;









}

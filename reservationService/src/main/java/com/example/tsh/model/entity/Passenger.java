package com.example.tsh.model.entity;

import com.example.tsh.util.validator.Email;
import com.example.tsh.util.validator.Name;
import com.example.tsh.util.validator.PhoneNumber;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.validation.constraints.Positive;

@Entity
public class Passenger extends BaseEntity{
    @Name
    @Column(nullable = false)
    private String firstName;
    @Name
    @Column
    private String middleName;
    @Name
    @Column( nullable = false)
    private String lastName;
    @Positive
    @Column(nullable = false)
    private Integer age;
    @Column( nullable = false, unique = true)
    @PhoneNumber
    private String phoneNumber;
    @Column(unique = true)
    @Email
    private String email;



    public Passenger(String firstName, String middleName, String lastName, Integer age, String phoneNumber, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Passenger() {

    }

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

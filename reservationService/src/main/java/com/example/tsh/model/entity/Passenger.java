package com.example.tsh.model.entity;

import com.example.tsh.util.validator.Email;
import com.example.tsh.util.validator.Name;

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
    private String phoneNumber;
    @Column(unique = true)
    @Email
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

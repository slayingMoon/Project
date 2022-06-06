package com.tsh.clientManager.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.tsh.clientManager.model.enums.ClientStatus;

@Entity
public class Client extends BaseEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private Integer age;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @Email
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Card> cards;
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Sale> purchases;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable=false)
    private ClientStatus status;

    public Integer getAge() {
        return age;
    }

	public List<Card> getCards() {
        return cards;
    }

	public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Sale> getPurchases() {
        return purchases;
    }

    public ClientStatus getStatus() {
		return status;
	}

    public Client setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Client setCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public Client setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Client setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Client setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public Client setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Client setPurchases(List<Sale> purchases) {
        this.purchases = purchases;
        return this;
    }

    public Client setStatus(ClientStatus status) {
		this.status = status;
		return this;
	}
}

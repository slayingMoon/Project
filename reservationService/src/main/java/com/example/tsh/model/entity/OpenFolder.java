package com.example.tsh.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OpenFolder extends BaseEntity{
    @OneToOne(cascade = { CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(nullable = false)
    private Direction direction;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}

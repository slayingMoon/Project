package com.example.tsh.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(columnList = "ticket_no_id"))
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
    @Column(nullable = false)
    private LocalDateTime reservationCreationDate;

    @OneToOne
    @JoinColumn(nullable = false)
    private TicketNo ticketNo;
    public LocalDateTime getReservationCreationDate() {
        return reservationCreationDate;
    }

    public void setReservationCreationDate(LocalDateTime reservationCreationDate) {
        this.reservationCreationDate = reservationCreationDate;
    }

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

    public TicketNo getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(TicketNo ticketNo) {
        this.ticketNo = ticketNo;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

}

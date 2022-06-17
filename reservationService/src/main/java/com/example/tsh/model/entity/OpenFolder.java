package com.example.tsh.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(columnList = "ticket_no_id"))
public class OpenFolder extends BaseEntity{
    @OneToOne(cascade = { CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(nullable = false)
    private Direction direction;

    @OneToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private Passenger passenger;
    @Column(nullable = false)
    private LocalDateTime expirationDate;
    @Column(nullable = false)
    private LocalDateTime reservationCreationDate;

    @OneToOne
    @JoinColumn(nullable = false)
    private TicketNumber ticketNo;
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

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public TicketNumber getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(TicketNumber ticketNo) {
        this.ticketNo = ticketNo;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }


}

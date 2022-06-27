package com.tsh.frantishex.reservationService.model.entity;
import com.tsh.frantishex.tripService.domain.entity.BaseEntity;
import com.tsh.frantishex.tripService.domain.entity.Transition;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseTripp extends BaseEntity {

    private String description;


    private LocalTime departureTime;

    @OneToMany(targetEntity = Transition.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private List<Transsition> transitions;

    public List<Transsition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transsition> transitions) {
        this.transitions = transitions;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
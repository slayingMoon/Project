package com.example.tsh.domain.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseTrip extends BaseEntity {

    private String description;

    private LocalTime departureTime;

    @OneToMany(targetEntity = Transition.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    private List<Transition> transitions;

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}

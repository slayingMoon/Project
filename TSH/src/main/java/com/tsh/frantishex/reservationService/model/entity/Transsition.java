package com.tsh.frantishex.reservationService.model.entity;

import com.tsh.frantishex.tripService.domain.entity.BaseEntity;
import com.tsh.frantishex.tripService.domain.entity.TransitionOptions;
import com.tsh.frantishex.tripService.domain.entity.TripCity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transsition extends BaseEntity {

    @ManyToOne(targetEntity = TripCity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private ReservationCity tripCity;

    private Integer travelDuration;

    public Transsition(ReservationCity tripCity, Integer travelDuration) {
        this.tripCity = tripCity;
        this.travelDuration = travelDuration;
    }

    public Transsition() {
    }

    public ReservationCity getTripCity() {
        return tripCity;
    }




    @ManyToMany(targetEntity = TransitionOptions.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "transition_options_mapping",
            joinColumns = @JoinColumn(
                    name = "transition_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "options_id",
                    referencedColumnName = "id"
            )
    )

    List<TransitionOptions> transitionOptions;





    public Integer getTravelDuration() {
        return travelDuration;
    }




}
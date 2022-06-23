package com.tsh.frantishex.tripService.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transition extends BaseEntity {

    @ManyToOne(targetEntity = TripCity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private TripCity tripCity;

    private Integer travelDuration;

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

    public List<TransitionOptions> getTransitionOptions() {
        return transitionOptions;
    }

    public TripCity getCity() {
        return tripCity;
    }


}

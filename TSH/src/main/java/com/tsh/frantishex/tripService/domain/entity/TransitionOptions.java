package com.tsh.frantishex.tripService.domain.entity;
import com.tsh.frantishex.tripService.enumeration.TransitionProperty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class TransitionOptions extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TransitionProperty transitionProperty;

    public TransitionProperty getTransitionProperty() {
        return transitionProperty;
    }
}

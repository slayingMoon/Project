package com.example.tsh.domain.entity;

import com.example.tsh.enumeration.TransitionProperties;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class TransitionOptions extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TransitionProperties transitionProperties;

    public TransitionProperties getTransitionProperties() {
        return transitionProperties;
    }
}

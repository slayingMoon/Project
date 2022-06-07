package com.example.tsh.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
public abstract class BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BaseEntity() {
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        return Optional.ofNullable(o).map(Object::hashCode)

                .filter(hc -> hc == hashCode()).isPresent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass().getName(), "$", id);
    }
}
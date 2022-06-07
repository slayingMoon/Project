package com.example.tsh.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OpenFolder extends BaseEntity{
    @OneToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(nullable = false)
    private Direction direction;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDateTime expirationDate;


}

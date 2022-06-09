package com.example.tsh.model.entity;

import com.example.tsh.util.StringPrefixedSequenceNumGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketNo  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @GenericGenerator(
            name = "ticket_seq",
            strategy = "com.example.tsh.util.StringPrefixedSequenceNumGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceNumGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceNumGenerator.VALUE_PREFIX_PARAMETER, value = "C_"),
                    @Parameter(name = StringPrefixedSequenceNumGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

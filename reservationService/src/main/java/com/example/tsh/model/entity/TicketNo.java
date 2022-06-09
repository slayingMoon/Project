package com.example.tsh.model.entity;

import com.example.tsh.util.StringPrefixedSequenceNumGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class TicketNo extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @GenericGenerator(
            name = "ticket_seq",
            strategy = "org.thoughts.on.java.generators.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceNumGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceNumGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
                    @Parameter(name = StringPrefixedSequenceNumGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String ticketNo;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

}

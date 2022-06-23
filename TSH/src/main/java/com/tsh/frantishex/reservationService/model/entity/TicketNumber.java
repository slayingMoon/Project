package com.tsh.frantishex.reservationService.model.entity;

import com.tsh.frantishex.reservationService.util.StringPrefixedSequenceNumGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TicketNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @GenericGenerator(
            name = "ticket_seq",
            strategy = "com.tsh.frantishex.reservationService.util.StringPrefixedSequenceNumGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceNumGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceNumGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),
                    @Parameter(name = StringPrefixedSequenceNumGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketNumber ticketNo = (TicketNumber) o;
        return Objects.equals(id, ticketNo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

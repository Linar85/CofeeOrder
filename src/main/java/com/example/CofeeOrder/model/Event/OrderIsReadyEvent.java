package com.example.CofeeOrder.model.Event;

import com.example.CofeeOrder.model.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(EventType.Values.BEEN_READING)
public class OrderIsReadyEvent extends Event {

    @Column(name = "eventtype")
    private String eventType = EventType.Values.BEEN_READING;
}

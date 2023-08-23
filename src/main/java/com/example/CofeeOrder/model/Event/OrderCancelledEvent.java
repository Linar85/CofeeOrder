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
@DiscriminatorValue(EventType.Values.CANCELED)
public class OrderCancelledEvent extends Event {

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "eventtype")
    private String eventType = EventType.Values.CANCELED;

}

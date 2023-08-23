package com.example.CofeeOrder.model.Event;

import com.example.CofeeOrder.model.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(EventType.Values.REGISTERED)
public class OrderRegisteredEvent extends Event {

    public Long clientId;
    @Column(name = "expected_delivery_time")
    public LocalDateTime expectedDeliveryTime;
    public Long productId;
    @Column(name = "product_price")
    public Double productPrice;
}

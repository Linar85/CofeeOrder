package com.example.CofeeOrder.model.Event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eventtype")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(allocationSize = 1, name = "order_seq")
    private Long id;
    private Long orderId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    private Long employeeId;
    @Column(name = "eventtype", insertable = false, updatable = false)
    private String eventType;

}

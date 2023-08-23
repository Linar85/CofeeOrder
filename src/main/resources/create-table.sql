
CREATE TYPE event_type AS ENUM ('registered', 'processed', 'canceled', 'been_readying', 'delivered');

CREATE TABLE IF NOT EXISTS ORDERS
(
    orderId         bigserial PRIMARY KEY,
    clientId        bigint NOT NULL,
    employeeId      bigint NOT NULL,
    expDeliveryTime timestamp,
    productId       bigint,
    productPrice    real,
    createdDate     timestamp,
    eventType       event_type
);
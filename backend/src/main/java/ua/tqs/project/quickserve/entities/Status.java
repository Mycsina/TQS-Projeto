package ua.tqs.project.quickserve.entities;

public enum Status {
    SCHEDULED, IN_MAKING, READY, ONGOING, DELIVERED, CANCELLED
    // SCHEDULED: The order was placed and is waiting to be processed
    // IN_MAKING: The order is being prepared
    // READY: The order is ready to be picked up
    // ONGOING: The order is being delivered
    // DELIVERED: The order was delivered
    // CANCELLED: The order was cancelled
}
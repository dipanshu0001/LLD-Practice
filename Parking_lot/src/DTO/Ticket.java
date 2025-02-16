package DTO;

import java.sql.Time;
import java.time.LocalDateTime;

public class Ticket {

    public Ticket(LocalDateTime enteryTime, ParkingSpot parkingSpot, Time exitTime, int ticketNumber, int price) {
        this.enteryTime = enteryTime;
        this.parkingSpot = parkingSpot;
        this.exitTime = exitTime;
        this.ticketNumber = ticketNumber;
        this.price = price;
    }

    public LocalDateTime getEnteryTime() {
        return enteryTime;
    }

    public void setEnteryTime(LocalDateTime enteryTime) {
        this.enteryTime = enteryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Time getExitTime() {
        return exitTime;
    }

    public void setExitTime(Time exitTime) {
        this.exitTime = exitTime;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private LocalDateTime enteryTime;
    private ParkingSpot parkingSpot;

    public Ticket(LocalDateTime enteryTime, ParkingSpot parkingSpot) {
        this.enteryTime = enteryTime;
        this.parkingSpot = parkingSpot;
    }

    private Time exitTime;

    private int ticketNumber;

    private int price;

}

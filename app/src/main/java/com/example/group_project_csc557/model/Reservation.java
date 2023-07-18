package com.example.group_project_csc557.model;

public class Reservation {
    private String name;
    private String phoneNumber;
    private String reservationDate;
    private String reservationTime;
    private int numberOfGuests;
    private int tableNumber;
    private String status;

    public Reservation(String name, String phoneNumber, String reservationDate, String reservationTime, int numberOfGuests, int tableNumber, String status) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numberOfGuests = numberOfGuests;
        this.tableNumber = tableNumber;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.ticket.bookingsystem.movies.databasefiles;

public class Seat {

    private int seatNo;
    private int showId;
    private int userID;
    private double price;
    private boolean isReserved;

    // Constructor
    public Seat(int seatNo) {
        this.seatNo = seatNo;
        this.isReserved = false; // default to not reserved
    }

    // Getters
    public int getSeatNo() {
        return seatNo;
    }

    public int getShowId() {
        return showId;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReserved() {
        return isReserved;
    }

    // Setters
    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public void bookSeat(int userID) {
        this.isReserved = true;
        this.userID = userID;
    }

    // toString method
    @Override
    public String toString() {
        return "Seat[" +
                "seatNo=" + seatNo +
                ", showId=" + showId +
                ", price=" + price +
                ", isReserved=" + isReserved +
                ']';
    }

}

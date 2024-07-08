package com.ticket.bookingsystem.movies.databasefiles;

public class Seat {

    private int seatNo;
    private int showId;
    private double price = 280;
    private boolean isReserved;
    private Integer userId;

    // Constructor
    public Seat(int seatNo) {
        this.userId = null;
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

    public Integer getuserId(){
        return userId;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void bookSeat(int userId) {
        this.isReserved = true;
        this.userId = userId;
    }

    public void cancelBooking() {
        this.isReserved = false;
        this.userId = null;
    }

    // toString method
    @Override
    public String toString() {
        return "Seat[" +
                "seatNo=" + seatNo +
                ", showId=" + showId +
                ", userId=" + userId +
                ", price=" + price +
                ", isReserved=" + isReserved +
                ']';
    }

}

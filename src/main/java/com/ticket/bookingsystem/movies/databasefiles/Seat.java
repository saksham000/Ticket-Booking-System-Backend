package com.ticket.bookingsystem.movies.databasefiles;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seatNo;

    private double price = 280;
    private boolean isReserved;
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonIgnore
    private Show show;

    public Seat(int seatNo) {
        this.userId = null;
        this.seatNo = seatNo;
        this.isReserved = false;
    }

    public Seat() {
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // public int getShowId() {
    // return showId;
    // }

    public double getPrice() {
        return price;
    }

    public Integer getuserId() {
        return userId;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    // public void setShowId(int showId) {
    // this.showId = showId;
    // }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public void bookSeat(int userId) {
        this.isReserved = true;
        this.userId = userId;
    }

    public void cancelBooking() {
        this.isReserved = false;
        this.userId = null;
    }

    @Override
    public String toString() {
        return "Seat[" +
                "seatNo=" + seatNo +
                ", userId=" + userId +
                ", price=" + price +
                ", isReserved=" + isReserved +
                ']';
    }

}

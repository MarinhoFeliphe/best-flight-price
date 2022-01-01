package com.src.bestflightprice.companies.domain;

public class FlightOffer {

    private long id;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String currency;
    private double price;
    private long points;
    private String type;
    private String log;
    private String site;
    private String company;

    public FlightOffer() {}

    public FlightOffer(FlightOffer flightOffer) {
        this.flightNumber = flightOffer.getFlightNumber();
        this.departureTime = flightOffer.getArrivalTime();
        this.arrivalTime = flightOffer.getArrivalTime();
        this.currency = flightOffer.getCurrency();
        this.type = flightOffer.getType();
        this.site = flightOffer.getSite();
        this.company = flightOffer.getCompany();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

package com.src.bestflightprice.companies.dto;

public class FlightOffer {

    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private double price;
    private String priceType;
    private double points;
    private String type;
    private String log;
    private String site;
    private String company;

    public FlightOffer() {
    }

    public FlightOffer(com.src.bestflightprice.companies.domain.FlightOffer flightOffer) {
        this.flightNumber = flightOffer.getFlightNumber();
        this.departureTime = flightOffer.getDepartureTime();
        this.arrivalTime = flightOffer.getArrivalTime();
        this.price = flightOffer.getPrice();
        this.points = flightOffer.getPoints();
        this.type = flightOffer.getType();
        this.priceType = flightOffer.getPriceType();
        this.log = flightOffer.getLog();
        this.site = flightOffer.getSite();
        this.company = flightOffer.getCompany();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
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

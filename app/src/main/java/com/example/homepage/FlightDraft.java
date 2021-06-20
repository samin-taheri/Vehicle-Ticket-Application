package com.example.homepage;

public class FlightDraft {
    private String startCity, startTime, date, endCity, arrivalTime, ticketPrice,company_name;

    public FlightDraft(String startCity, String startTime, String date, String endCity, String arrivalTime, String ticketPrice,String company_name) {
        this.startCity = startCity;
        this.startTime = startTime;
        this.date = date;
        this.endCity = endCity;
        this.arrivalTime = arrivalTime;
        this.ticketPrice = ticketPrice;
        this.company_name = company_name;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}

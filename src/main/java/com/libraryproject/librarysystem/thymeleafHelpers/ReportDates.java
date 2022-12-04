package com.libraryproject.librarysystem.thymeleafHelpers;

public class ReportDates {

    private String fromDate;

    private String toDate;

    public ReportDates(String fromDate, String toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ReportDates() {
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}

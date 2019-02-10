package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Place {

    public Place() {
    }

    public Place(String placeName, LocalDate startDate, LocalDate endDate, String placeTitle, String placeURL, String description) {
        this.placeName = placeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.placeTitle = placeTitle;
        this.placeURL = placeURL;
        this.content = description;
    }

    private String placeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String placeTitle;
    private String placeURL;
    private String content;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getPlaceURL() {
        return placeURL;
    }

    public void setPlaceURL(String placeURL) {
        this.placeURL = placeURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeName='" + placeName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", placeTitle='" + placeTitle + '\'' +
                ", placeURL='" + placeURL + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

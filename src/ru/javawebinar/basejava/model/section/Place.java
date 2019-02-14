package ru.javawebinar.basejava.model.section;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents generic Place of work, education, etc.
 */

public class Place {

    private String placeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String placeTitle;
    private String placeUrl;
    private String content;

    /**
     * Class constructor specifying name of place (eg. company name), start date (first working month),
     * end date (last working month), title (eg. position), optional URL and optional description.
     */
    public Place(String placeName, LocalDate startDate,
                 LocalDate endDate, String placeTitle, String placeUrl, String description) {
        Objects.requireNonNull(placeName, "placeName must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(endDate, "title must not be null");
        this.placeName = placeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.placeTitle = placeTitle;
        this.placeUrl = placeUrl;
        this.content = description;
    }

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

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return placeName.equals(place.placeName) &&
                startDate.equals(place.startDate) &&
                endDate.equals(place.endDate) &&
                placeTitle.equals(place.placeTitle) &&
                Objects.equals(placeUrl, place.placeUrl) &&
                Objects.equals(content, place.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeName, startDate, endDate, placeTitle, placeUrl, content);
    }

    @Override
    public String toString() {
        return "Place{"
                + "placeName='" + placeName + '\''
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", placeTitle='" + placeTitle + '\''
                + ", placeUrl='" + placeUrl + '\''
                + ", content='" + content + '\''
                + '}';
    }
}

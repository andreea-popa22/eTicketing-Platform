package entities.events;

import java.util.Objects;

public class Hour {
    private Integer hour;
    private Integer minutes;

    public Hour() {
        this.hour = 0;
        this.minutes = 0;
    }

    public Hour(Integer hour, Integer minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        if (minutes < 10){
            return hour + ":0" + minutes;
        }
        return hour + ":" + minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hour hour1 = (Hour) o;
        return Objects.equals(hour, hour1.hour) && Objects.equals(minutes, hour1.minutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minutes);
    }
}

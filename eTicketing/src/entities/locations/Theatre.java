package entities.locations;

import java.util.Objects;

public class Theatre extends Location {
    private Integer price_per_hour;
    private Integer surface;

    public Theatre() {}; // Empty constructor

    public Theatre(String name, String address, Phone contact, Integer capacity, Integer price_per_hour, Integer surface) {
        super(name, address, contact, capacity);
        this.price_per_hour = price_per_hour;
        this.surface = surface;
    }

    public Integer getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(Integer price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    @Override
    public String toString() {
        return super.toString() + "; Price per hour = " + price_per_hour + "; Surface = " + surface;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Theatre)) return false;
        if (!super.equals(o)) return false;
        Theatre theatre = (Theatre) o;
        return Objects.equals(price_per_hour, theatre.price_per_hour) && Objects.equals(surface, theatre.surface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price_per_hour, surface);
    }
}

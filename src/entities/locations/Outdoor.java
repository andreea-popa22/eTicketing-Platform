package entities.locations;

import java.util.Objects;

public class Outdoor extends Location {
    private Integer id;
    private Integer price_per_hour;
    private Integer surface;
    public static Integer outdoors = 0;

    public Outdoor() {}; // Empty constructor

    public Outdoor(String name, String address, Phone contact, Integer capacity, Integer price_per_hour, Integer surface) {
        super(name, address, contact, capacity);
        outdoors++;
        //this.id = outdoors;
        this.price_per_hour = price_per_hour;
        this.surface = surface;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(o instanceof Outdoor)) return false;
        if (!super.equals(o)) return false;
        Outdoor outdoor = (Outdoor) o;
        return Objects.equals(price_per_hour, outdoor.price_per_hour) && Objects.equals(surface, outdoor.surface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price_per_hour, surface);
    }
}

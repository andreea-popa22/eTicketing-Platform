package locations;

import java.util.Objects;

public abstract class Location {
    private String address;
    protected Phone contact;
    private Integer capacity;

    public Location() {} // Empty constructor

    public Location(String address, Phone contact, Integer capacity) {
        this.address = address;
        this.contact = contact;
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Phone getContact() {
        return contact;
    }

    public void setContact(Phone contact) {
        this.contact = contact;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Address = " + address + "; Contact = " + contact.toString() + "; Capacity = " + capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(address, location.address) && Objects.equals(contact, location.contact) && Objects.equals(capacity, location.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, contact, capacity);
    }
}

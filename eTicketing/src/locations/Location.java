package locations;

import usermenu.Service;

import java.util.Objects;

public abstract class Location implements Comparable<Location>{
    private String name;
    private String address;
    protected Phone contact;
    private Integer capacity;

    public Location() {} // Empty constructor

    public Location(String name, String address, Phone contact, Integer capacity) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.capacity = capacity;
        Service.all_locations.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Name = " + name + "; Address = " + address + "; Contact = " + contact.toString() + "; Capacity = " + capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) && Objects.equals(address, location.address) && Objects.equals(contact, location.contact) && Objects.equals(capacity, location.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, contact, capacity);
    }

    @Override
    public int compareTo(Location o) {
        return this.capacity.compareTo(o.getCapacity());
    }
}

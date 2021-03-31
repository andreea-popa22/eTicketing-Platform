package person;

import events.Event;
import locations.Phone;
import java.util.List;
import java.util.Objects;

public class Organizer {
    private String first_name;
    private String last_name;
    private Phone phone;

    public Organizer() {}; // Empty constructor

    public Organizer(String first_name, String last_name, Phone phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "First name = " + first_name + "; Last name = " + last_name + "; Phone = " + phone.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizer organizer = (Organizer) o;
        return first_name.equals(organizer.first_name) && last_name.equals(organizer.last_name) && phone.equals(organizer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, phone);
    }
}

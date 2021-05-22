package entities.person;

import entities.locations.Phone;

import java.util.Objects;

public class Adult extends Client{
    private Phone phone;

    public Adult() {}; // Empty constructor

    public Adult(String first_name, String last_name, Phone phone) {
        super(first_name, last_name);
        this.phone = phone;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString() + "; Phone = " + phone.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adult)) return false;
        if (!super.equals(o)) return false;
        Adult adult = (Adult) o;
        return Objects.equals(phone, adult.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone);
    }
}

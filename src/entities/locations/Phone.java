package entities.locations;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class Phone {
    String phone;

    public Phone() {}; // Empty constructor

    public Phone(String phone) {
        if (!phone.matches("^$|[0][7][0-9]{8}")) {
            throw new PatternSyntaxException("Invalid phone number", "^$|[0][7][0-9]{8}", -1);
        }
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return Objects.equals(phone, phone1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}

package entities.person;

import java.util.Objects;

public class Child extends Client{
    public final float discount = 0.5F;

    public Child() {}; // Empty constructor

    public Child(String first_name, String last_name) {
        super(first_name, last_name);
    }

    @Override
    public String toString() {
        return "Child: " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Child)) return false;
        if (!super.equals(o)) return false;
        Child child = (Child) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discount);
    }
}

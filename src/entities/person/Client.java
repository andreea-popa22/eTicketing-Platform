package entities.person;

import java.util.Objects;

public abstract class Client {
    private String first_name;
    private String last_name;

    public Client() {}; // Empty constructor

    public Client(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
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

    @Override
    public String toString() {
        return "First name = " + first_name + "; Last name = " + last_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(first_name, client.first_name) && Objects.equals(last_name, client.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), first_name, last_name);
    }
}

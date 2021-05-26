package entities.events;

import services.Service;

import java.util.Objects;

public class Actor {
    private Integer id;
    private String name;
    private Integer price_per_play;
    public static Integer actors = 0;

    public Actor() {}

    public Actor(String name, Integer price_per_play) {
        actors ++;
        this.id = actors;
        this.name = name;
        this.price_per_play = price_per_play;
        Service.all_actors.add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice_per_play() {
        return price_per_play;
    }

    public void setPrice_per_play(Integer price_per_play) {
        this.price_per_play = price_per_play;
    }

    @Override
    public String toString() {
        return "Actor: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name) && Objects.equals(price_per_play, actor.price_per_play);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price_per_play);
    }
}

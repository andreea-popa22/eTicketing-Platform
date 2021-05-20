package entities.events;

import services.Service;

import java.util.Objects;

public class Singer {
    private String name;
    private SingerType music_type;
    private Integer price_per_hour;
    public static Integer singers = 0;

    public Singer() {}  //Empty constructor

    public Singer(String name, Integer price_per_hour, SingerType music_type) {
        this.name = name;
        this.price_per_hour = price_per_hour;
        this.music_type = music_type;
        singers ++;
        Service.all_singers.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SingerType getMusic_type() {
        return music_type;
    }

    public void setMusic_type(SingerType music_type) {
        this.music_type = music_type;
    }

    public Integer getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(Integer price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    @Override
    public String toString() {
        return "Name = " + name + "; Music Type = " + music_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Singer)) return false;
        Singer singer = (Singer) o;
        return Objects.equals(name, singer.name) && music_type == singer.music_type && Objects.equals(price_per_hour, singer.price_per_hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, music_type, price_per_hour);
    }
}

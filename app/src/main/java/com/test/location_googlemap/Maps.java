package com.test.location_googlemap;

public class Maps {

    String iconUrl;
    String name;
    String vicinity;

    public Maps() {
    }

    public Maps(String iconUrl, String name, String vicinity) {
        this.iconUrl = iconUrl;
        this.name = name;
        this.vicinity = vicinity;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}

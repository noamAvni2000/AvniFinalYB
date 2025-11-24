package com.example.avnifinalyb;

public class MyItem {

    private String country;
    private String continent;
    private String religion;
    private String landLocked;
    private String hasNoam;
    private String population;

    public MyItem(String country, String continent, String religion, String landLocked, String hasNoam, String population) {
        this.country = country;
        this.continent = continent;
        this.religion = religion;
        this.landLocked = landLocked;
        this.hasNoam = hasNoam;
        this.population = population;
    }

    public String getCountry() { return country; }
    public String getContinent() { return continent; }
    public String getReligion() { return religion; }
    public String getLandLocked() { return landLocked; }
    public String getHasNoam() { return hasNoam; }
    public String getPopulation() { return population; }
}



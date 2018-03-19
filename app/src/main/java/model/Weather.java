package model;

/**
 * Created by mahbub on 3/19/2018.
 */

public class Weather {

    public Place place;
    public String icon;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}

package model;

/**
 * Created by mahbub on 3/19/2018.
 */

public class Weather {

    public Place place;
    public String icon;
    CurrentCondition currentCondition = new CurrentCondition();
    Temperature temperature = new Temperature();
    Wind wind = new Wind();
    Snow snow = new Snow();
    Clouds clouds = new Clouds();

}

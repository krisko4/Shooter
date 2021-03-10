package sample.Levels;


import sample.Controller;
import sample.LevelFile;
import sample.ParametricFile;

import java.io.*;

/**
 * Klasa abstrakcyjna, po której dziedziczą poziomy trudności i gry
 */

public class Level extends Controller {

    public String LevelName, NumberOfLevels;
    public double PauseTime,DurationTime,ObjectLifeTime,Changer;
    public int Ammo,Time,Level,ScorePoints,RequiredScore;

    public final static int initialScore=0;


    /**
     * Pobiera parametry poziomu z klasy LevelFile, która z kolei pobiera informacje z pliku opisującego poziom gry
     */
    public void getDifficulty()
    {

        LevelName= LevelFile.getInstance().LevelName;
        PauseTime=Double.parseDouble(LevelFile.getInstance().PauseTime);
        DurationTime=Double.parseDouble(LevelFile.getInstance().ObjectSpeed);
        ObjectLifeTime=Double.parseDouble(LevelFile.getInstance().VisibleTime);
    }

    /**
     * Ustala nowe parametry poziomu
     * @param level
     */
    public void setParameters(Level level)
    {

        Changer= Double.parseDouble(ParametricFile.getInstance().Changer);
        PauseTime=(1-(0.01*Changer))*level.PauseTime;
        DurationTime=(1-(0.01*Changer))*level.DurationTime;
        ObjectLifeTime=(1-(0.01*Changer))*level.ObjectLifeTime;

    }

    /**
     * Ustala startowe parametry poziomu
     * @param level
     */
    public void setStartParameters(Level level)
    {
        PauseTime=level.PauseTime;
        DurationTime=level.DurationTime;
        ObjectLifeTime=level.ObjectLifeTime;
        LevelName=level.LevelName;
    }



}


package sample.Levels;

/**
 * Klasa ustalająca parametry poziomu trudności
 */
public class GlobalLevel extends sample.Levels.Level {


    public   GlobalLevel()
    {
        LevelName="GLOBAL";
       SupremeLevel level=new SupremeLevel();
       setParameters(level);
    }
}

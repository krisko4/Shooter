package sample.Levels;

/**
 * Klasa ustalająca parametry poziomu trudności
 */
public class SupremeLevel extends sample.Levels.Level {


    public  SupremeLevel()
    {
        LevelName="SUPREME";
        EagleLevel level=new EagleLevel();
        setParameters(level);
    }

}

package sample.Levels;

/**
 * Klasa ustalająca parametry poziomu trudności
 */
public class GoldLevel extends sample.Levels.Level {

    public   GoldLevel()
    {
        LevelName="GOLD";
        SilverLevel level=new SilverLevel();
        setParameters(level);
    }
}

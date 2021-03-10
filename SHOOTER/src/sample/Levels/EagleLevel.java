package sample.Levels;

/**
 * Klasa ustalająca parametry poziomu trudności
 */
public class EagleLevel extends sample.Levels.Level {

  public EagleLevel()
    {
        LevelName="EAGLE";
        GoldLevel level=new GoldLevel();
        setParameters(level);
    }

}

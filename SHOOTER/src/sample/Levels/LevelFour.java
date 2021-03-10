package sample.Levels;

/**
 * Klasa ustalająca parametry poziomu gry
 */
public class LevelFour extends Level {

    public   LevelFour()
    {
        Level=4;
        Time=25;
        Ammo=20;
        ScorePoints=5;
        RequiredScore=50;
       /* ReadFromFile("par.txt");
        NumberOfLevels=wlasnosci.getProperty("liczbaStopniTrudności");
        if(NumberOfLevels.equals("3") || NumberOfLevels.equals("4"))
        {
            SilverLevel level=new SilverLevel();
            setParameters(level);
        }
        else if(NumberOfLevels.equals("5"))
        {
            EagleLevel level=new EagleLevel();
            setParameters(level);
        }*/
       getDifficulty();

    }
}

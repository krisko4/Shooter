package sample.Levels;


/**
 * Klasa ustalająca parametry poziomu gry
 */
public class LevelFive extends Level {

    public    LevelFive()
    {
        Level=5;
        Time=20;
        Ammo=20;
        ScorePoints=5;
        RequiredScore=50;
        getDifficulty();
    }

}

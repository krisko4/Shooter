package sample;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton odczytujący z pliku informacje o poziomie gry i trudności
 */
public class LevelFile extends Controller {

    private static LevelFile instance;

    public String LevelName,VisibleTime,ObjectSpeed,LevelTime,PointsForHit,PauseTime,LevelNumber,Ammo,PromotionPoints;

    private LevelFile()
    {
    }

    public static synchronized LevelFile getInstance()
    {
        if (instance==null){
            instance=new LevelFile();

        }
        return instance;
    }

    /**
     * Czyta plik opisujący poziom i przypisuje zmiennym informacje zawarte w tym pliku
     */
    public void readFile()
    {
        readFromFile(ParametricFile.getInstance().Filename);
        LevelName=wlasnosci.getProperty("NazwaPoziomu");
        VisibleTime=wlasnosci.getProperty("CzasWidocznościObiektuNaPlanszy");
        PauseTime=wlasnosci.getProperty("PrzerwaPoZestrzeleniuObiektu");
        ObjectSpeed=wlasnosci.getProperty("PrędkośćPrzemieszczaniaSięObiektu");
        LevelTime=wlasnosci.getProperty("CzasTrwaniaPoziomu");
        PointsForHit=wlasnosci.getProperty("IlośćPunktówZaTrafienie");
        LevelNumber=wlasnosci.getProperty("NumerPoziomu");
        Ammo=wlasnosci.getProperty("IlośćAmunicji");
        PromotionPoints=wlasnosci.getProperty("IlośćPunktówDoAwansu");
    }


}

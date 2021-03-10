package sample;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton odczytujący plik parametryczny i zapisujący informacje z pliku do zmiennych
 */
public class ParametricFile extends Controller {

    private static ParametricFile instance;
    public String Background,Width,Height,Filename,GameObjects,GameFigure,NumberOfLevels,GameName,Changer,R,G,B,ObjectWidth;

    private ParametricFile()
    {

        readFromFile("par.txt");
        Background=wlasnosci.getProperty("tło");
        GameName=wlasnosci.getProperty("nazwaGry");
        Width = wlasnosci.getProperty("początkowaSzerokośćPlanszy").trim();
        Height = wlasnosci.getProperty("początkowaWysokośćPlanszy").trim();
        Filename=wlasnosci.getProperty("nazwaBazowaPlikuZOpisemPoziomu") + "." + wlasnosci.getProperty("rozszerzeniePlikuZOpisemPoziomu");
        GameObjects=wlasnosci.getProperty("obiektyGry");
        GameFigure=wlasnosci.getProperty("figuraObiektuGry");
        NumberOfLevels=wlasnosci.getProperty("liczbaStopniTrudności");
        Changer=wlasnosci.getProperty("zmianaStopniaTrudności");
        R=wlasnosci.getProperty("kolorTłaR");
        G=wlasnosci.getProperty("kolorTłaG");
        B=wlasnosci.getProperty("kolorTłaB");
        ObjectWidth=wlasnosci.getProperty("początkowaSzerokośćObiektuGryJakoProcentPoczątkowejSzerokościPlanszy");





    }

    public static synchronized ParametricFile getInstance()
    {
        if (instance==null){
            instance=new ParametricFile();

        }
        return instance;
    }



}

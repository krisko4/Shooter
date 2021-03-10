/**
 *
 */
        package sample;

import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Levels.Level;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Klasa abstrakcyjna, po której dziedziczą kontrolery wszystkich scen GUI. Zawiera metody dotyczące przemieszczania między scenami i komunikacją z plikiem parametrycznym
 */

public class Controller  implements Initializable  {

    public Integer startTime;
    public Integer seconds = startTime;
    public String sceneName;
    public int random1, random2;
    public String PopUpWindowName;
    public String R, G, B, Background;
    public String Width, Heigth;
    public String LevelTime;
    public Properties wlasnosci;
    public Scene scene;
    public String fileName;
    public SequentialTransition seqTransition,sequTransition;
    public Rectangle r1,r2;

    final public String lsep = System.getProperty("line.separator");


    /**
     * Przypisuje do zmiennej typu Properties zawartość wczytywanego pliku
     * @param fileName
     * @return
     */

    public Properties readFromFile(String fileName)
    {
        try(FileInputStream input = new FileInputStream(new File(fileName)))
        {

            (wlasnosci=new Properties()).load(new InputStreamReader(input , Charset.forName("UTF-8")));

        }catch(IOException fe)
        {
            System.out.println("Loading failed");

        }
        return wlasnosci;
    }

    /**
     * Zamyka okno
     * @param event
     */
    public void closeWindow(MouseEvent event)
{
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.close();
}


    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    /**
     * Ustawia rozmiary okna pobrane z pliku parametrycznego
     * @param pane
     */

    /**
     * Ustawia rozmiary całego okna planszy
     * @param pane
     */
    public void setLayout(SplitPane pane)
    {

        int x = Integer.parseInt(ParametricFile.getInstance().Width);
        int y = Integer.parseInt(ParametricFile.getInstance().Height);
        pane.setPrefWidth(x);
        pane.setPrefHeight(y);
    }

    /**
     * Ustawia rozmiary poszczególnych podokien okna głównego
     * @param pane
     */
    public void setLayout(AnchorPane pane)
    {
        int x = Integer.parseInt(ParametricFile.getInstance().Width);
        int y = Integer.parseInt(ParametricFile.getInstance().Height);
        pane.setPrefWidth(x);
        pane.setPrefHeight(y);
    }






    /**
     * Tworzy i wyświetla wyskakujące okienko
     * @param event
     * @throws IOException
     */
    public void setPopUpWindow(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(PopUpWindowName));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Tworzy i wyświetla nowe okno z zamknięciem poprzedniego
     * @param event
     * @return
     * @throws IOException
     */
    public Stage setNewScene(ActionEvent event) throws IOException {

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        Parent root = FXMLLoader.load(getClass().getResource(sceneName));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        return stage;
    }



    /**
     * Po naciśnięciu przycisku do którego jest przydzielona zamyka okno
     * @param event
     * @throws IOException
     */

    public void closeButtonAction(ActionEvent event) throws IOException {
        // get a handle to the stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // do what you have to do
        window.close();

    }



    /**
     * Po naciśnięciu klawisza ESCAPE zamyka okno
     * @param e
     * @throws IOException
     */
    public void closeKeyAction(KeyEvent e) throws IOException {


        if (e.getEventType() == KeyEvent.KEY_PRESSED && e.getCode() == KeyCode.ESCAPE) {
            // get a handle to the stage
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // do what you have to do
            window.close();
        }
    }


    /**
     * Tworzy i wyświetla wyskakujące okno
     * @param fileName
     * @return
     * @throws IOException
     */
    public FXMLLoader setPopUpWindow(String fileName) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        Stage stage=new Stage();
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();


        return loader;


    }

    /**
     * Tworzy plik opisujący poziom gry
     * @param level
     */
    public void saveLevelToFile(Level level) {


        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "UTF-8")));
        } catch (IOException ioe) {
            System.out.println("Failed to save file");
        }
        pw.println("#Plik opisujący poziom gry." + lsep
                + "NazwaPoziomu=" + level.LevelName + lsep
                + "CzasWidocznościObiektuNaPlanszy=" + level.ObjectLifeTime +lsep
                + "PrzerwaPoZestrzeleniuObiektu=" + level.PauseTime +lsep
                + "PrędkośćPrzemieszczaniaSięObiektu=" +level.DurationTime +lsep
                + "CzasTrwaniaPoziomu=" + level.Time +lsep
                + "IlośćAmunicji=" + level.Ammo +lsep
                + "IlośćPunktówZaTrafienie=" +level.ScorePoints +lsep
                + "NumerPoziomu=" +level.Level + lsep
                + "IlośćPunktówDoAwansu=" + level.RequiredScore + lsep);
        pw.close();
    }

    /**
     * Ustawia tło planszy w zależności od parametrów określonych w pliku parametrycznym
     * @param anchorPane
     */
    public void setBackground(AnchorPane anchorPane)
    {

        if (ParametricFile.getInstance().Background.equals("plikGraficzny")) {
            anchorPane.getStyleClass().add("anchor");
            anchorPane.getStylesheets().add("anchor.css");
        } else  {
            R = ParametricFile.getInstance().R;
            G = ParametricFile.getInstance().G;
            B = ParametricFile.getInstance().B;
            int r = Integer.parseInt(R);
            int g = Integer.parseInt(G);
            int b = Integer.parseInt(B);
            anchorPane.setBackground(new Background(new BackgroundFill(Color.rgb(r, g, b), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }


    /**
     * Zamyka aplikację
     * @param event
     */
    public void exit(ActionEvent event) {
        System.exit(0);
    }
}

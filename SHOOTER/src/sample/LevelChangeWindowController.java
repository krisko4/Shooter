package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler okna informującego o zmianie poziomu
 */
public class LevelChangeWindowController extends Controller {


    public javafx.scene.control.Label Label;
    private final Integer startTime = 5;
    private Integer seconds = startTime;

    public void initialize(URL url, ResourceBundle resourceBundle) {
            prepareForNextLevel();
    }


    /**
     * Odlicza do rozpoczęcia następnego poziomu
     */
    public void prepareForNextLevel(){

    Timeline time = new Timeline();
    time.setCycleCount(Timeline.INDEFINITE);

    KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            seconds--;

            Label.setText(seconds.toString());
            if (Label.getText().equals("0")) {
                time.stop();
                Stage stage = (Stage) Label.getScene().getWindow();
                stage.close();

            }


        }
    });


    time.getKeyFrames().add(frame);
    time.playFromStart();
}
}


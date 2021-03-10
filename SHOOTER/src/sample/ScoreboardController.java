package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.EventHandler;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;

import java.util.ResourceBundle;

/**
 * Kontroler okna zawierającego tablice wyników
 */
public class ScoreboardController extends Controller {

    final ToggleGroup Group=new ToggleGroup();


    public TableView<Person> tableView;
    public TableColumn<Person, String> pseudoColumn;
    public TableColumn<Person, String> scoreColumn;
    public TableColumn<Person, String> levelColumn;
    public RadioButton SilverTick;
    public RadioButton GoldTick;
    public RadioButton EagleTick;
    public RadioButton SupremeTick;
    public RadioButton GlobalTick;
    public Button BackButton;
    public AnchorPane pane;


    public void initialize(URL url, ResourceBundle resourceBundle)
{
    pane.getStyleClass().add("anchor");
    pane.getStylesheets().add("anchor3.css");

 BackButton.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
       closeWindow(mouseEvent);
    }
 });

    pseudoColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("Nickname"));
    scoreColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("Score"));
    levelColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("Level"));
    Person person=new Person(); //tworzenie nowej osoby której statystyki dodane będą do tabeli wyników
    setToggles();


    Group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        @Override
        public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {

            RadioButton selectedRadioButton = (RadioButton) Group.getSelectedToggle(); //naciśnięcie na przycisk powoduje przypisanie parametrów tego przycisku do zmiennej
            String toogleGroupValue = selectedRadioButton.getText();
            tableView.setItems(person.getPeople(toogleGroupValue));








        }






    });









}

public void setToggles()
{
    EagleTick.setToggleGroup(Group);
    SilverTick.setToggleGroup(Group);
    GoldTick.setToggleGroup(Group);
    SupremeTick.setToggleGroup(Group);
    GlobalTick.setToggleGroup(Group);
}



}

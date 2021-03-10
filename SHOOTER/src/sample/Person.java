package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Klasa służąca do zapisywania graczy do tabeli wyników
 */
public class Person extends Controller {
    private SimpleStringProperty Nickname;
    private SimpleStringProperty Score, Level;

    public Person(String Nickname, String Score, String Level) {
        this.Nickname = new SimpleStringProperty(Nickname);
        this.Score = new SimpleStringProperty(Score);
        this.Level = new SimpleStringProperty(Level);

    }

    public Person() {
    }


    /**
     * Pobiera pseudonim
     * @return
     */
    public String getNickname() {
        return Nickname.get();
    }

    /**
     * Pobiera wynik
     * @return
     */
    public String getScore() {
        return Score.get();
    }

    /**
     * Pobiera poziom
     * @return
     */
    public String getLevel() {
        return Level.get();
    }

    /**
     * Odczytuje z pliku JSON mapę graczy i dodaje graczy do odpowiednich rubryk tabeli
     * @param level
     * @return
     */
    public ObservableList<Person> getPeople(String level) {
        Gson gson = new Gson();

        ObservableList<Person> people = FXCollections.observableArrayList();
        List<Player> foo;
        Map<String, List<Player>> map;
        try (JsonReader reader = new JsonReader(new FileReader("Output.json", Charset.forName("UTF-8")))) {
            Type mapType = new TypeToken<Map<String, List<Player>>>() {
            }.getType();
            map = gson.fromJson(reader, mapType);
            foo = map.get(level);
            if (foo != null) {
                for (int i = 0; i < foo.size(); i++) {
                    people.add(new Person(foo.get(i).Nickname, foo.get(i).Score, foo.get(i).Level));

                }
            }
            else{
                people.clear();
            }




        } catch (IOException ie) {

        }
        return people;
    }
}

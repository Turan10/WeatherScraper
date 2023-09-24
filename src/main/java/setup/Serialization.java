package setup;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
public class Serialization {
    Gson gson = new Gson();

    public String serialize(Object object) {
        return gson.toJson(object);
    }

    public void JsonToFile(String json, String filename) {

        try(FileWriter file = new FileWriter("WeatherForecast.json", true)) {
            file.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

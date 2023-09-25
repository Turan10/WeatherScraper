package setup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dto.ApiWeatherDTO;
import dto.WeatherDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Serialization {
    Gson gson = new Gson();

    public String serialize(Object object) {
        return gson.toJson(object);
    }

    public void JsonToFile(String json, String filename) {
        try (FileWriter file = new FileWriter(filename, true)) {
            file.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void listToJsonFile(List<WeatherDTO> weatherDTOList, String filename) {
      String starttext = "{ \n \"weathers\" : [ \n";
        String endtext = "] \n }";

        try (FileWriter file = new FileWriter(filename, false)) {

       file.write(starttext);
       int lastindex = -1;

       for(int i = 0; i < weatherDTOList.size()-2; i++) {
           String json = gson.toJson(weatherDTOList.get(i));
           file.write(json + ", \n");
           lastindex = i;
       }
       String json = gson.toJson(weatherDTOList.get(lastindex+1));
       file.write(json + "\n");

         file.write(endtext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ApiWeatherDTO readWeatherFromJson(String filename) {
        try (Reader reader = new FileReader(filename)) {
            return new Gson().fromJson(reader, ApiWeatherDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

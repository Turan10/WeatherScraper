package dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WeatherDTO {

    private String date;
    private int temp;
    private int chanceOfRain;
    private String wind;




}

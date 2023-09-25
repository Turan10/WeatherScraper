package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WeatherDTO {
    private String location;
    private String date;
    private int temp;
    private int chanceOfRain;
    private String wind;


    public int FehrenheitToCelcius(int temp) {
        return (int) ((temp - 32) * 5 / 9);
    }

}

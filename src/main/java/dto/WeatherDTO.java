package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class WeatherDTO {
    private String location;
    private int date;
    private int temp;
    private int rain;
    private int humidity;


    public int FehrenheitToCelcius(int temp) {
        return (int) ((temp - 32) * 5 / 9);
    }

/*    @Override
    public String toString() {
        return "WeatherDTO{" +
                "temp=" + temp + "" + "C" +
                ", humidity=" + rain + "%" +
                ", rainfallinmm=" + rainfallinmm + "mm" +
                '}';
    }*/
}

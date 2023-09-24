package dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class WeatherDTO {
    private int temp;
    private int humidity;
    private int rainfallinmm;


    public int FehrenheitToCelcius(int temp) {
        return (int) ((temp - 32) * 5 / 9);
    }

    @Override
    public String toString() {
        return "WeatherDTO{" +
                "temp=" + temp + "" + "C" +
                ", humidity=" + humidity + "%" +
                ", rainfallinmm=" + rainfallinmm + "mm" +
                '}';
    }
}

package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class WeatherForecastContainerDTO {
    private List<WeatherDTO> weathers;

}

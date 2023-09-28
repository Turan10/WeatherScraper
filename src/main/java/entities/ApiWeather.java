package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ApiWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;


    //Change to LocalDate
    @Column(name = "forecast_date")
    private String forecastDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_weather_data_id")
    private CurrentWeatherData currentWeatherData;

}


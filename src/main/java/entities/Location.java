package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name", unique = true, nullable = false)
    private String locationName;

    @OneToMany(mappedBy = "location")
    private List<ApiWeather> apiForecasts;

    @OneToMany(mappedBy = "location")
    private List<WeatherForecast> weatherForecasts;
}

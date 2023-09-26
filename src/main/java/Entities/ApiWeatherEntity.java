package Entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "api_weather")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ApiWeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_data_id", referencedColumnName = "id")
    private CurrentDataEntity currentData;

    public ApiWeatherEntity(String locationName, CurrentDataEntity currentData) {
        this.locationName = locationName;
        this.currentData = currentData;
    }
}

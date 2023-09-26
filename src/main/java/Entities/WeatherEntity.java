package Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "weather")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private String date;

    @Column(name = "temp")
    private int temp;

    @Column(name = "chance_of_rain")
    private int chanceOfRain;

    @Column(name = "wind")
    private String wind;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_data_id", referencedColumnName = "id")
    private CurrentDataEntity currentData;

    public int FehrenheitToCelcius(int temp) {
        return (int) ((temp - 32) * 5 / 9);
    }
}


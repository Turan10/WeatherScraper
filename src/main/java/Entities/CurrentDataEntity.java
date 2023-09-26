package Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "current_data")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CurrentDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sky_text")
    private String skyText;

    @Column(name = "humidity")
    private String humidity;
}

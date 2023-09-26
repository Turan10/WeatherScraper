package dto;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiWeatherDTO {
    private String LocationName;
    private CurrentData CurrentData;


}

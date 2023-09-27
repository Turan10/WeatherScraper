package dto;


import com.google.gson.annotations.SerializedName;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiWeatherDTO {
    private String LocationName;
    @SerializedName("CurrentData")
    private CurrentDataDTO currentData;





}

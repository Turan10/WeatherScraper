package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CurrentData {
    private String temperature;
    private String skyText;
    private String humidity;
    private String windText;
}

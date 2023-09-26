package setup;

import dto.ApiWeatherDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiReaderTest {



    @Test
    void getWeatherForCopenhagen (){
        ApiReader apiReader = new ApiReader();
        ApiWeatherDTO weatherCPH = apiReader.getWeatherFromApi("Copenhagen");
        assertNotNull(weatherCPH);
        assertEquals(true , weatherCPH.getLocationName().contains("København"));
    }

    


}
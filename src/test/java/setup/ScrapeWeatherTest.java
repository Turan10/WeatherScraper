package setup;

import dto.WeatherDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScrapeWeatherTest {
    List<WeatherDTO> testList;

    @BeforeEach
    public void setUp(){
        ScrapeWeather scrapeWeather = new ScrapeWeather();

        testList = scrapeWeather.scrapeWeather();
    }

    @Test
    void dayTester() {

        assertNotNull(testList);

        if(!testList.isEmpty()){
            boolean isADay = false;
            for (WeatherDTO dto : testList) {

                boolean isEnumValue = false;
                for (DaysOfWeek day : DaysOfWeek.values()) {
                    if (day.getDay().equalsIgnoreCase(dto.getDate())) {
                        isEnumValue = true;
                        break;
                    }
                }
            }
        }
    }

    @Test
    void isScraping() {
        assertNotNull(testList);
        assertFalse(testList.isEmpty());
    }


    @Test
    void isTempCorrect() {
        assertNotNull(testList);
        assertFalse(testList.isEmpty());

        for (WeatherDTO dto : testList) {
            boolean matchesPattern = dto.getTemp() > -20 && dto.getTemp() < 60;
            assertTrue(dto.getTemp() < 100);
        }
    }

    @Test
    void isEnriched() {
        assertNotNull(testList);
        assertFalse(testList.isEmpty());

        for (WeatherDTO dto : testList) {
            assertNotNull(dto.getCurrentDataDTO());
        }
    }


}
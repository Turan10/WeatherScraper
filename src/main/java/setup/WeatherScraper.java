package setup;

import dto.WeatherDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WeatherScraper {

    public WeatherDTO scrapeWeather(String city) {
        String url = "https://www.wunderground.com/weather/dk/" + city;
        Document document = null;
        WeatherDTO weatherDTO = null;


        try {
            document = Jsoup.connect(url).header("Accept-Language", "en-GB;q=0.5").get();

            String tempTemperture = document.select("#inner-content > div.region-content-main > div:nth-child(1) > div > div:nth-child(1) > div:nth-child(1) > lib-city-current-conditions > div > div.conditions-circle-wrap.small-12.medium-7.columns.text-center > div > div > div.current-temp > lib-display-unit > span > span.wu-value.wu-value-to").text();
            String tempHumidity = document.select("#inner-content > div.region-content-main > div:nth-child(2) > div > div.row.city-forecast > div:nth-child(1) > lib-additional-conditions > lib-item-box > div > div.content > div > div:nth-child(5) > div.small-8.columns > lib-display-unit > span > span.wu-value.wu-value-to").text();
            String tempRainfall = document.select("#inner-content > div.region-content-main > div:nth-child(2) > div > div.row.city-forecast > div:nth-child(1) > lib-additional-conditions > lib-item-box > div > div.content > div > div:nth-child(6) > div.small-8.columns > lib-display-unit > span > span.wu-value.wu-value-to").text();

            int temperture = Integer.parseInt(tempTemperture.replaceAll("[^0-9.]", ""));
            int humidity = Integer.parseInt(tempHumidity.replaceAll("[^0-9.]", ""));
            int rainfall = Integer.parseInt(tempRainfall.replaceAll("[^0-9.]", ""));


            weatherDTO = WeatherDTO.builder()
                    .temp(temperture)
                    .humidity(humidity)
                    .rainfallinmm(rainfall)
                    .build();

            int celcius = weatherDTO.FehrenheitToCelcius(temperture);
            weatherDTO.setTemp(celcius);
            System.out.println(weatherDTO.toString());

return weatherDTO;

        } catch (IOException e) {
            e.printStackTrace();
        }
return weatherDTO;
    }
}

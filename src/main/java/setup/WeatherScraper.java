package setup;

import dto.WeatherDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WeatherScraper {

    public WeatherDTO scrapeWeather() {
        String url = "https://weather.com/da-DK/weather/tenday/l/92d08441e183caf58d83600630214143efe0aea795ef69df0009011b395e16a8";
        Document document = null;
        WeatherDTO weatherDTO = null;


        try {
            document = Jsoup.connect(url).get();

            String tempTemperature = document.selectXpath("/html/body/div[1]/main/div[2]/main/div[1]/section/div[2]/div[2]/details[1]/div/div[1]/div/div[1]/span").text();
            String tempHumidity = document.select("#detailIndex0 > div:nth-child(2) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > div:nth-child(2) > span:nth-child(2)").text();
            String tempRainfall = document.select("#detailIndex0 > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > span:nth-child(2)").text();


            System.out.println("Temparature" +tempTemperature);
            System.out.println("Humidity" +tempHumidity);
            System.out.println("Rainfall" +tempRainfall);

            int temperature = Integer.parseInt(tempTemperature.replaceAll("°", ""));
            int humidity = Integer.parseInt(tempHumidity.replaceAll("%", ""));
            int rainfall = Integer.parseInt(tempRainfall.replaceAll("%", ""));

            System.out.println(temperature);
            System.out.println(humidity);
            System.out.println(rainfall);

            weatherDTO = WeatherDTO.builder()
                    .temp(temperature)
                    .humidity(humidity)
                    .rainfallinmm(rainfall)
                    .build();

            //int celcius = weatherDTO.FehrenheitToCelcius(temperature);
           // weatherDTO.setTemp(celcius);
           // System.out.println(weatherDTO.toString());

return weatherDTO;

        } catch (IOException e) {
            e.printStackTrace();
        }
return weatherDTO;
    }
}

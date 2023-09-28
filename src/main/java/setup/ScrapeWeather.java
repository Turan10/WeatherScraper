package setup;

import dto.ApiWeatherDTO;
import dto.WeatherDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;


public class ScrapeWeather {

    private static final String URL = "https://weather.com/da-DK/weather/tenday/l/92d08441e183caf58d83600630214143efe0aea795ef69df0009011b395e16a8";

    public List<WeatherDTO> scrapeWeather() {
        List<WeatherDTO> weatherDTOList = new ArrayList<>();


        try {
            Document document = Jsoup.connect(URL).get();
            Element table = document.select(".DailyForecast--DisclosureList--nosQS").first();

            table.select("details > summary").forEach(tr -> {
                String date = tr.select("h3.DetailsSummary--daypartName--kbngc").text();

                String temp = tr.select("span.DetailsSummary--highTempValue--3PjlX").text().replace("°", "");
                if (temp.equals("--")) {
                    temp = tr.select(".DetailsSummary--lowTempValue--2tesQ").text().replaceAll("°", "");
                }

                String chanceOfRain = tr.select(".DetailsSummary--precip--1a98O span").text();
                String wind = tr.select(".DetailsSummary--wind--1tv7t.DetailsSummary--extendedData--307Ax > span").text();

                WeatherDTO weatherDTO = WeatherDTO.builder()
                        .date(date)
                        .temp(Integer.parseInt(temp))
                        .chanceOfRain(Integer.parseInt(chanceOfRain.replace("%", "")))
                        .wind(wind)
                        .build();

                weatherDTOList.add(weatherDTO);
            });

        } catch (Exception e) {
            System.out.println("Failed to scrape weather data: " + e.getMessage());
            e.printStackTrace();
        }
        return weatherDTOList;
    }
}

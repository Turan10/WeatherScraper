package setup;

import dto.WeatherDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Paginated {

    Document document = null;
    WeatherDTO weatherDTO = null;
    //public static void main(String[] args) {

    public List<WeatherDTO> scrapeWeather() {
        List<WeatherDTO> weatherDTOList = new ArrayList<>();

        String url = "https://weather.com/da-DK/weather/tenday/l/92d08441e183caf58d83600630214143efe0aea795ef69df0009011b395e16a8";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Element table = document.select(".DailyForecast--DisclosureList--nosQS").first();


            table.select("details > summary").forEach(tr -> {
                String date = tr.select("h3.DetailsSummary--daypartName--kbngc").text();

                String temp = tr.select("span.DetailsSummary--highTempValue--3PjlX").text().replace("°","");
                if(temp.equals("--")){
                    temp = tr.select(".DetailsSummary--lowTempValue--2tesQ").text().replaceAll("°","");
                }

                String chanceOfRain = tr.select(".DetailsSummary--precip--1a98O span").text();

                String wind = tr.select(".DetailsSummary--wind--1tv7t.DetailsSummary--extendedData--307Ax > span").text();

                WeatherDTO weatherDTO = WeatherDTO.builder()
                        .date(date)
                        .temp(Integer.parseInt(temp))
                        .chanceOfRain(Integer.parseInt(chanceOfRain.replace("%","")))
                        .wind(wind)
                        .build();

                weatherDTOList.add(weatherDTO);
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherDTOList;
    }
}

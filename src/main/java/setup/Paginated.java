package setup;

import dto.WeatherDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Paginated {
    public static void main(String[] args) {

        String url = "https://weather.com/da-DK/weather/tenday/l/92d08441e183caf58d83600630214143efe0aea795ef69df0009011b395e16a8";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Element table = document.select(".DailyForecast--DisclosureList--nosQS").first();


            table.select("details > summary").forEach(tr -> {

                System.out.println(tr.select(".DetailsSummary--temperature--1kVVp").text());
               // String spans = tr.select("details > summary").first().text();
               // String date, temp, rain;


              //  elementList.add(spans);

                /*WeatherDTO weatherDTO = WeatherDTO.builder()
                        .date(getLocalDate(date))
                        .temp(temp)
                        .rain(rain)
                        .build();*/
            });

            //System.out.println(elementList.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

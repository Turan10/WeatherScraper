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
    public static void main(String[] args) {

        String url = "https://weather.com/da-DK/weather/tenday/l/92d08441e183caf58d83600630214143efe0aea795ef69df0009011b395e16a8";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Element table = document.select(".DailyForecast--DisclosureList--nosQS").first();


            table.select("details > summary").forEach(tr -> {
                // String spans = tr.select("details > summary").first().text();
                // String date, temp, rain;

                String date = tr.select("h3.DetailsSummary--daypartName--kbngc").text();

                String temp = tr.select("span.DetailsSummary--highTempValue--3PjlX").text();

                String rain = tr.select(".DetailsSummary--precip--1a98O span").text();





                //int date = Integer.parseInt(tr.select(".DailyContent--daypartDate--3VGlz").text());
               // int rain = Integer.parseInt(tr.select(".DailyContent--value--1Jers").text());
               // int humidity = Integer.parseInt(tr.select(".DetailsTable--listItem--Z-5Vi").text());

                //  elementList.add(spans);


            });

            //System.out.println(elementList.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

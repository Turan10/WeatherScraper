package setup;

import dto.WeatherDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Paginated {
    public static void main(String[] args) {

        String url = "https://weather.com/da-DK/weather/tenday/l/92d08441e183caf58d83600630214143efe0aea795ef69df0009011b395e16a8";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Elements trs = document.select(".DailyForecast--DisclosureList--nosQS");

            trs.forEach(tr -> {
                Elements spans = tr.select("details > summary");
                String date, temp, rain;
                if (spans.size() == 8) {
                    date = spans.get(8).text();
                    temp = spans.get(1).text();
                    System.out.println(date + " " + temp);
                } else {
                    date = spans.get(8).text();
                    temp = spans.get(1).text();
                    rain = spans.get(2).text();
                    System.out.println(date + " " + temp + " " + rain);
                }
                WeatherDTO weatherDTO = WeatherDTO.builder()
                        .date(getLocalDate(date))
                        .temp(temp)
                        .rain(rain)
                        .build();
            });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package setup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {
    public static void main(String[] args) {


        String url = "https://weather.com/da-DK/weather/tenday/l/92d08441e183caf58d83600630214143efe0aea795ef69df0009011b395e16a8";
        Document document = null;

        try {
            document = Jsoup.connect(url).get();

            String tempTemperature = document.select("#detailIndex0 > summary:nth-child(1)").text();
            System.out.println(tempTemperature);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
public class Main {
    public static void main(String[] args) {
        try {
            InputStream api_response = new URL("https://api.exchangeratesapi.io/latest").openStream();
            Scanner s = new Scanner(api_response).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            JSONObject value_rates = new JSONObject(result).getJSONObject("rates");
            Scanner rate_from_user = new Scanner(System.in);
            while (true) {
                System.out.println("Jaka waluta cie interesuje?? podaj kod:");
                String code = rate_from_user.nextLine().trim().toUpperCase();
                if (value_rates.has(code)) {
                    System.out.printf("EUR/%s to %.6f\n", code, value_rates.getBigDecimal(code));
                } else {
                    System.out.printf("Nie znaleziono kodu, podaj inny:\n", code);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
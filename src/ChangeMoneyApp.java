import com.google.gson.Gson;

import java.io.IOException;

public class ChangeMoneyApp {

    public static void main(String[] args) throws IOException, InterruptedException {

        ChangeRateService service = new ChangeRateService();
        String json = service.getRates("USD");

        Gson gson = new Gson();
        ChangeRateResponse data = gson.fromJson(json, ChangeRateResponse.class);

        System.out.println("Base: " + data.getBase_code());
        System.out.println("ARS: " + data.getConversion_rates().get("ARS"));
        System.out.println("EUR: " + data.getConversion_rates().get("EUR"));
    }

}

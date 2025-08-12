package ExchangeRate;

import java.util.Map;

public class ChangeRateResponse {
    //Class para la desealizar JSON
    private String result;
    private String base_code;
    private Map<String, Double> conversion_rates;


    //Getters


    public String getResult() {
        return result;
    }

    public String getBase_code() {
        return base_code; 
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    //Setters
    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}

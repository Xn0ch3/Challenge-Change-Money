import Conversion.ConversionMoney;
import ExchangeRate.ChangeRateResponse;
import ExchangeRate.ChangeRateService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeMoneyApp {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        ChangeRateService service = new ChangeRateService();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        boolean salir = false;

        List<ConversionMoney> historial = new ArrayList<>();

        String menu = """
                \n=== Conversor de Monedas ===
                1 - De Dólares (USD) a Pesos Argentinos (ARS)
                2 - De Pesos Argentinos (ARS) a Dólares (USD)
                3 - De Dólares (USD) a Reales Brasileños (BRL)
                4 - De Reales Brasileños (BRL) a Dólares (USD)
                5 - De Dólares (USD) a Pesos Colombianos (COP)
                6 - De Pesos Colombianos (COP) a Dólares (USD)
                7 - Salir
                Seleccione una opción:\s
                """;

        while (!salir) {
            System.out.println(menu);
            String opcion = scanner.nextLine();

            if (opcion.equals("7")) {
                salir = true;
                System.out.println("¡Hasta luego!");
                continue;
            }

            String monedaOrigen = "";
            String monedaDestino = "";

            switch (opcion) {
                case "1": monedaOrigen = "USD"; monedaDestino = "ARS"; break;
                case "2": monedaOrigen = "ARS"; monedaDestino = "USD"; break;
                case "3": monedaOrigen = "USD"; monedaDestino = "BRL"; break;
                case "4": monedaOrigen = "BRL"; monedaDestino = "USD"; break;
                case "5": monedaOrigen = "USD"; monedaDestino = "COP"; break;
                case "6": monedaOrigen = "COP"; monedaDestino = "USD"; break;
                default:
                    System.out.println("❌ Opción inválida.");
                    continue;
            }

            try {
                System.out.printf("Ingrese la cantidad de %s a convertir a %s: ", monedaOrigen, monedaDestino);
                double montoOrigen = Double.parseDouble(scanner.nextLine());

                String json = service.getRates(monedaOrigen);
                ChangeRateResponse data = gson.fromJson(json, ChangeRateResponse.class);

                Double tasa = data.getConversion_rates().get(monedaDestino);

                if (tasa != null) {
                    double montoDestino = montoOrigen * tasa;
                    System.out.printf("%.2f %s = %.2f %s%n", montoOrigen, monedaOrigen, montoDestino, monedaDestino);

                    historial.add(new ConversionMoney(monedaOrigen, montoOrigen, monedaDestino, montoDestino));
                } else {
                    System.out.println("❌ Moneda de destino no encontrada.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ El monto ingresado no es válido.");
            } catch (Exception e) {
                System.out.println("❌ Error al obtener los datos: " + e.getMessage());
            }
        }

        try (FileWriter writer = new FileWriter("historial.json")) {
            writer.write(gson.toJson(historial));
            System.out.println("✅ Historial guardado en historial.json");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar historial: " + e.getMessage());
        }

        scanner.close();
    }
}

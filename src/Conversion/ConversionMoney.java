package Conversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversionMoney {

    private String monedaOrigen;
    private double montoOrigen;
    private String monedaDestino;
    private double montoDestino;
    private String fecha;

    // Constructor parametrizado
    public ConversionMoney(String monedaOrigen, double montoOrigen, String monedaDestino, double montoDestino) {
        this.monedaOrigen = monedaOrigen;
        this.montoOrigen = montoOrigen;
        this.monedaDestino = monedaDestino;
        this.montoDestino = montoDestino;
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    // Constructor sin parámetros
    public ConversionMoney() {}

    // Getters
    public String getMonedaOrigen() { return monedaOrigen; }
    public double getMontoOrigen() { return montoOrigen; }
    public String getMonedaDestino() { return monedaDestino; }
    public double getMontoDestino() { return montoDestino; }
    public String getFecha() { return fecha; }
}

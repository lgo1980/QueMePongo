package ar.edu.utn.frba.dds;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ServicioMeteorologicoAccuWeatherApi implements ServicioMeteorologico {

  private final AccuWeatherApi api;
  private final Duration periodoDeValidez;
  private CondicionClimatica condicionClimatica;
  private final String direccion;
  private LocalDateTime proximaExpiracion;

  public ServicioMeteorologicoAccuWeatherApi(
      AccuWeatherApi api, Duration periodoDeValidez, String direccion) {
    if (api == null) {
      throw new IllegalArgumentException("Debe ingresar una api del clima");
    }
    if (periodoDeValidez == null) {
      throw new IllegalArgumentException("Debe ingresar un periodo de validez");
    }
    if (direccion == null) {
      throw new IllegalArgumentException("Debe ingresar una localidad");
    }
    this.api = api;
    this.periodoDeValidez = periodoDeValidez;
    this.direccion = direccion;
  }

  public CondicionClimatica obtenerCondicionesClimaticas() {
    if (this.expiro()) {
      Map<String, Object> condicion = consultarApi();
      Double temperatura = obtenerTemperatura(condicion);
      condicionClimatica = new CondicionClimatica(temperatura);
      proximaExpiracion = LocalDateTime.now().plus(periodoDeValidez);
    }
    return condicionClimatica;
  }

  @Override
  public List<AlertaMeteorologica> obtenerAlertaMeteorologica() {
    List<String> alertas = api.getAlerts(direccion).get("CurrentAlerts");
    return (List<AlertaMeteorologica>) alertas.stream().map(alerta -> switch (alerta) {
      case "STORM" -> AlertaMeteorologica.TORMENTA;
      case "HAIL" -> AlertaMeteorologica.GRANIZO;
      default -> AlertaMeteorologica.HURACAN;
    });
  }

  private static Double obtenerTemperatura(Map<String, Object> condicion) {
    Map<String, Object> temperatura = (Map<String, Object>) condicion.get("Temperature");
    String unit = (String) temperatura.get("Unit");
    Double valor = (Double) temperatura.get("Value");
    return (unit.equals("F") ? valor * (5 / 9) : valor);
  }



  private LocalDateTime proximaExpiracion() {
    return LocalDateTime.now().plus(this.periodoDeValidez);
  }

  private Map<String, Object> consultarApi() {
    return this.api.getWeather(direccion).get(0);
  }

  public boolean expiro() {
    return proximaExpiracion.isAfter(LocalDateTime.now());
  }

  public String getDireccion() {
    return direccion;
  }
}

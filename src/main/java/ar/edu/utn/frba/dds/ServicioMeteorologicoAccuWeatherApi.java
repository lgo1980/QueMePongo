package ar.edu.utn.frba.dds;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ServicioMeteorologicoAccuWeatherApi implements ServicioMeteorologico {

  private final AccuWeatherAPI api;
  private final Duration periodoDeValidez;
  private CondicionClimatica condicionClimatica;
  private final String direccion;
  private LocalDateTime proximaExpiracion;

  public ServicioMeteorologicoAccuWeatherApi(AccuWeatherAPI api, Duration periodoDeValidez, String direccion) {
    this.api = api;
    this.periodoDeValidez = periodoDeValidez;
    this.direccion = direccion;
  }

  public CondicionClimatica obtenerCondicionesClimaticas() {
    if (this.expiro()) {
      Map<String, Object> condicion = consultarApi();
      Double temperatura = Double.valueOf(condicion.get("Temperature").get("Unit").get("F")
          ? condicion.get("Temperature").get("Value") * 5 / 9
          : condicion.get("Temperature").get("Value"));
      condicionClimatica = new CondicionClimatica(temperatura);
      proximaExpiracion = LocalDateTime.now().plus(periodoDeValidez);
    }
    return condicionClimatica;
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
}

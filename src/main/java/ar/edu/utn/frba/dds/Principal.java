package ar.edu.utn.frba.dds;

import java.util.List;
import java.util.Map;

public class Principal {

  public static void main(String[] args) {
    AccuWeatherAPI apiClima = new AccuWeatherAPI();
    List<Map<String, Object>> condicionesClimaticas = apiClima.getWeather("Buenos Aires, Argentina");
    System.out.println("Valor: " + condicionesClimaticas.get(0).get("PrecipitationProbability")); //Devuelve un número del 0 al 1 “”
  }
}

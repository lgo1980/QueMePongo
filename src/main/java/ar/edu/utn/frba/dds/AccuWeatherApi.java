package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AccuWeatherApi {

  public final List<Map<String, Object>> getWeather(String ciudad) {
    return List.of(new HashMap<String, Object>() {{
        put("DateTime", "2019-05-03T01:00:00-03:00");
        put("EpochDateTime", 1556856000);
        put("WeatherIcon", 33);
        put("IconPhrase", "Clear");
        put("IsDaylight", false);
        put("PrecipitationProbability", 0.5);
        put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
        put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
        put("Temperature", new HashMap<String, Object>() {{
            put("Value", 57);
            put("Unit", "F");
            put("UnitType", 18);
          }
        });
      }
    });
  }

  Map<String, List<String>> getAlerts(String ciudad) {
    final Map<String, List<String>> mapaRetorno = new HashMap<>();
    List<String> lista = new ArrayList<>();
    lista.add("STORM");
    lista.add("HAIL");
    lista.add("HURRICANE");
    return (Map<String, List<String>>) mapaRetorno.put("CurrentAlerts", lista);
  }
}

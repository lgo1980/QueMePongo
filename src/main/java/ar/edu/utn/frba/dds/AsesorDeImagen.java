package ar.edu.utn.frba.dds;

import java.util.List;

public class AsesorDeImagen {

  private ServicioMeteorologicoAccuWeatherApi servicioMeteorologico;

  public AsesorDeImagen(ServicioMeteorologicoAccuWeatherApi servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

  public ServicioMeteorologicoAccuWeatherApi getServicioMeteorologico() {
    return servicioMeteorologico;
  }

  public void setServicioMeteorologico(ServicioMeteorologicoAccuWeatherApi servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

  public Uniforme sugerirUniforme(List<Uniforme> uniformes) {
    CondicionClimatica estadoDelTiempo = servicioMeteorologico
        .obtenerCondicionesClimaticas();

    return uniformes.stream()
        .filter(combinacion -> combinacion.esAptaParaLaTemperatura(estadoDelTiempo.getTemperatura()))
        .findFirst()
        .orElse(null);
  }

}

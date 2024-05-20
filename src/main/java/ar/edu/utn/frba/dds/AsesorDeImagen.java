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

  public Uniforme sugerirUniforme(List<Uniforme> combinaciones) {
    CondicionClimatica estadoDelTiempo = servicioMeteorologico
        .obtenerCondicionesClimaticas();

    return combinaciones.stream()
        .filter(combinacion -> combinacion.esAptaParaLaTemperatura(estadoDelTiempo.getTemperatura()))
        .anyMatch();
  }

}

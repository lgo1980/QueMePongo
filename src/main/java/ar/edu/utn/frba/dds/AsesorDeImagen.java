package ar.edu.utn.frba.dds;

import java.util.List;

public class AsesorDeImagen {

  private final ServicioMeteorologico servicioMeteorologico;

  public AsesorDeImagen(ServicioMeteorologico servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

  public Uniforme sugerirUniforme(List<Uniforme> uniformes) {
    if (uniformes.isEmpty()) {
      throw new RuntimeException("La lista de uniformes no puede ser vacia");
    }
    CondicionClimatica estadoDelTiempo = servicioMeteorologico
        .obtenerCondicionesClimaticas();
    return uniformes.stream()
        .filter(combinacion -> combinacion.esAptaParaLaTemperatura(estadoDelTiempo.temperatura()))
        .findFirst()
        .orElse(null);
  }

}

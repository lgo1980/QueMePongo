package ar.edu.utn.frba.dds;

public class CondicionClimatica {

  private final Double temperatura;
//  private Double humedad;

  public CondicionClimatica(Double temperatura) {
    this.temperatura = temperatura;
  }

  public Double getTemperatura() {
    return temperatura;
  }
}

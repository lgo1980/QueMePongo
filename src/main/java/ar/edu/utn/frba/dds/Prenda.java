package ar.edu.utn.frba.dds;

public record Prenda(TipoPrenda tipoPrenda, Material material, Trama trama,
                     Color colorPrimario, Color colorSecundario,
                     Clase clase, Double temperaturaMaxima) {

  Boolean esAptaParaLaTemperatura(Double temperatura) {
    return temperatura < temperaturaMaxima;
  }
}

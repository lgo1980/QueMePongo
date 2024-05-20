package ar.edu.utn.frba.dds;

public class Prenda {

  private final TipoPrenda tipoPrenda;
  private final Material material;
  private final Trama trama;
  private final Color colorPrimario;
  private final Color colorSecundario;
  private final Clase clase;
  private final Double temperaturaMaxima;

  public Prenda(TipoPrenda tipoPrenda, Material material, Trama trama, Color colorPrimario, Color colorSecundario, Clase clase, Double temperaturaMaxima) {
    this.tipoPrenda = tipoPrenda;
    this.material = material;
    this.trama = trama;
    this.colorPrimario = colorPrimario;
    this.colorSecundario = colorSecundario;
    this.clase = clase;
    this.temperaturaMaxima = temperaturaMaxima;
  }

  public TipoPrenda getTipoPrenda() {
    return tipoPrenda;
  }

  public Material getMaterial() {
    return material;
  }

  public Trama getTrama() {
    return trama;
  }

  public Color getColorPrimario() {
    return colorPrimario;
  }

  public Color getColorSecundario() {
    return colorSecundario;
  }

  public Clase getClase() {
    return clase;
  }

  public Double getTemperaturaMaxima() {
    return temperaturaMaxima;
  }

  Boolean isAptaParaLaTemperatura(Double temperatura) {
    return temperatura < temperaturaMaxima;
  }
}

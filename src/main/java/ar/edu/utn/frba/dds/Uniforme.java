package ar.edu.utn.frba.dds;

public class Uniforme {

  private Prenda calzado;
  private Prenda parteInferior;
  private Prenda parteSuperior;

  public Uniforme(Prenda calzado,
                  Prenda parteInferior, Prenda parteSuperior) {
    if (calzado.tipoPrenda().getCategoria()
        != Categoria.CALZADO) {
      throw new IllegalArgumentException(
          "Se ingreso una prenda como calzado, que no lo era");
    }
    if (parteInferior.tipoPrenda().getCategoria()
        != Categoria.PARTE_INFERIOR) {
      throw new IllegalArgumentException(
          "Se ingreso una prenda como parte inferior, que no lo era");
    }
    if (parteSuperior.tipoPrenda().getCategoria()
        != Categoria.PARTE_SUPERIOR) {
      throw new IllegalArgumentException(
          "Se ingreso una prenda como parte superior, que no lo era");
    }
    this.calzado = calzado;
    this.parteInferior = parteInferior;
    this.parteSuperior = parteSuperior;
  }

  public Prenda getCalzado() {
    return calzado;
  }

  public void setCalzado(Prenda calzado) {
    this.calzado = calzado;
  }

  public Prenda getParteInferior() {
    return parteInferior;
  }

  public void setParteInferior(Prenda parteInferior) {
    this.parteInferior = parteInferior;
  }

  public Prenda getParteSuperior() {
    return parteSuperior;
  }

  public void setParteSuperior(Prenda parteSuperior) {
    this.parteSuperior = parteSuperior;
  }

  Boolean esAptaParaLaTemperatura(Double temperatura) {
    return calzado.temperaturaMaxima() < temperatura
        && parteInferior.temperaturaMaxima() < temperatura
        && parteSuperior.temperaturaMaxima() < temperatura;
  }

}

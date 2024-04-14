package ar.edu.utn.frba.dds;

public enum TipoPrenda {
  CAMISA(Categoria.DORSO),
  PANTALON(Categoria.CADERA),
  REMERA(Categoria.DORSO),
  CORBATA(Categoria.DORSO),
  ZAPATILLA(Categoria.CALZADO),
  ZAPATO(Categoria.CALZADO),
  PANTALON_CORTO(Categoria.CADERA),
  SOMBRERO(Categoria.CABEZA);

  private final Categoria categoria;

  TipoPrenda(Categoria categoria) {
    this.categoria = categoria;
  }

  public Categoria getCategoria() {
    return categoria;
  }
}

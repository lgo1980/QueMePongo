package ar.edu.utn.frba.dds;

public enum TipoPrenda {
  CAMISA(Categoria.PARTE_SUPERIOR),
  PANTALON(Categoria.PARTE_INFERIOR),
  REMERA(Categoria.PARTE_SUPERIOR),
  CORBATA(Categoria.PARTE_SUPERIOR),
  ZAPATILLA(Categoria.CALZADO),
  ZAPATO(Categoria.CALZADO),
  PANTALON_CORTO(Categoria.PARTE_INFERIOR),
  SOMBRERO(Categoria.PARTE_SUPERIOR);

  private final Categoria categoria;

  TipoPrenda(Categoria categoria) {
    this.categoria = categoria;
  }

  public Categoria getCategoria() {
    return categoria;
  }
}

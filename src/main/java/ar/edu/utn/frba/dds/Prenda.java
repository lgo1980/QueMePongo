package ar.edu.utn.frba.dds;

public class Prenda {

  private final TipoPrenda tipo;
  private final Material material;
  private final Color colorPrimario;
  private final Color colorSecundario;

  public Prenda(TipoPrenda tipo, Material material, Color colorPrimario, Color colorSecundario) {
    if (tipo == null)
      throw new IllegalArgumentException("Especificar el tipo de prenda");
    if (material == null)
      throw new IllegalArgumentException("Especificar el tipo de material");
    if (colorPrimario == null)
      throw new IllegalArgumentException("Especificar el tipo de colorprimario");
    this.tipo = tipo;
    this.material = material;
    this.colorPrimario = colorPrimario;
    this.colorSecundario = colorSecundario;
  }

  public TipoPrenda getTipo() {
    return tipo;
  }

  public Material getMaterial() {
    return material;
  }

  public Color getColorPrimario() {
    return colorPrimario;
  }

  public Color getColorSecundario() {
    return colorSecundario;
  }

  /*public void esValidaLaPrenda() {
    if (this.tipo == null)
      throw new IllegalArgumentException("Especificar el tipo de prenda");
    *//*if (this.categoria == null)
      throw new IllegalArgumentException("Especificar el tipo de categoria");*//*
    if (this.material == null)
      throw new IllegalArgumentException("Especificar el tipo de material");
    if (this.colorPrimario == null)
      throw new IllegalArgumentException("Especificar el tipo de colorprimario");
  }*/
}

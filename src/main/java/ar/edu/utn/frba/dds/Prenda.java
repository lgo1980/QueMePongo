package ar.edu.utn.frba.dds;

public class Prenda {

  private TipoPrenda tipo;
  private Material material;
  private Color colorPrimario;
  private Color colorSecundario;

  public Prenda() {
  }

  public Prenda(TipoPrenda tipo, Material material, Color colorPrimario, Color colorSecundario) {
    this.setTipo(tipo);
    this.setMaterial(material);
    this.setColorPrimario(colorPrimario);
    this.colorSecundario = colorSecundario;
  }

  public TipoPrenda getTipo() {
    return tipo;
  }

  public void setTipo(TipoPrenda tipo) {
    if (tipo == null)
      throw new IllegalArgumentException("Especificar el tipo de prenda");
    this.tipo = tipo;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    if (material == null)
      throw new IllegalArgumentException("Especificar el tipo de material");
    this.material = material;
  }

  public Color getColorPrimario() {
    return colorPrimario;
  }

  public void setColorPrimario(Color colorPrimario) {
    if (colorPrimario == null)
      throw new IllegalArgumentException("Especificar el tipo de color primario");
    this.colorPrimario = colorPrimario;
  }

  public Color getColorSecundario() {
    return colorSecundario;
  }

  public void setColorSecundario(Color colorSecundario) {
    this.colorSecundario = colorSecundario;
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

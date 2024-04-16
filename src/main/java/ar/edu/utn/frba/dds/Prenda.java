package ar.edu.utn.frba.dds;

public class Prenda {

  private TipoPrenda tipoPrenda;
  private Material material;
  private Color colorPrimario;
  private Color colorSecundario;
  private boolean guardada = false;

  public Prenda() {
  }

  public Prenda(TipoPrenda tipoPrenda, Material material,
                Color colorPrimario, Color colorSecundario) {
    this.especificarTipo(tipoPrenda);
    this.especificarResto(material, colorPrimario, colorSecundario);
  }

  public void especificarTipo(TipoPrenda tipoPrenda) {
    if (tipoPrenda == null)
      throw new IllegalArgumentException("Especificar el tipo de prenda");
    this.tipoPrenda = tipoPrenda;
  }

  public void especificarResto(Material material, Color colorPrimario, Color colorSecundario) {
    if (material == null)
      throw new IllegalArgumentException("Especificar el tipo de material");
    this.material = material;
    if (colorPrimario == null)
      throw new IllegalArgumentException("Especificar el tipo de color primario");
    this.colorPrimario = colorPrimario;
    this.colorSecundario = colorSecundario;
  }

  public TipoPrenda getTipoPrenda() {
    return tipoPrenda;
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

  public boolean isGuardada() {
    return guardada;
  }

  public Prenda copy() {
    return new Prenda(this.tipoPrenda, this.material, this.colorPrimario, this.colorSecundario);
  }

  public void guardar() {
    if (this.tipoPrenda == null || this.material == null)
      throw new IllegalArgumentException("¡No se pudo guardar la prenda! La prenda no es valida");
    this.guardada = true;
  }
}

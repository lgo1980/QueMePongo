package ar.edu.utn.frba.dds;

import java.util.Optional;

public class BorradorPrenda {

  private TipoPrenda tipoPrenda;
  private Material material;
  private Trama trama = Trama.LISA;
  private Color colorPrimario;
  private Color colorSecundario;

  public TipoPrenda getTipoPrenda() {
    return tipoPrenda;
  }

  public void setTipoPrenda(TipoPrenda tipoPrenda) {
    this.validarTipoNoNulo(tipoPrenda);
    this.tipoPrenda = tipoPrenda;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.validarTipoNoNulo(tipoPrenda);
    if (material == null) throw new IllegalArgumentException("Especificar el tipo de material");
    this.material = material;
  }

  public Trama getTrama() {
    return trama;
  }

  public void setTrama(Trama trama) {
    this.validarTipoNoNulo(tipoPrenda);
    this.trama = Optional.ofNullable(trama).orElse(Trama.LISA);
  }

  public Color getColorPrimario() {
    return colorPrimario;
  }

  public void setColorPrimario(Color colorPrimario) {
    this.validarTipoNoNulo(tipoPrenda);
    if (material == null) throw new IllegalArgumentException("Especificar el tipo de color primario");
    this.colorPrimario = colorPrimario;
  }

  public Color getColorSecundario() {
    return colorSecundario;
  }

  public void setColorSecundario(Color colorSecundario) {
    this.validarTipoNoNulo(tipoPrenda);
    this.colorSecundario = colorSecundario;
  }

  private void validarTipoNoNulo(TipoPrenda tipoPrenda) {
    if (tipoPrenda == null)
      throw new IllegalArgumentException("Especificar el tipo de prenda");
  }

  public Prenda guardarPrenda() {
    return new Prenda(this.tipoPrenda, this.material, this.trama, this.colorPrimario, this.colorSecundario);
  }
}

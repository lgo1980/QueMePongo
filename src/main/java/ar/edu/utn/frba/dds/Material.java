package ar.edu.utn.frba.dds;

public class Material {

  private String nombre;
  private Trama trama;

  public Material(String nombre) {
    this.nombre = nombre;
    this.trama = Trama.LISA;
  }

  public Material(String nombre, Trama trama) {
    this.nombre = nombre;
    this.trama = trama;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Trama getTrama() {
    return trama;
  }

  public void setTrama(Trama trama) {
    this.trama = trama;
  }
}

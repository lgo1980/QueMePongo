package ar.edu.utn.frba.dds;

public class Institucion {

  private String nombre;
  private Uniforme uniforme;

  public void configurarUniforme(String nombre, Uniforme uniforme) {
    this.validarAdministrador(nombre, uniforme);
    this.nombre = nombre;
    this.uniforme = uniforme;
  }

  public String getNombre() {
    return nombre;
  }

  public Uniforme getUniforme() {
    return uniforme;
  }

  private void validarAdministrador(String nombre, Uniforme uniforme) {
    if (nombre == null) {
      throw new IllegalArgumentException("Debe ingresar un nombre de la institucion");
    }
    if (uniforme == null) {
      throw new IllegalArgumentException("Debe ingresar un uniforme de la institucion");
    }
  }

}

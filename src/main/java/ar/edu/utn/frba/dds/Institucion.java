package ar.edu.utn.frba.dds;

public class Institucion {

  private String nombre;
  private Uniforme uniforme;

  public void configurarUniforme(String nombre, Uniforme uniforme, UsuarioAdmin usuarioAdmin) {
    this.validarAdministrador(usuarioAdmin);
    this.nombre = nombre;
    this.uniforme = uniforme;
  }

  public String getNombre() {
    return nombre;
  }

  public Uniforme getUniforme() {
    return uniforme;
  }

  private void validarAdministrador(UsuarioAdmin usuarioAdmin) {
    if (usuarioAdmin == null)
      throw new IllegalArgumentException("Solo un administrador puede configurar uniformes");
  }
}

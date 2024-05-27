package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

  private final List<Guardarropa> guardarropas;
  private int edad;

  public Usuario(int edad) {
    this.edad = edad;
    guardarropas = new ArrayList<>();
  }

  public Usuario(List<Guardarropa> guardarropas, int edad) {
    validarGuardarropa(guardarropas);
    this.guardarropas = guardarropas;
    this.edad = edad;
  }

  private static void validarGuardarropa(List<Guardarropa> guardarropas) {
    if (guardarropas == null || guardarropas.isEmpty()) {
      throw new IllegalArgumentException("Debe ingresar una lista de guardarropas validas");
    }
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public List<Guardarropa> getGuardarropas() {
    return guardarropas;
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    this.guardarropas.add(guardarropa);
  }

  public void removerGuardarropa(Guardarropa guardarropa) {
    this.guardarropas.remove(guardarropa);
  }

}

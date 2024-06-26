package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

  private final List<Guardarropa> guardarropas;
  private int edad;
  private List<AccionParaAlertasMeteorologicas> accionParaAlertasMeteorologicas;

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

  public List<AccionParaAlertasMeteorologicas> getAccionParaAlertasMeteorologicas() {
    return accionParaAlertasMeteorologicas;
  }

  public void setAccionParaAlertasMeteorologicas(
      List<AccionParaAlertasMeteorologicas> accionParaAlertasMeteorologicas) {
    this.accionParaAlertasMeteorologicas = accionParaAlertasMeteorologicas;
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    this.guardarropas.add(guardarropa);
  }

  public void removerGuardarropa(Guardarropa guardarropa) {
    this.guardarropas.remove(guardarropa);
  }

  void calcularSugerenciaDiaria() {
    guardarropas.forEach(Guardarropa::sugerenciaDiaria);
  }

  void realizarAccionesSobreAlertas(List<AlertaMeteorologica> alertas) {
    accionParaAlertasMeteorologicas.forEach(accion ->
        accion.nuevasAlertas(alertas, this));
  }
}

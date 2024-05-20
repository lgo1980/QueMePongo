package ar.edu.utn.frba.dds;

import java.util.List;

public class Usuario {

  private List<Prenda> prendas;
  private int edad;
  private MotorSugerencia motor;

  public Usuario(List<Prenda> prendas, int edad, MotorSugerencia motor) {
    validarPrenda(prendas);
    this.prendas = prendas;
    this.edad = edad;
    this.motor = motor;
  }

  private static void validarPrenda(List<Prenda> prendas) {
    if (prendas == null || prendas.isEmpty()) {
      throw new IllegalArgumentException("Debe ingresar una lista de prendas validas");
    }
  }

  public List<Prenda> getPrendas() {
    return prendas;
  }

  public void setPrendas(List<Prenda> prendas) {
    validarPrenda(prendas);
    this.prendas = prendas;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public MotorSugerencia getMotor() {
    return motor;
  }

  public void setMotor(MotorSugerencia motor) {
    this.motor = motor;
  }

  public List<Uniforme> recibirSugerencias() {
    List<Uniforme> uniformesSugeridos = motor.generarSugerencias(this);
    return motor.generarSugerencias(this);
  }
}

package ar.edu.utn.frba.dds;

import java.util.List;

public class Guardarropa {

  private final List<Prenda> prendas;
  private CriterioGuardarropa criterioGuardarropa;
  private final Usuario usuarioCreador;
  private MotorSugerencia motor;
  private final AsesorDeImagen asesorDeImagen;
  private List<SugerirAccion> sugerenciasDeAcciones;
  private Uniforme uniformeDelDia;

  public Guardarropa(List<Prenda> prendas, CriterioGuardarropa criterioGuardarropa,
                     Usuario usuarioCreador, MotorSugerencia motor, AsesorDeImagen asesorDeImagen) {
    validarPrenda(prendas);
    this.prendas = prendas;
    this.criterioGuardarropa = criterioGuardarropa;
    this.usuarioCreador = usuarioCreador;
    this.motor = motor;
    this.asesorDeImagen = asesorDeImagen;
  }

  public List<Prenda> getPrendas() {
    return prendas;
  }

  public void agregarPrenda(Prenda prenda) {
    this.prendas.add(prenda);
  }

  public void removerPrenda(Prenda prenda) {
    this.prendas.remove(prenda);
  }

  public CriterioGuardarropa getCriterioGuardarropa() {
    return criterioGuardarropa;
  }

  public void setCriterioGuardarropa(CriterioGuardarropa criterioGuardarropa) {
    this.criterioGuardarropa = criterioGuardarropa;
  }

  public Usuario getUsuarioCreador() {
    return usuarioCreador;
  }

  public MotorSugerencia getMotor() {
    return motor;
  }

  public void setMotor(MotorSugerencia motor) {
    this.motor = motor;
  }

  public AsesorDeImagen getAsesorDeImagen() {
    return asesorDeImagen;
  }

  private static void validarPrenda(List<Prenda> prendas) {
    if (prendas == null || prendas.isEmpty()) {
      throw new IllegalArgumentException("Debe ingresar una lista de prendas validas");
    }
  }

  public List<Uniforme> recibirSugerencias() {
    return motor.generarSugerencias(this);
  }

  Uniforme recibirSugerenciaPorTemperatura() {
    List<Uniforme> uniformesSugeridos = motor.generarSugerencias(this);
    return asesorDeImagen.sugerirUniforme(uniformesSugeridos);
  }

  public List<SugerirAccion> getSugerenciasDeAcciones() {
    return sugerenciasDeAcciones;
  }

  void agregarSugerenciaDeAccion(SugerirAccion sugerirAccion) {
    sugerenciasDeAcciones.add(sugerirAccion);
  }

  void removerSugerenciaDeAccion(SugerirAccion sugerirAccion) {
    sugerenciasDeAcciones.remove(sugerirAccion);
  }

  void sugerenciaDiaria() {
    uniformeDelDia = asesorDeImagen.sugerirUniforme(motor.generarSugerencias(this));
  }

  public Uniforme getUniformeDelDia() {
    return uniformeDelDia;
  }
}

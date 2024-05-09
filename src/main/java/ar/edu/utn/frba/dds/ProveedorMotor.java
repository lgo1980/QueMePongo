package ar.edu.utn.frba.dds;

public class ProveedorMotor {

  static MotorSugerencia motor;

  static MotorSugerencia getMotor() {
    return motor;
  }

  static void setMotor(MotorSugerencia motor) {
    ProveedorMotor.motor = motor;
  }
}

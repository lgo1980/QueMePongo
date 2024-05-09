package ar.edu.utn.frba.dds;

import java.util.List;

public class MotorBasico extends MotorSugerencia {

  @Override
  List<Prenda> devolverUsuario(Usuario usuario) {
    return usuario.getPrendas();
  }
}

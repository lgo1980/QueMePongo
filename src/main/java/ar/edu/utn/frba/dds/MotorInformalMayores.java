package ar.edu.utn.frba.dds;

import java.util.List;

public class MotorInformalMayores extends MotorSugerencia {


  @Override
  List<Prenda> devolverUsuario(Usuario usuario) {
    return usuario.getEdad() > 55
        ? usuario.getPrendas().stream().filter(
            prenda -> prenda.getClase() == Clase.INFORMAL)
        .toList()
        : usuario.getPrendas();
  }
}

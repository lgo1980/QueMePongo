package ar.edu.utn.frba.dds;

import java.util.List;

public class MotorInformalMayores extends MotorSugerencia {


  @Override
  List<Prenda> devolverUsuario(Guardarropa guardarropa) {
    return guardarropa.getUsuarioCreador().getEdad() > 55
        ? guardarropa.getPrendas().stream().filter(
            prenda -> prenda.clase() == Clase.INFORMAL)
        .toList()
        : guardarropa.getPrendas();
  }
}

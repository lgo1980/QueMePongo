package ar.edu.utn.frba.dds;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MotorSugerencia {

  List<Uniforme> generarSugerencias(Guardarropa guardarropa) {
    List<Prenda> prendas = devolverUsuario(guardarropa);
    List<Prenda> prendasSuperiores = prendas.stream()
        .filter(prenda -> prenda.tipoPrenda().getCategoria()
            == Categoria.PARTE_SUPERIOR)
        .toList();
    List<Prenda> prendasInferiores = prendas.stream()
        .filter(prenda -> prenda.tipoPrenda().getCategoria()
            == Categoria.PARTE_INFERIOR)
        .toList();
    List<Prenda> prendasCalzados = prendas.stream()
        .filter(prenda -> prenda.tipoPrenda().getCategoria()
            == Categoria.CALZADO)
        .toList();
    List<List<Prenda>> sugerencias = Lists.cartesianProduct(
        prendasSuperiores, prendasInferiores, prendasCalzados);
    return sugerencias.stream()
        .map(sugerencia -> new Uniforme(
            sugerencia.get(2), sugerencia.get(1), sugerencia.get(0)))
        .collect(Collectors.toList());
  }

  abstract List<Prenda> devolverUsuario(Guardarropa guardarropa);
}

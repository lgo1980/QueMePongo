package ar.edu.utn.frba.dds;

import java.util.List;

public abstract class SugerirAccion {

  private final Prenda prenda;
  private EstadoSugerirAccion estadoSugerirAccion;

  public SugerirAccion(Prenda prenda) {
    if (prenda == null) {
      throw new IllegalArgumentException("Debe ingresar una prenda para sugerir acción");
    }
    this.prenda = prenda;
    estadoSugerirAccion = EstadoSugerirAccion.PENDIENTE;
  }

  public Prenda getPrenda() {
    return prenda;
  }

  public EstadoSugerirAccion getEstadoSugerirAccion() {
    return estadoSugerirAccion;
  }

  public void setEstadoSugerirAccion(EstadoSugerirAccion estadoSugerirAccion) {
    this.estadoSugerirAccion = estadoSugerirAccion;
  }

  void aceptarSugerencia(Guardarropa guardarropa) {
    estadoSugerirAccion = EstadoSugerirAccion.ACEPTADA;
    aplicarAceptarSugerencia(guardarropa);
  }

  void rechazarSugerencia(Guardarropa guardarropa) {
    estadoSugerirAccion = EstadoSugerirAccion.RECHAZADA;
  }

  void deshacerAccion(Guardarropa guardarropa) {
    List<SugerirAccion> sugerenciasDeAccionesAceptadas = guardarropa.getSugerenciasDeAcciones()
        .stream().filter(sugerirAccion ->
            sugerirAccion.getEstadoSugerirAccion() == EstadoSugerirAccion.ACEPTADA)
        .toList();
    sugerenciasDeAccionesAceptadas.forEach(
        sugerirAccion -> {
          sugerirAccion.setEstadoSugerirAccion(EstadoSugerirAccion.PENDIENTE);
          aplicarDeshacerAccion(guardarropa);
        }
    );
  }

  abstract void aplicarAceptarSugerencia(Guardarropa guardarropa);

  abstract void aplicarDeshacerAccion(Guardarropa guardarropa);

}

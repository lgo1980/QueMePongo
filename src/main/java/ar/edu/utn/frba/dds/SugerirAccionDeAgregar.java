package ar.edu.utn.frba.dds;

public class SugerirAccionDeAgregar extends SugerirAccion {

  public SugerirAccionDeAgregar(Prenda prenda) {
    super(prenda);
  }

  @Override
  void aplicarAceptarSugerencia(Guardarropa guardarropa) {
    guardarropa.agregarPrenda(getPrenda());
  }

  @Override
  void aplicarDeshacerAccion(Guardarropa guardarropa) {
    guardarropa.removerPrenda(getPrenda());
  }

}

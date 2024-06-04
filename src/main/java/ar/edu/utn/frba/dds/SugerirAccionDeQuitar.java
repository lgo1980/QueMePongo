package ar.edu.utn.frba.dds;

public class SugerirAccionDeQuitar extends SugerirAccion {

  public SugerirAccionDeQuitar(Prenda prenda) {
    super(prenda);
  }

  @Override
  void aplicarAceptarSugerencia(Guardarropa guardarropa) {
    guardarropa.removerPrenda(getPrenda());
  }

  @Override
  void aplicarDeshacerAccion(Guardarropa guardarropa) {
    guardarropa.agregarPrenda(getPrenda());
  }

}

package ar.edu.utn.frba.dds;

public abstract class SugerirAccion {

  private Prenda prenda;

  abstract void aceptarPrenda(Guardarropa guardarropa);

  abstract void removerPrenda(Guardarropa guardarropa);

  abstract void deshacerAccion(Guardarropa guardarropa);

}

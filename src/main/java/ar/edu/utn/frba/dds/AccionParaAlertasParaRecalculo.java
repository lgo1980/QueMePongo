package ar.edu.utn.frba.dds;

import java.util.List;

public class AccionParaAlertasParaRecalculo implements AccionParaAlertasMeteorologicas {

  @Override
  public void nuevasAlertas(List<AlertaMeteorologica> alertasMeteorologicas, Usuario usuario) {
    usuario.calcularSugerenciaDiaria();
  }
}

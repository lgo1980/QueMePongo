package ar.edu.utn.frba.dds;

import java.util.List;

public interface AccionParaAlertasMeteorologicas {

  void nuevasAlertas(List<AlertaMeteorologica> alertasMeteorologicas, Usuario usuario);
}

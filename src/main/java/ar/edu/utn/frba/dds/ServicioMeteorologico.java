package ar.edu.utn.frba.dds;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ServicioMeteorologico {
  CondicionClimatica obtenerCondicionesClimaticas();

  List<AlertaMeteorologica> obtenerAlertaMeteorologica();
}

package ar.edu.utn.frba.dds;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AccionParaAlertasParaEmail implements AccionParaAlertasMeteorologicas {

  private final CorreoElectronico correoElectronico;

  public AccionParaAlertasParaEmail(CorreoElectronico correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  @Override
  public void nuevasAlertas(List<AlertaMeteorologica> alertasMeteorologicas, Usuario usuario) {
    AtomicReference<String> cuerpo = new AtomicReference<>("Las alertas que se generaron fueron: ");
    alertasMeteorologicas.forEach(alertaMeteorologica -> {
      cuerpo.set(cuerpo.get() + alertaMeteorologica.name() + " - ");
    });
    correoElectronico.enviarCorreo(usuario, cuerpo.get());
  }
}

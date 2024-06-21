package ar.edu.utn.frba.dds;

import java.util.List;

public class AccionParaAlertasParaEmail implements AccionParaAlertasMeteorologicas {

  private final CorreoElectronico correoElectronico;

  public AccionParaAlertasParaEmail(CorreoElectronico correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  @Override
  public void nuevasAlertas(List<AlertaMeteorologica> alertasMeteorologicas, Usuario usuario) {
    alertasMeteorologicas.forEach(alertaMeteorologica -> correoElectronico
        .enviarCorreo(usuario, "La alerta que se genero fue" + alertaMeteorologica.name()));
  }
}

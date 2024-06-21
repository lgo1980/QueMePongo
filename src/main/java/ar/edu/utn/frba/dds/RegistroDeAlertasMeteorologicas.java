package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class RegistroDeAlertasMeteorologicas {
  private final ServicioMeteorologico servicioMeteorologico;
  private List<AlertaMeteorologica> alertaMeteorologicas;
  private final UsuarioRepositorio usuarioRepositorio;

  public RegistroDeAlertasMeteorologicas(
      ServicioMeteorologico servicioMeteorologico, UsuarioRepositorio usuarioRepositorio) {
    this.servicioMeteorologico = servicioMeteorologico;
    this.usuarioRepositorio = usuarioRepositorio;
    this.alertaMeteorologicas = new ArrayList<>();
  }

  public void actualizarAlertas() {
    alertaMeteorologicas = servicioMeteorologico.obtenerAlertaMeteorologica();
    if (!alertaMeteorologicas.isEmpty()) {
      usuarioRepositorio.usuarios().forEach(usuario ->
          usuario.realizarAccionesSobreAlertas(alertaMeteorologicas));
    }
  }

  public List<AlertaMeteorologica> getAlertaMeteorologicas() {
    return alertaMeteorologicas;
  }
}

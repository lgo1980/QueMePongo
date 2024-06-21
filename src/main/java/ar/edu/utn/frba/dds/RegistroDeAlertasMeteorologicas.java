package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class RegistroDeAlertasMeteorologicas {
  private final ServicioMeteorologico servicioMeteorologico;
  private List<AlertaMeteorologica> alertaMeteorologicas;
  private UsuarioRepositorio usuarioRepositorio;

  public RegistroDeAlertasMeteorologicas(
      ServicioMeteorologico servicioMeteorologico,
      List<AlertaMeteorologica> alertaMeteorologicas) {
    this.servicioMeteorologico = servicioMeteorologico;
    this.alertaMeteorologicas = (alertaMeteorologicas.isEmpty())
        ? new ArrayList<>() : alertaMeteorologicas;
  }

  public void actualizarAlertas() {
    alertaMeteorologicas = servicioMeteorologico.obtenerAlertaMeteorologica();
    usuarioRepositorio.getUsuarios().forEach(usuario ->
        usuario.realizarAccionesSobreAlertas(alertaMeteorologicas));
  }


}

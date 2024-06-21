package ar.edu.utn.frba.dds;

import java.util.List;

public class AccionParaAlertasParaNotificacion implements AccionParaAlertasMeteorologicas {

  private final NotificationService notificationService;

  public AccionParaAlertasParaNotificacion(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @Override
  public void nuevasAlertas(List<AlertaMeteorologica> alertasMeteorologicas, Usuario usuario) {
    if (alertasMeteorologicas.contains(AlertaMeteorologica.TORMENTA)) {
      notificationService.notificar("Sali con paraguas!");
    } else if (alertasMeteorologicas.contains(AlertaMeteorologica.GRANIZO)) {
      notificationService.notificar("No salgas en auto!");
    }
  }
}

package ar.edu.utn.frba.dds;

public class CorreoElectronicoMailSender implements CorreoElectronico {

  private final MailSender mailSender;

  public CorreoElectronicoMailSender(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarCorreo(Usuario usuario, String cuerpo) {
    mailSender.enviarCorreo(usuario, cuerpo);
  }
}

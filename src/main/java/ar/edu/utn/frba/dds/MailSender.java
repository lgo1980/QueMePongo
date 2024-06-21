package ar.edu.utn.frba.dds;

public interface MailSender {
  void enviarCorreo(Usuario usuario, String cuerpo);
}

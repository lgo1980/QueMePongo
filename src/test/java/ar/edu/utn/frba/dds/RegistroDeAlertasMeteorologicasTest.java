package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RegistroDeAlertasMeteorologicasTest {

  private ServicioMeteorologico servicioMeteorologicoApi;
  private RegistroDeAlertasMeteorologicas registroDeAlertasMeteorologicas;
  private MailSender mailer;
  private NotificationService notificador;
  private AccionParaAlertasParaRecalculo accionRecalculo;
  private Usuario usuarioCon3PrendasDeCadaCategoria;

  @BeforeEach
  public void setUp() {
    ProveedorMotor.setMotor(new MotorBasico());
    servicioMeteorologicoApi = mock(ServicioMeteorologico.class);
    mailer = mock(MailSender.class);
    notificador = mock(NotificationService.class);
    accionRecalculo = mock(AccionParaAlertasParaRecalculo.class);
    AsesorDeImagen asesorDeImagen = new AsesorDeImagen(servicioMeteorologicoApi);
    Prenda remera1 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3),
        null, Clase.INFORMAL, 20D);
    Prenda remera2 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3),
        null, Clase.NEUTRAL, 20D);
    Prenda remera3 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3),
        null, Clase.FORMAL, 20D);
    Prenda pantalon1 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3),
        null, Clase.NEUTRAL, 20D);
    Prenda pantalon2 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3),
        null, Clase.INFORMAL, 20D);
    Prenda pantalon3 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3),
        null, Clase.FORMAL, 20D);
    Prenda zapatilla1 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3),
        null, Clase.INFORMAL, 20D);
    Prenda zapatilla2 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3),
        null, Clase.NEUTRAL, 20D);
    Prenda zapatilla3 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3),
        null, Clase.FORMAL, 20D);
    Guardarropa guardarropaCon3PrendasDeCadaCategoria;
    List<Prenda> prendas = new ArrayList<>();
    prendas.add(remera1);
    prendas.add(remera2);
    prendas.add(remera3);
    prendas.add(pantalon1);
    prendas.add(pantalon2);
    prendas.add(pantalon3);
    prendas.add(zapatilla1);
    prendas.add(zapatilla2);
    prendas.add(zapatilla3);
    usuarioCon3PrendasDeCadaCategoria = new Usuario(32);
    guardarropaCon3PrendasDeCadaCategoria = new Guardarropa(prendas,
        CriterioGuardarropa.ROPA_DE_ENTRECASA, usuarioCon3PrendasDeCadaCategoria,
        ProveedorMotor.getMotor(), asesorDeImagen);
    usuarioCon3PrendasDeCadaCategoria.agregarGuardarropa(guardarropaCon3PrendasDeCadaCategoria);
    UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(new ArrayList<>());
    usuarioRepositorio.agregarUsuario(usuarioCon3PrendasDeCadaCategoria);
    registroDeAlertasMeteorologicas = new RegistroDeAlertasMeteorologicas(
        servicioMeteorologicoApi, usuarioRepositorio);
  }

  @DisplayName("Si hay alertas de Granizo y tormenta y el usuario tiene activadas todas las alertas" +
      " se avisan a los usuarios los mensajes correspondientes")
  @Test
  public void actualizarLasAlertasConGranizoYTormentaConUsuarioConTodasLasAlertas() {
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    listaDeAcciones.add(new AccionParaAlertasParaNotificacion(notificador));
    CorreoElectronico correoElectronico = new CorreoElectronicoMailSender(mailer);
    listaDeAcciones.add(new AccionParaAlertasParaEmail(correoElectronico));
    listaDeAcciones.add(accionRecalculo);
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas(listaDeAcciones);
    List<AlertaMeteorologica> lista = new ArrayList<>();
    lista.add(AlertaMeteorologica.GRANIZO);
    lista.add(AlertaMeteorologica.TORMENTA);
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.only()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.only()).notificar(Mockito.any());
    verify(accionRecalculo, Mockito.only()).nuevasAlertas(lista, usuarioCon3PrendasDeCadaCategoria);
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
  }

  @DisplayName("Si hay alertas de tormenta solamente y el usuario tiene activadas todas las alertas" +
      " se avisan a los usuarios los mensajes correspondientes")
  @Test
  public void actualizarLasAlertasConTormentaConUsuarioConTodasLasAlertas() {
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    listaDeAcciones.add(new AccionParaAlertasParaNotificacion(notificador));
    CorreoElectronico correoElectronico = new CorreoElectronicoMailSender(mailer);
    listaDeAcciones.add(new AccionParaAlertasParaEmail(correoElectronico));
    listaDeAcciones.add(accionRecalculo);
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas(listaDeAcciones);
    List<AlertaMeteorologica> lista = new ArrayList<>();
    lista.add(AlertaMeteorologica.TORMENTA);
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.only()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.only()).notificar(Mockito.any());
    verify(accionRecalculo, Mockito.only()).nuevasAlertas(lista, usuarioCon3PrendasDeCadaCategoria);
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
  }

  @DisplayName("Si hay alertas de tormenta solamente y el usuario tiene activadas la del notificador y recalculo, " +
      "solo tienen que mandar los mensajes correspondientes a esas 2 alertas")
  @Test
  public void actualizarLasAlertasConTormentaConUsuarioConAlgunasDeLasAlertas() {
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    listaDeAcciones.add(new AccionParaAlertasParaNotificacion(notificador));
    listaDeAcciones.add(accionRecalculo);
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas(listaDeAcciones);
    List<AlertaMeteorologica> lista = new ArrayList<>();
    lista.add(AlertaMeteorologica.TORMENTA);
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.never()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.only()).notificar(Mockito.any());
    verify(accionRecalculo, Mockito.only()).nuevasAlertas(lista, usuarioCon3PrendasDeCadaCategoria);
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
  }

  @DisplayName("Si no hay alertas y el usuario tiene activadas todas las alertas" +
      " no se avisan a los usuarios los mensajes correspondientes")
  @Test
  public void NoSeActualizanLasAlertasSiNoHay() {
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    listaDeAcciones.add(new AccionParaAlertasParaNotificacion(notificador));
    CorreoElectronico correoElectronico = new CorreoElectronicoMailSender(mailer);
    listaDeAcciones.add(new AccionParaAlertasParaEmail(correoElectronico));
    listaDeAcciones.add(accionRecalculo);
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas(listaDeAcciones);
    List<AlertaMeteorologica> lista = new ArrayList<>();
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.never()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.never()).notificar(Mockito.any());
    verify(accionRecalculo, Mockito.never()).nuevasAlertas(lista, usuarioCon3PrendasDeCadaCategoria);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
  }

  @DisplayName("Si hay alertas de tormenta solamente y el usuario tiene activadas la del recalculo, " +
      "solo tienen que mandar los mensajes correspondientes de esa alerta")
  @Test
  public void actualizarLasAlertasConTormentaConUsuarioConSoloElRecalculo() {
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    listaDeAcciones.add(accionRecalculo);
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas(listaDeAcciones);
    List<AlertaMeteorologica> lista = new ArrayList<>();
    lista.add(AlertaMeteorologica.TORMENTA);
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.never()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.never()).notificar(Mockito.any());
    verify(accionRecalculo, Mockito.only()).nuevasAlertas(lista, usuarioCon3PrendasDeCadaCategoria);
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
  }

  @DisplayName("Si hay alertas de tormenta solamente y el usuario tiene activadas la del notificador, " +
      "solo tienen que mandar los mensajes correspondientes de esa alerta")
  @Test
  public void actualizarLasAlertasConTormentaConUsuarioConSoloElNotificador() {
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    listaDeAcciones.add(new AccionParaAlertasParaNotificacion(notificador));
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas(listaDeAcciones);
    List<AlertaMeteorologica> lista = new ArrayList<>();
    lista.add(AlertaMeteorologica.TORMENTA);
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.never()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.only()).notificar(Mockito.any());
    verify(accionRecalculo, Mockito.never()).nuevasAlertas(lista, usuarioCon3PrendasDeCadaCategoria);
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
  }

  @DisplayName("Si hay alertas de tormenta solamente y el usuario tiene activadas la del notificador, " +
      "solo tienen que mandar los mensajes correspondientes de esa alerta")
  @Test
  public void noSeEjecutanLasAlertasSiELUsuarioNoseCargoNinguna() {
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas(listaDeAcciones);
    List<AlertaMeteorologica> lista = new ArrayList<>();
    lista.add(AlertaMeteorologica.TORMENTA);
    lista.add(AlertaMeteorologica.GRANIZO);
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertFalse(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.never()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.never()).notificar(Mockito.any());
    verify(accionRecalculo, Mockito.never()).nuevasAlertas(lista, usuarioCon3PrendasDeCadaCategoria);
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.TORMENTA));
    assertTrue(registroDeAlertasMeteorologicas.getAlertaMeteorologicas().contains(AlertaMeteorologica.GRANIZO));
  }
}

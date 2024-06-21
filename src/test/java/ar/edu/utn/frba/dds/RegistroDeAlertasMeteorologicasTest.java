package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RegistroDeAlertasMeteorologicasTest {

  //  private AccuWeatherApi apiExterna;
  private ServicioMeteorologico servicioMeteorologicoApi;
  private RegistroDeAlertasMeteorologicas registroDeAlertasMeteorologicas;
  private MailSender mailer;
  private NotificationService notificador;

  @BeforeEach
  public void setUp() {
//    apiExterna = mock(AccuWeatherApi.class);
    servicioMeteorologicoApi = mock(ServicioMeteorologico.class);
    mailer = mock(MailSender.class);
    notificador = mock(NotificationService.class);
    /*ServicioMeteorologico servicioMeteorologico = new ServicioMeteorologicoAccuWeatherApi(
        apiExterna, Duration.ofHours(1), "Buenos Aires, Argentina");*/
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
    Usuario usuarioCon3PrendasDeCadaCategoria = new Usuario(32);
    List<AccionParaAlertasMeteorologicas> listaDeAcciones = new ArrayList<>();
    listaDeAcciones.add(new AccionParaAlertasParaNotificacion());
    listaDeAcciones.add(new AccionParaAlertasParaEmail());
    listaDeAcciones.add(new AccionParaAlertasParaRecalculo());
    usuarioCon3PrendasDeCadaCategoria.setAccionParaAlertasMeteorologicas();
    guardarropaCon3PrendasDeCadaCategoria = new Guardarropa(prendas,
        CriterioGuardarropa.ROPA_DE_ENTRECASA, usuarioCon3PrendasDeCadaCategoria,
        ProveedorMotor.getMotor(), asesorDeImagen);
    usuarioCon3PrendasDeCadaCategoria.agregarGuardarropa(guardarropaCon3PrendasDeCadaCategoria);
    UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(new ArrayList<>());
    usuarioRepositorio.agregarUsuario(usuarioCon3PrendasDeCadaCategoria);
    registroDeAlertasMeteorologicas = new RegistroDeAlertasMeteorologicas(
        servicioMeteorologicoApi, usuarioRepositorio);
  }

  @DisplayName("Si se actualizan las alertan se avisan a los usuarios los mensajes correspondientes")
  @Test
  public void actualizarLasAlertas() {
//    Map<String, List<String>> mapa = new HashMap<>();
    List<AlertaMeteorologica> lista = new ArrayList<>();
    lista.add(AlertaMeteorologica.GRANIZO);
    lista.add(AlertaMeteorologica.TORMENTA);
//    mapa.put("CurrentAlerts", lista);
    when(servicioMeteorologicoApi.obtenerAlertaMeteorologica()).thenReturn(lista);
    registroDeAlertasMeteorologicas.actualizarAlertas();
    verify(mailer, Mockito.only()).enviarCorreo(Mockito.any(), Mockito.any());
    verify(notificador, Mockito.only()).notificar(Mockito.any());
//    assertEquals("La lista de uniformes no puede ser vacia", exception.getMessage());
  }
}

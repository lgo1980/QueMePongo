package ar.edu.utn.frba.dds;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Main {

  public static void main(String[] args) {
    UsuarioRepositorio usuarioRepositorio = obtenerLosUsuarios();
    usuarioRepositorio.calcularSugerenciaDiaria();
    RegistroDeAlertasMeteorologicas registro = obtenerRegistros(usuarioRepositorio);
    registro.actualizarAlertas();
  }

  private static RegistroDeAlertasMeteorologicas obtenerRegistros(UsuarioRepositorio usuarioRepositorio) {
    ServicioMeteorologico servicioMeteorologico = new ServicioMeteorologicoAccuWeatherApi(
        null, Duration.ofHours(1), "Buenos Aires, Argentina");
    return new RegistroDeAlertasMeteorologicas(servicioMeteorologico, usuarioRepositorio);
  }

  private static UsuarioRepositorio obtenerLosUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();
    Usuario usuario1 = new Usuario(50);
    usuario1.agregarGuardarropa(obtenerGuardarropaParaUsuario(usuario1));
    Usuario usuario2 = new Usuario(34);
    usuario2.agregarGuardarropa(obtenerGuardarropaParaUsuario(usuario2));
    usuarios.add(usuario1);
    usuarios.add(usuario2);
    return new UsuarioRepositorio(usuarios);
  }

  private static Guardarropa obtenerGuardarropaParaUsuario(Usuario usuarioCreador) {
    return new Guardarropa(
        obtenerPrendasParaGuardarropa(), CriterioGuardarropa.ROPA_DE_ENTRECASA, usuarioCreador,
        obtenerMotor(), ObtenerAsesor());
  }

  private static AsesorDeImagen ObtenerAsesor() {
    ServicioMeteorologico servicioMeteorologico = new ServicioMeteorologicoAccuWeatherApi(
        null, Duration.ofHours(1), "Buenos Aires, Argentina");
    return new AsesorDeImagen(servicioMeteorologico);
  }

  private static MotorSugerencia obtenerMotor() {
    ProveedorMotor.setMotor(new MotorBasico());
    return ProveedorMotor.getMotor();
  }

  private static List<Prenda> obtenerPrendasParaGuardarropa() {
    List<Prenda> lista = new ArrayList<>();
    lista.add(new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA,
        new Color(0, 2, 3), null, Clase.INFORMAL, 21D));
    lista.add(new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA,
        new Color(0, 2, 3), null, Clase.NEUTRAL, 20D));
    lista.add(new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA,
        new Color(0, 2, 3), null, Clase.NEUTRAL, 21D));
    lista.add(new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA,
        new Color(0, 2, 3), null, Clase.FORMAL, 19D));
    return lista;
  }
}

package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GuardarropaTest {

  private Guardarropa guardarropaCon3PrendasDeCadaCategoria;
  private Guardarropa guardarropaCon1PrendasDeCadaCategoria;
  private Guardarropa guardarropaConDistintasPrendasDeCadaCategoria;
  private Guardarropa guardarropaQueNoTengaAlgoDeLos3Informal;

  @BeforeEach
  public void setUp() {
    ProveedorMotor.setMotor(new MotorBasico());
    ServicioMeteorologicoAccuWeatherApi servicio = new ServicioMeteorologicoAccuWeatherApi(
        new AccuWeatherApi(), Duration.ofHours(1), "Buenos Aires, Argentina");
    AsesorDeImagen asesorDeImagen = new AsesorDeImagen(servicio);
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
    guardarropaCon3PrendasDeCadaCategoria = new Guardarropa(prendas, CriterioGuardarropa.ROPA_DE_ENTRECASA,
        usuarioCon3PrendasDeCadaCategoria, ProveedorMotor.getMotor(), asesorDeImagen);
    List<Prenda> prendas2 = new ArrayList<>();
    prendas2.add(remera1);
    prendas2.add(pantalon2);
    prendas2.add(zapatilla1);
    Usuario usuarioCon1PrendasDeCadaCategoria = new Usuario(55);
    guardarropaCon1PrendasDeCadaCategoria = new Guardarropa(prendas2, CriterioGuardarropa.ROPA_DE_ENTRECASA,
        usuarioCon1PrendasDeCadaCategoria, ProveedorMotor.getMotor(), asesorDeImagen);
    List<Prenda> prendas3 = new ArrayList<>();
    prendas3.add(remera1);
    prendas3.add(remera2);
    prendas3.add(remera3);
    prendas3.add(pantalon2);
    prendas3.add(zapatilla1);
    Usuario usuarioConDistintasPrendasDeCadaCategoria = new Usuario(56);
    guardarropaConDistintasPrendasDeCadaCategoria = new Guardarropa(prendas3, CriterioGuardarropa.ROPA_DE_ENTRECASA,
        usuarioConDistintasPrendasDeCadaCategoria, ProveedorMotor.getMotor(), asesorDeImagen);
    List<Prenda> prendas4 = new ArrayList<>();
    prendas4.add(remera1);
    prendas4.add(remera2);
    prendas4.add(remera3);
    prendas4.add(pantalon2);
    prendas4.add(pantalon1);
    prendas4.add(zapatilla2);
    Usuario usuarioQueNoTengaAlgoDeLos3Informal = new Usuario(56);
    guardarropaQueNoTengaAlgoDeLos3Informal = new Guardarropa(prendas4, CriterioGuardarropa.ROPA_DE_ENTRECASA,
        usuarioQueNoTengaAlgoDeLos3Informal, ProveedorMotor.getMotor(), asesorDeImagen);
  }

  @DisplayName("Validar que el guardarropa reciba una lista de prendas validas")
  @Test
  public void configurarUniformeConFaltantesTest() {
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Guardarropa(null, CriterioGuardarropa.ROPA_DE_ENTRECASA, new Usuario(32),
            null, null));
    assertEquals("Debe ingresar una lista de prendas validas", exception.getMessage());
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Guardarropa(new ArrayList<>(), CriterioGuardarropa.ROPA_DE_ENTRECASA, new Usuario(32),
            null, null));
    assertEquals("Debe ingresar una lista de prendas validas", exception.getMessage());
  }

  @DisplayName("Recibir la lista de sugerencias de uniformes")
  @Test
  public void recibirSugerenciasMotorBasicoTest() {
    assertEquals(27, guardarropaCon3PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(1, guardarropaCon1PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(3, guardarropaConDistintasPrendasDeCadaCategoria.recibirSugerencias().size());
  }

  @DisplayName("Recibir la lista de sugerencias de uniformes pero cambiando el motor de Informal mayores")
  @Test
  public void recibirSugerenciasMotorInformalMayoresTest() {
    ProveedorMotor.setMotor(new MotorInformalMayores());
    guardarropaCon3PrendasDeCadaCategoria.setMotor(ProveedorMotor.getMotor());
    guardarropaCon1PrendasDeCadaCategoria.setMotor(ProveedorMotor.getMotor());
    guardarropaConDistintasPrendasDeCadaCategoria.setMotor(ProveedorMotor.getMotor());
    guardarropaQueNoTengaAlgoDeLos3Informal.setMotor(ProveedorMotor.getMotor());
    assertEquals(27, guardarropaCon3PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(1, guardarropaCon1PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(1, guardarropaConDistintasPrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(0, guardarropaQueNoTengaAlgoDeLos3Informal.recibirSugerencias().size());
  }
}

package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class AsesorDeImagenTest {

  private ServicioMeteorologico servicioMeteorologicoApi;
  private AsesorDeImagen asesorDeImagen;
  private final List<Uniforme> uniformesMayoresA20Grados = new ArrayList<>();
  private final List<Uniforme> uniformesConMenosA20Grados = new ArrayList<>();
  private Uniforme uniformeConMenorA20Grados;

  @BeforeEach
  public void setUp() {
    servicioMeteorologicoApi = mock(ServicioMeteorologico.class);
    asesorDeImagen = new AsesorDeImagen(servicioMeteorologicoApi);
    Prenda remeraMayor20 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3), null, Clase.INFORMAL, 21D);
    Prenda remeraIgual20 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRAL, 20D);
    Prenda remeraMenor20 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3), null, Clase.FORMAL, 19D);
    Prenda pantalonMayor20 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRAL, 21D);
    Prenda pantalonIgual20 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.INFORMAL, 20D);
    Prenda pantalonMenor20 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRAL, 19D);
    Prenda zapatillaMayor20 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.INFORMAL, 21D);
    Prenda zapatillaIgual20 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRAL, 20D);
    Prenda zapatillaMenor20 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.FORMAL, 19D);
    uniformesMayoresA20Grados.add(new Uniforme(zapatillaMayor20, pantalonMayor20, remeraMayor20));
    Uniforme uniformeConIgualA20Grados = new Uniforme(zapatillaIgual20, pantalonIgual20, remeraIgual20);
    uniformesConMenosA20Grados.add(uniformeConIgualA20Grados);
    uniformeConMenorA20Grados = new Uniforme(zapatillaMenor20, pantalonMenor20, remeraMenor20);
    uniformesConMenosA20Grados.add(uniformeConMenorA20Grados);
  }

  @DisplayName("Ver que si se ingresa una lista vacia o nula tire una excepcion")
  @Test
  public void sugerirUniformeErrorTest() {
    List<Uniforme> uniformes = new ArrayList<>();
    RuntimeException exception = assertThrows(RuntimeException.class, () ->
        asesorDeImagen.sugerirUniforme(uniformes));
    assertEquals("La lista de uniformes no puede ser vacia", exception.getMessage());
  }

  @DisplayName("Ver que si se ingresa una lista que no cumpla con la temperatura actual devuelva vacio")
  @Test
  public void sugerirUniformeQueNoHayaNingunoConLaTemperaturaPedidaTest() {
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    assertNull(asesorDeImagen.sugerirUniforme(uniformesMayoresA20Grados));
  }

  @DisplayName("Ver que si se ingresa una lista que haya alguno que cumple con la temperatura actual devuelva alguno")
  @Test
  public void sugerirUniformeQueDevuelvaAlgunoDeLosQueSiCumplenTest() {
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    assertEquals(uniformeConMenorA20Grados, asesorDeImagen.sugerirUniforme(uniformesConMenosA20Grados));
  }
}

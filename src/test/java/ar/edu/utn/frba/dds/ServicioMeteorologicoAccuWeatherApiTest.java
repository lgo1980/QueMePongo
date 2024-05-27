package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServicioMeteorologicoAccuWeatherApiTest {

  private ServicioMeteorologico servicioMeteorologicoApi;

  @BeforeEach
  public void setUp() {
    servicioMeteorologicoApi = mock(ServicioMeteorologico.class);
  }

  @DisplayName("Ver que si no se ingresa los parametros necesarios no se pueda instanciar el servicio meteorologico")
  @Test
  public void instanciarServicioMeteorologicoErrorTest() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
        new ServicioMeteorologicoAccuWeatherApi(null, null, null));
    assertEquals("Debe ingresar una api del clima", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () ->
        new ServicioMeteorologicoAccuWeatherApi(new AccuWeatherAPI(), null, null));
    assertEquals("Debe ingresar un periodo de validez", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () ->
        new ServicioMeteorologicoAccuWeatherApi(new AccuWeatherAPI(), Duration.ofHours(1), null));
    assertEquals("Debe ingresar una localidad", exception.getMessage());
    ServicioMeteorologicoAccuWeatherApi servico = new ServicioMeteorologicoAccuWeatherApi(new AccuWeatherAPI(), Duration.ofHours(1), "Buenos Aires, Argentina");
    assertEquals("Buenos Aires, Argentina", servico.getDireccion());
  }

  @DisplayName("Ver que si ingreso los parametros necesarios se pueda instanciar el servicio meteorologico")
  @Test
  public void instanciarServicioMeteorologicoBienSinMockitoTest() {
    ServicioMeteorologicoAccuWeatherApi servico = new ServicioMeteorologicoAccuWeatherApi(new AccuWeatherAPI(), Duration.ofHours(1), "Buenos Aires, Argentina");
    assertEquals("Buenos Aires, Argentina", servico.getDireccion());
  }

  @DisplayName("Ver que si ingreso los parametros necesarios se pueda instanciar el servicio meteorologico")
  @Test
  public void instanciarServicioMeteorologicoBienConMockitoTest() {
    when(servicioMeteorologicoApi.obtenerCondicionesClimaticas()).thenReturn(new CondicionClimatica(20d));
    assertEquals(20d, servicioMeteorologicoApi.obtenerCondicionesClimaticas().temperatura());
  }

}

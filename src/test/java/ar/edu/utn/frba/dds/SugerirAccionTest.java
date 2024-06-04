package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SugerirAccionTest {

  private SugerirAccion sugerirAccionDeAgregar;
  private SugerirAccion sugerirAccionDeQuitar;
  private Guardarropa guardarropaParaAgregar;
  private Guardarropa guardarropaParaQuitar;

  @BeforeEach
  public void setUp() {
    Prenda prendaAgregar = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA,
        new Color(2, 3, 4), new Color(0, 0, 0), Clase.FORMAL, 20D);
    Prenda prendaQuitar = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA,
        new Color(2, 3, 4), new Color(0, 0, 0), Clase.FORMAL, 10D);
    List<Prenda> prendasParaAgregar = new ArrayList<>();
    prendasParaAgregar.add(prendaQuitar);
    sugerirAccionDeAgregar = new SugerirAccionDeAgregar(prendaAgregar);
    guardarropaParaAgregar = new Guardarropa(prendasParaAgregar, CriterioGuardarropa.ROPA_DE_ENTRECASA,
        null, null, null);
    sugerirAccionDeQuitar = new SugerirAccionDeQuitar(prendaQuitar);
  }

  @DisplayName("Si instancio una sugerencia con una prenda en null no se instancia la sugerencia")
  @Test
  public void instanciarUnaSugerenciaConPrendaVaciaTest() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
        new SugerirAccionDeAgregar(null));
    assertEquals("Debe ingresar una prenda para sugerir acción", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () ->
        new SugerirAccionDeQuitar(null));
    assertEquals("Debe ingresar una prenda para sugerir acción", exception.getMessage());
  }

  @DisplayName("Si acepto una sugerencia para agregar prenda tiene que agregarse al guardarropa")
  @Test
  public void aceptarUnaSugerenciaParaAgregarPrenda() {
    assertFalse(guardarropaParaAgregar.getPrendas().contains(sugerirAccionDeAgregar.getPrenda()));
    sugerirAccionDeAgregar.aceptarSugerencia(guardarropaParaAgregar);
    assertTrue(guardarropaParaAgregar.getPrendas().contains(sugerirAccionDeAgregar.getPrenda()));
    assertEquals(EstadoSugerirAccion.ACEPTADA, sugerirAccionDeAgregar.getEstadoSugerirAccion());
  }

  @DisplayName("Si acepto una sugerencia para quitar prenda tiene que quitarse del guardarropa")
  @Test
  public void aceptarUnaSugerenciaParaQuitarPrenda() {
    assertTrue(guardarropaParaAgregar.getPrendas().contains(sugerirAccionDeQuitar.getPrenda()));
    sugerirAccionDeQuitar.aceptarSugerencia(guardarropaParaAgregar);
    assertFalse(guardarropaParaAgregar.getPrendas().contains(sugerirAccionDeQuitar.getPrenda()));
    assertEquals(EstadoSugerirAccion.ACEPTADA, sugerirAccionDeQuitar.getEstadoSugerirAccion());
  }

  @DisplayName("Si rechazo una sugerencia para quitar prenda, la sugerencia tien que ponerse como rechazada")
  @Test
  public void rechazarUnaSugerenciaParaQuitarPrenda() {
    sugerirAccionDeQuitar.rechazarSugerencia(guardarropaParaAgregar);
    assertEquals(EstadoSugerirAccion.RECHAZADA, sugerirAccionDeQuitar.getEstadoSugerirAccion());
  }

  @DisplayName("Si rechazo una sugerencia para agregar prenda, la sugerencia tien que ponerse como rechazada")
  @Test
  public void rechazarUnaSugerenciaParaAgregarPrenda() {
    sugerirAccionDeAgregar.rechazarSugerencia(guardarropaParaAgregar);
    assertEquals(EstadoSugerirAccion.RECHAZADA, sugerirAccionDeAgregar.getEstadoSugerirAccion());
  }

}

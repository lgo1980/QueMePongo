package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstitucionTest {

  @Test
  public void configurarUniformeConFaltantesTest() {
    Institucion institucion = new Institucion();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        institucion.configurarUniforme(null, null));
    Assertions.assertEquals("Debe ingresar un nombre de la institucion", exception.getMessage());
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        institucion.configurarUniforme("Saint Jean", null));
    Assertions.assertEquals("Debe ingresar un uniforme de la institucion", exception.getMessage());
  }

  @Test
  public void configurarUniformeCorrectoTest() {
    Prenda prendaRemera = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(1, 3, 2), null, Clase.NEUTRAL);
    Prenda prendaZapatilla = new Prenda(TipoPrenda.ZAPATILLA, Material.ALGODON, Trama.LISA, new Color(1, 3, 2), null, Clase.NEUTRAL);
    Prenda prendaPantalon = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Trama.LISA, new Color(1, 3, 2), null, Clase.NEUTRAL);
    Institucion institucion = new Institucion();
    Uniforme uniforme = new Uniforme(prendaZapatilla, prendaPantalon, prendaRemera);
    institucion.configurarUniforme("Saint Jean", uniforme);
    Assertions.assertEquals("Saint Jean", institucion.getNombre());
  }
}

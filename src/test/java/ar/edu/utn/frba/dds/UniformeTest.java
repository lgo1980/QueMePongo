package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UniformeTest {

  @Test
  public void UniformeContructorTest() {
    Prenda prendaRemera = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(1, 3, 2), null, Clase.NEUTRAL);
    Prenda prendaZapatilla = new Prenda(TipoPrenda.ZAPATILLA, Material.ALGODON, Trama.LISA, new Color(1, 3, 2), null, Clase.NEUTRAL);
    Prenda prendaPantalon = new Prenda(TipoPrenda.PANTALON, Material.ALGODON, Trama.LISA, new Color(1, 3, 2), null, Clase.NEUTRAL);
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Uniforme(prendaRemera, null, null));
    Assertions.assertEquals("Se ingreso una prenda como calzado, que no lo era", exception.getMessage());
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Uniforme(prendaZapatilla, prendaRemera, null));
    Assertions.assertEquals("Se ingreso una prenda como parte inferior, que no lo era", exception.getMessage());
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Uniforme(prendaZapatilla, prendaPantalon, prendaPantalon));
    Assertions.assertEquals("Se ingreso una prenda como parte superior, que no lo era", exception.getMessage());
    Uniforme uniforme = new Uniforme(prendaZapatilla, prendaPantalon, prendaRemera);
    Assertions.assertEquals(TipoPrenda.ZAPATILLA, uniforme.getCalzado().getTipoPrenda());
  }
}

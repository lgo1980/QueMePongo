package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrendaTest {

  @Test
  public void expeptionSetTipo() {
    Prenda prenda = new Prenda();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        prenda.setTipo(null));
    Assertions.assertEquals("Especificar el tipo de prenda", exception.getMessage());
  }

  @Test
  public void expeptionSetMaterial() {
    Prenda prenda = new Prenda();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        prenda.setMaterial(null));
    Assertions.assertEquals("Especificar el tipo de material", exception.getMessage());
  }

  @Test
  public void expeptionSetColorPrimario() {
    Prenda prenda = new Prenda();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        prenda.setColorPrimario(null));
    Assertions.assertEquals("Especificar el tipo de color primario", exception.getMessage());
  }
}

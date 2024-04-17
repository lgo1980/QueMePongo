package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrendaTest {

  @Test
  public void expeptionEspecificarTipo() {
    Prenda prenda = new Prenda();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        prenda.especificarTipo(null));
    Assertions.assertEquals("Especificar el tipo de prenda", exception.getMessage());
  }

  @Test
  public void expeptionEspecificarResto() {
    Prenda prenda = new Prenda();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        prenda.especificarResto(null, null, null));
    Assertions.assertEquals("Especificar el tipo de material", exception.getMessage());
    Material material = new Material("Algodon");
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        prenda.especificarResto(material, null, null));
    Assertions.assertEquals("Especificar el tipo de color primario", exception.getMessage());
  }

  @Test
  public void expeptionConstructor() {
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Prenda(null, null, null, null));
    Assertions.assertEquals("Especificar el tipo de prenda", exception.getMessage());
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Prenda(TipoPrenda.REMERA, null, null, null));
    Assertions.assertEquals("Especificar el tipo de material", exception.getMessage());
    Material material = new Material("Algodon");
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Prenda(TipoPrenda.REMERA, material, null, null));
    Assertions.assertEquals("Especificar el tipo de color primario", exception.getMessage());
  }

  @Test
  public void guardarTest() {
    Prenda prenda = new Prenda();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, prenda::guardar);
    Assertions.assertEquals("¡No se pudo guardar la prenda! La prenda no es valida", exception.getMessage());
    prenda.especificarTipo(TipoPrenda.REMERA);
    exception = Assertions.assertThrows(IllegalArgumentException.class, prenda::guardar);
    Assertions.assertEquals("¡No se pudo guardar la prenda! La prenda no es valida", exception.getMessage());
    Material material = new Material("Algodon");
    Color colorPrimario = new Color(2, 3, 4);
    prenda.especificarResto(material, colorPrimario, null);
    prenda.guardar();
    Assertions.assertTrue(prenda.isGuardada());
  }
}

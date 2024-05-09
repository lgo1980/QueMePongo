package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrendaTest {

  @Test
  public void expeptionSetTipo() {
    BorradorPrenda borradorPrenda = new BorradorPrenda();
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        borradorPrenda.setTipoPrenda(null));
    Assertions.assertEquals("Especificar el tipo de prenda", exception.getMessage());
    borradorPrenda.setTipoPrenda(TipoPrenda.REMERA);
    Assertions.assertEquals(Categoria.PARTE_SUPERIOR, borradorPrenda.getTipoPrenda().getCategoria());
  }
}

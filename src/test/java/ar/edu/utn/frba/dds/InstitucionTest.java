package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InstitucionTest {

  @Test
  public void UniformeContructorTest() {
    Prenda prendaRemera = new Prenda(TipoPrenda.REMERA, new Material("Algodon"), new Color(1, 3, 2), null);
    Prenda prendaZapatilla = new Prenda(TipoPrenda.ZAPATILLA, new Material("Tela"), new Color(1, 3, 2), null);
    Prenda prendaPantalon = new Prenda(TipoPrenda.PANTALON, new Material("JEAN"), new Color(1, 3, 2), null);
    UsuarioAdmin usuarioAdmin = new UsuarioAdmin();
    Institucion institucion = new Institucion();
    Institucion institucion2 = new Institucion();
    Uniforme uniforme = new Uniforme(prendaZapatilla, prendaPantalon, prendaRemera);
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        institucion.configurarUniforme("Saint Jean", uniforme, null));
    Assertions.assertEquals("Solo un administrador puede configurar uniformes", exception.getMessage());
    institucion2.configurarUniforme("Saint Jean", uniforme, usuarioAdmin);
    Assertions.assertEquals("Saint Jean", institucion2.getNombre());
  }
}

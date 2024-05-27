package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

  @DisplayName("Validar que el usuario reciba una lista de guardarropas validas")
  @Test
  public void noSePuedeInstaciarUnUsuarioSinGuardarropaTest() {
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Usuario(null, 0));
    assertEquals("Debe ingresar una lista de guardarropas validas", exception.getMessage());
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Usuario(new ArrayList<>(), 0));
    assertEquals("Debe ingresar una lista de guardarropas validas", exception.getMessage());
  }

  @DisplayName("Validar que el usuario reciba una lista de prendas validas")
  @Test
  public void configurarUniformeConFaltantesTest() {
    Usuario usuario = new Usuario(52);
    Assertions.assertEquals(52, usuario.getEdad());
    Assertions.assertTrue(usuario.getGuardarropas().isEmpty());
  }


}

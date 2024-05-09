package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

  private Usuario usuarioCon3PrendasDeCadaCategoria;
  private Usuario usuarioCon1PrendasDeCadaCategoria;
  private Usuario usuarioConDistintasPrendasDeCadaCategoria;
  private Usuario usuarioQueNoTengaAlgoDeLos3Informal;

  @BeforeEach
  public void setUp() {
    ProveedorMotor.setMotor(new MotorBasico());
    Prenda remera1 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3), null, Clase.INFORMAL);
    Prenda remera2 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRA);
    Prenda remera3 = new Prenda(TipoPrenda.REMERA, Material.ALGODON, Trama.LISA, new Color(0, 2, 3), null, Clase.FORMAL);
    Prenda pantalon1 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRA);
    Prenda pantalon2 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.INFORMAL);
    Prenda pantalon3 = new Prenda(TipoPrenda.PANTALON, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRA);
    Prenda zapatilla1 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.INFORMAL);
    Prenda zapatilla2 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.NEUTRA);
    Prenda zapatilla3 = new Prenda(TipoPrenda.ZAPATILLA, Material.CUERO, Trama.LISA, new Color(0, 2, 3), null, Clase.FORMAL);
    List<Prenda> prendas = new ArrayList<>();
    prendas.add(remera1);
    prendas.add(remera2);
    prendas.add(remera3);
    prendas.add(pantalon1);
    prendas.add(pantalon2);
    prendas.add(pantalon3);
    prendas.add(zapatilla1);
    prendas.add(zapatilla2);
    prendas.add(zapatilla3);
    usuarioCon3PrendasDeCadaCategoria = new Usuario(prendas, 32, ProveedorMotor.getMotor());
    List<Prenda> prendas2 = new ArrayList<>();
    prendas2.add(remera1);
    prendas2.add(pantalon2);
    prendas2.add(zapatilla1);
    usuarioCon1PrendasDeCadaCategoria = new Usuario(prendas2, 55, ProveedorMotor.getMotor());
    List<Prenda> prendas3 = new ArrayList<>();
    prendas3.add(remera1);
    prendas3.add(remera2);
    prendas3.add(remera3);
    prendas3.add(pantalon2);
    prendas3.add(zapatilla1);
    usuarioConDistintasPrendasDeCadaCategoria = new Usuario(prendas3, 56, ProveedorMotor.getMotor());
    List<Prenda> prendas4 = new ArrayList<>();
    prendas4.add(remera1);
    prendas4.add(remera2);
    prendas4.add(remera3);
    prendas4.add(pantalon2);
    prendas4.add(pantalon1);
    prendas4.add(zapatilla2);
    usuarioQueNoTengaAlgoDeLos3Informal = new Usuario(prendas4, 56, ProveedorMotor.getMotor());
  }

  @DisplayName("Validar que el usuario reciba una lista de prendas validas")
  @Test
  public void configurarUniformeConFaltantesTest() {
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Usuario(null, 0, null));
    Assertions.assertEquals("Debe ingresar una lista de prendas validas", exception.getMessage());
    exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
        new Usuario(new ArrayList<>(), 0, null));
    Assertions.assertEquals("Debe ingresar una lista de prendas validas", exception.getMessage());
  }

  @DisplayName("Recibir la lista de sugerencias de uniformes")
  @Test
  public void recibirSugerenciasMotorBasicoTest() {
    assertEquals(27, usuarioCon3PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(1, usuarioCon1PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(3, usuarioConDistintasPrendasDeCadaCategoria.recibirSugerencias().size());
  }

  @DisplayName("Recibir la lista de sugerencias de uniformes pero cambiando el motor de Informal mayores")
  @Test
  public void recibirSugerenciasMotorInformalMayoresTest() {
    ProveedorMotor.setMotor(new MotorInformalMayores());
    usuarioCon3PrendasDeCadaCategoria.setMotor(ProveedorMotor.getMotor());
    usuarioCon1PrendasDeCadaCategoria.setMotor(ProveedorMotor.getMotor());
    usuarioConDistintasPrendasDeCadaCategoria.setMotor(ProveedorMotor.getMotor());
    usuarioQueNoTengaAlgoDeLos3Informal.setMotor(ProveedorMotor.getMotor());
    assertEquals(27, usuarioCon3PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(1, usuarioCon1PrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(1, usuarioConDistintasPrendasDeCadaCategoria.recibirSugerencias().size());
    assertEquals(0, usuarioQueNoTengaAlgoDeLos3Informal.recibirSugerencias().size());
  }

}

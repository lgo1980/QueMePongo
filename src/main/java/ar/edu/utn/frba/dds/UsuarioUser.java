package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsuarioUser implements Usuario {

  private Collection<Uniforme> uniformesSugeridos = new ArrayList<>();

  @Override
  public void recibirSugerencia(Uniforme uniformesSugeridos) {

  }
}

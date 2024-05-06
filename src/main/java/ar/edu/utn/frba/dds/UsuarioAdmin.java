package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

public class UsuarioAdmin implements Usuario {

  private Collection<Uniforme> uniformesDeColegios = new ArrayList<>();

  @Override
  public void recibirSugerencia(Uniforme uniformesSugeridos) {

  }
}

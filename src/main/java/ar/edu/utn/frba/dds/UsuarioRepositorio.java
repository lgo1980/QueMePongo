package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public record UsuarioRepositorio(List<Usuario> usuarios) {

  public UsuarioRepositorio(List<Usuario> usuarios) {
    this.usuarios = (usuarios.isEmpty()) ? new ArrayList<>() : usuarios;
  }

  public void agregarUsuario(Usuario usuario) {
    this.usuarios.add(usuario);
  }

  void calcularSugerenciaDiaria() {
    usuarios.forEach(Usuario::calcularSugerenciaDiaria);
  }
}

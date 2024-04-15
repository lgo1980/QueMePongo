package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class Atuendo {

  private List<Prenda> prendas;

  public Atuendo() {
    this.prendas = new ArrayList<>();
  }

  public Atuendo(List<Prenda> prendas) {
    this.prendas = prendas;
  }

  public List<Prenda> getPrendas() {
    return prendas;
  }

  public void setPrendas(List<Prenda> prendas) {
    this.prendas = prendas;
  }

}

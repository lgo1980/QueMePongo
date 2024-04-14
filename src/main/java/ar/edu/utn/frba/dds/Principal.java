package ar.edu.utn.frba.dds;

public class Principal {

  public static void main(String[] args) {
    Color color = new Color(0, 0, 2);
    System.out.println("clor: " + color.b());
    Material material = new Material("Algodon");
//    Color color = new Color(1, 3, 150);
    Prenda prenda = new Prenda(TipoPrenda.REMERA, material, color, null);

    TipoPrenda tipo = prenda.getTipo();
    System.out.println("Categ: " + tipo.REMERA.getCategoria());
  }
}

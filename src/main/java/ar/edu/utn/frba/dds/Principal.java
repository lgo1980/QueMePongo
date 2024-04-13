package ar.edu.utn.frba.dds;

public class Principal {

    public static void main(String[] args) {
        Prenda prenda = new Prenda();
        prenda.setTipo("medias");
        Atuendo atuendo = new Atuendo();
        atuendo.addPrenda(prenda);
        System.out.println("Hola, Mundo!");
    }
}

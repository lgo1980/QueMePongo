package ar.edu.utn.frba.dds;

public class Prenda {

    private String tipo;
    private String categoria;
    private String material;
    private String colorPrimario;
    private String colorSecundario;

    public Prenda() {
    }

    public Prenda(String tipo, String categoria, String material, String colorPrimario, String colorSecundario) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.material = material;
        this.colorPrimario = colorPrimario;
        this.colorSecundario = colorSecundario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColorPrimario() {
        return colorPrimario;
    }

    public void setColorPrimario(String colorPrimario) {
        this.colorPrimario = colorPrimario;
    }

    public String getColorSecundario() {
        return colorSecundario;
    }

    public void setColorSecundario(String colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

    public void esValidaLaPrenda(){
        if(this.tipo == null )
            throw new IllegalArgumentException("Especificar el tipo de prenda");
        if(this.categoria == null )
            throw new IllegalArgumentException("Especificar el tipo de categoria");
        if(this.material == null )
            throw new IllegalArgumentException("Especificar el tipo de material");
        if(this.colorPrimario == null )
            throw new IllegalArgumentException("Especificar el tipo de colorprimario");
    }
}

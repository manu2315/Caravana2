package calculatuesfuerzo.finsol.com.mx.calcula.models;

import calculatuesfuerzo.finsol.com.mx.calcula.adapters.Model;

public class Telefono extends Model{
    private String tipo,numero;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "tipo='" + tipo + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}

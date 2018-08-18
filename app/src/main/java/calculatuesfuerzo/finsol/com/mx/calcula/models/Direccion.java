package calculatuesfuerzo.finsol.com.mx.calcula.models;

import calculatuesfuerzo.finsol.com.mx.calcula.adapters.Model;

public class Direccion extends Model {
    private String direccionCalle,numeroExterior,numeroInterior,cp,colonia,correo;

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "direccionCalle='" + direccionCalle + '\'' +
                ", numeroExterior='" + numeroExterior + '\'' +
                ", numeroInterior='" + numeroInterior + '\'' +
                ", cp='" + cp + '\'' +
                ", colonia='" + colonia + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}

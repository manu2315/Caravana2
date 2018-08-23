package calculatuesfuerzo.finsol.com.mx.calcula.models;

import calculatuesfuerzo.finsol.com.mx.calcula.adapters.Model;

public class Cliente extends Model {

    private String id,apellidoPaterno,apellidoMaterno,nombre,fechaDeNacimiento,rfc,genero;

    private Direccion direccion;
    private Telefono telefono;
    private Adicionales adicionales;
    public Cliente() {
    }

    public Cliente(String apellidoPaterno, String apellidoMaterno, String nombre, String fechaDeNacimiento, String rfc, String genero) {
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.rfc = rfc;
        this.genero = genero;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Adicionales getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(Adicionales adicionales) {
        this.adicionales = adicionales;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", rfc='" + rfc + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}

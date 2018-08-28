package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import android.content.Context;

import calculatuesfuerzo.finsol.com.mx.calcula.models.Adicionales;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Cliente;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Direccion;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Telefono;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.Provider;

public class ClienteProvider {

    private ClienteProviderListener clienteProviderListener;

    //Firebase
    private static final String PATH="prospectos";
    private Cliente client;


    public ClienteProvider(ClienteProviderListener clienteProviderListener) {
        this.clienteProviderListener = clienteProviderListener;
    }


    public  void setCliente(Cliente cliente){
        setCliente(cliente.getApellidoPaterno(),cliente.getApellidoMaterno(),cliente.getNombre(),cliente.getFechaDeNacimiento(),cliente.getRfc(),cliente.getGenero());
    }
    public void setCliente(String apellidoPaterno, String apellidoMaterno, String nombre, String fechaDeNacimiento, String rfc, String genero){
        nuevoCliente(apellidoPaterno,apellidoMaterno,nombre,fechaDeNacimiento,rfc,genero);
        //Provider.pushValue(PATH,client);
    }

    private void nuevoCliente(String ap_pat, String ap_mat, String nom, String fecha, String rfc_, String gen_){
        this.client = new Cliente(ap_pat,ap_mat,nom,fecha,rfc_,gen_);
    }

    public Cliente getCliente(){
        return this.client;
    }

    public void setDireccion(Direccion direccion){
        this.client.setDireccion(direccion);
    }
    public Direccion getDireccion(){
        return client.getDireccion();
    }

    public void setTelefono(Telefono telefono){
        this.client.setTelefono(telefono);
    }

    public Telefono getTelefono(){
        return client.getTelefono();
    }

    public void setAdicionales(Adicionales adicionales){
        this.client.setAdicionales(adicionales);
    }
    public Adicionales getAdicionales(){
        return this.client.getAdicionales();
    }

    public interface ClienteProviderListener{
        void onResponse(Cliente clienteModel);
        void onErrorResponse(Errors error);
    }
}

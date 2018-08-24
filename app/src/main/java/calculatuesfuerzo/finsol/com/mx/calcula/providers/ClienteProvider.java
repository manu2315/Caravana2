package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import calculatuesfuerzo.finsol.com.mx.calcula.models.Cliente;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.Provider;

public class ClienteProvider {

    private ClienteProviderListener clienteProviderListener;

    //Firebase
    private static final String PATH="prospectos";
    private Cliente client;
    public String nom,ap_pat,ap_mat,fecha,gen,rfc;

    public ClienteProvider(ClienteProviderListener clienteProviderListener) {
        this.clienteProviderListener = clienteProviderListener;
    }

    public void setCliente(String apellidoPaterno, String apellidoMaterno, String nombre, String fechaDeNacimiento, String rfc, String genero){
        nuevoCliente(apellidoPaterno,apellidoMaterno,nombre,fechaDeNacimiento,rfc,genero);
        //Provider.pushValue(PATH,client);
    }

    public void nuevoCliente(String ap_pat, String ap_mat, String nom, String fecha, String rfc_, String gen){
        client = new Cliente(ap_pat,ap_mat,nom,fecha,rfc_,gen);
    }
    public interface ClienteProviderListener{
        void onResponse(Cliente clienteModel);
        void onErrorResponse(Errors error);
    }
}

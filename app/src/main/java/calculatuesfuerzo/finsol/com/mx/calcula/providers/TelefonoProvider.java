package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import calculatuesfuerzo.finsol.com.mx.calcula.models.Telefono;

public class TelefonoProvider {

    private TelefonoProviderListener telefonoProviderListener;

    private Telefono telefono;
    private String tipo,numero;

    public void setTelefono(Telefono telefono){
        setTelefono(telefono.getTipo(),telefono.getNumero());
    }
    public void setTelefono(String tipo,String numero){
        nuevoTelefono(tipo,numero);
    }
    private void nuevoTelefono(String tipo_,String numero_){
        this.telefono=new Telefono(tipo_,numero_);
    }

    public Telefono getTelefono(){
        return this.telefono;
    }
    public interface TelefonoProviderListener{
        void onResponse(Telefono telefonoModel);
        void onErrorResponse(Errors error);
    }

}

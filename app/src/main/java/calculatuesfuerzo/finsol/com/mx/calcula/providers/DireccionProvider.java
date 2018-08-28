package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import calculatuesfuerzo.finsol.com.mx.calcula.models.Direccion;

public class DireccionProvider {

    private DireccionProviderListener direccionProviderListener;


    private Direccion direccion;

    public DireccionProvider(DireccionProviderListener direccionProviderListener){
        this.direccionProviderListener = direccionProviderListener;
    }

    public void setDireccion(Direccion direccion){
        setDireccion(direccion.getCalle(),direccion.getNumeroExterior(),direccion.getNumeroInterior(),direccion.getCp(),direccion.getColonia(),direccion.getCorreo());
    }
    public void setDireccion(String calle, String numeroExterior, String numeroInterior, String cp, String colonia, String correo){
        nuevaDireccion(calle,numeroExterior,numeroInterior,cp,colonia,correo);
    }
    private void nuevaDireccion(String calle_,String numExt,String numInt,String cp_,String colonia_,String correo_){
        this.direccion = new Direccion(calle_,numExt,numInt,cp_,colonia_,correo_);
    }

    public Direccion getDireccion(){
        return this.direccion;
    }

    public interface DireccionProviderListener{
        void onResponse(Direccion direccion);
        void onErrorResponse(Errors error);
    }
}

package calculatuesfuerzo.finsol.com.mx.calcula.util;

import android.widget.EditText;

public class Validaciones {

    public boolean CORRECTO=true;

    public Validaciones() {
    }

    private  void checkString(String cadena){
        if(cadena.equals("")&& CORRECTO)
            CORRECTO=false;
        else if(!cadena.equals("")&&CORRECTO)
            CORRECTO= true;
    }
    public  String checkValue(EditText editText){
        return checkValue(editText.getText().toString());
    }
    public  String checkValue(String cadena){
       checkString(cadena);
       return cadena;
    }
}

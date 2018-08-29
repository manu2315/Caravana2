package calculatuesfuerzo.finsol.com.mx.calcula.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

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
    public String checkValue(Spinner spinner){
        final String[] selectedItemText = new String[1];
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText[0] = (String) parent.getItemAtPosition(position);
                checkValue(selectedItemText[0]);
            }
        });
        return selectedItemText[0];
    }
    public  String checkValue(String cadena){
       checkString(cadena);
       return cadena;
    }
    public ArrayList<String> compareDaysSelected(boolean[] selected){
        ArrayList<String> dias_semana = new ArrayList<>();
        for (int i=0;i<selected.length;i++){
            if(selected[i])
                dias_semana.add(Constantes.DIAS_SEMANA[i]);
        }
        return dias_semana;
    }

}

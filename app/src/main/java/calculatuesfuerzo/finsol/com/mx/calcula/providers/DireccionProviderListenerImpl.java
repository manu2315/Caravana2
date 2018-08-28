package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import android.content.Context;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Direccion;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;

public class DireccionProviderListenerImpl implements DireccionProvider.DireccionProviderListener{

    private Direccion mDireccionModel;
    private Context context;

    public DireccionProviderListenerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Direccion direccionModel) {
        this.mDireccionModel=direccionModel;
    }

    @Override
    public void onErrorResponse(Errors error) {
        if(error==Errors.INTERNET_CONECTION_ERROR){
            Util.infoDialog(context, R.string.dialog_internet_problem_message,R.string.dialog_internet_problem_positive_button_label).show();
        }else if (error==Errors.UNKNOWN || error==Errors.PARSE_ERROR){
            Util.infoDialog(context, R.string.dialog_unknown_problem_message, R.string.dialog_unknown_problem_positive_button_label).show();
        }
    }
}

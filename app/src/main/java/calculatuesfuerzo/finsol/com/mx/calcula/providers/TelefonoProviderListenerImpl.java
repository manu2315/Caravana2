package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import android.content.Context;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Telefono;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;

public class TelefonoProviderListenerImpl implements TelefonoProvider.TelefonoProviderListener {
    private Telefono mTelefonoModel;
    private Context context;

    public TelefonoProviderListenerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Telefono telefonoModel) {
        this.mTelefonoModel=telefonoModel;
    }

    @Override
    public void onErrorResponse(Errors error) {
        //Fatla un progressbar//*******
        // mBinding.progressBar.setVisibility(View.GONE);
        if(error==Errors.INTERNET_CONECTION_ERROR){
            Util.infoDialog(context, R.string.dialog_internet_problem_message,R.string.dialog_internet_problem_positive_button_label).show();
        }else if (error==Errors.UNKNOWN || error==Errors.PARSE_ERROR){
            Util.infoDialog(context, R.string.dialog_unknown_problem_message, R.string.dialog_unknown_problem_positive_button_label).show();
        }
    }
}

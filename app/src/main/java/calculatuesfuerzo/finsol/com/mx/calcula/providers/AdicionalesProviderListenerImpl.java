package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import android.content.Context;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Adicionales;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;

public class AdicionalesProviderListenerImpl implements AdicionalesProvider.AdicionalesProviderListener{

    private Adicionales mAdicionalesModel;
    private Context context;

    public AdicionalesProviderListenerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Adicionales adicionalesModel) {
        this.mAdicionalesModel=adicionalesModel;
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

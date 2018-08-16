package calculatuesfuerzo.finsol.com.mx.calcula.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;


import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.MainActivity;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.StepperActivity;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.Test1Fragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.Test2Fragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.Test3Fragment;

public  class MyStepperAdapter extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";
    public MyStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                final Test1Fragment step1 = new Test1Fragment();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                step1.setArguments(b1);
                return step1;
            case 1:
                final Test2Fragment step2 = new Test2Fragment();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                step2.setArguments(b2);
                return step2;
            case 2:
                final Test3Fragment step3 = new Test3Fragment();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                step3.setArguments(b3);
                return step3;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        StepViewModel.Builder builder = new StepViewModel.Builder(context);
                //.setTitle("Hola Mundo!!");
        //INICIO
        switch (position){
            case 0:
                builder.setTitle("Cliente")
                        .setEndButtonLabel("Siguiente")
                        .setBackButtonLabel("Cancelar")
                        .setBackButtonVisible(true);
                       /* .setNextButtonEndDrawableResId(R.drawable.ic_arrow_forward_24dp)
                        .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);*/

                break;
            case 1:
                builder.setTitle("Dirección")
                        .setEndButtonLabel("Siguiente")
                        .setBackButtonLabel("Volver");
                       /* .setNextButtonEndDrawableResId(R.drawable.ic_chevron_right_24dp)
                        .setBackButtonStartDrawableResId(R.drawable.ic_arrow_back_24dp);*/

                break;
            case 2:
                builder.setTitle("Teléfono")
                        .setEndButtonLabel("Completar")
                        .setBackButtonLabel("Volver");
                        /*.setNextButtonEndDrawableResId(StepViewModel.NULL_DRAWABLE)
                        .setBackButtonStartDrawableResId(R.drawable.ic_arrow_back_24dp);*/

                break;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
       //FIN
        return builder.create();


        /*
         StepViewModel.Builder builder = new StepViewModel.Builder(context)
                .setTitle(R.string.tab_title);
        switch (position) {
            case 0:
                builder
                    .setEndButtonLabel("This way")
                    .setBackButtonLabel("Cancel")
                    .setNextButtonEndDrawableResId(R.drawable.ms_forward_arrow)
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
                break;
            case 1:
                builder
                    .setEndButtonLabel(R.string.go_to_summary)
                    .setBackButtonLabel("Go to first")
                    .setBackButtonStartDrawableResId(R.drawable.ms_back_arrow);
                break;
            case 2:
                builder
                    .setBackButtonLabel("Go back")
                    .setEndButtonLabel("I'm done!");
                break;
            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
        return builder.create();
        */

        /*switch (position){
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("Cliente") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Dirección") //can be a CharSequence instead
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Teléfono") //can be a CharSequence instead
                        .create();
        }
        return null;*/
    }

}
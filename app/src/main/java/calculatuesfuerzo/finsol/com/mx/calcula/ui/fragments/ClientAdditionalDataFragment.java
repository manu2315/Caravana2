package calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Adicionales;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.AdicionalesProvider;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.AdicionalesProviderListenerImpl;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Constantes;
import calculatuesfuerzo.finsol.com.mx.calcula.util.MultiSpinner;
import calculatuesfuerzo.finsol.com.mx.calcula.util.TimePickerFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Validaciones;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClientAdditionalDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClientAdditionalDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientAdditionalDataFragment extends Fragment implements BlockingStep {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //vars
    MultiSpinner multiSpinner;//Dias de la semana
    ArrayList<String> dias;
    //String[] diasArray = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

    private MultiSpinner.MultiSpinnerListener listener;
    EditText txtIntialTime, txtFinalTime;

    //Spnners
    Spinner spinnerExperienceInGroupCredit, spinnerCampaign, spinnerStatus;
    //private String[] arrayGrupalExperience = {"Elige la experiencia grupal", "Si", "No"};
    private ArrayAdapter<String> spinnerArrayAdapterGrupalExperience;
    //private String[] arrayCampaign = {"Elige una opción", "Sin campaña", "1 iguala y Supera Plus"};
    private ArrayAdapter<String> spinnerArrayAdapterCampaign;

    // String[] arrayStatus = {"Elige un estatus", "Contactar posteriormente", "Interesado", "Rechazado"};
    private ArrayAdapter<String> spinnerArrayAdapterStatus;

    //Validar
    private AdicionalesProvider adicionalesProvider;
    private Validaciones validar;
    private String hora_inicial, hora_final, experiencia_credito_grupal,campana,estatus;
    private boolean consultar_buro_de_credito;
    private ArrayList<String> dias_semana;

    public ClientAdditionalDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientAdditionalDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientAdditionalDataFragment newInstance(String param1, String param2) {
        ClientAdditionalDataFragment fragment = new ClientAdditionalDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_additional_data, container, false);
        bindUI(view);
        horaInicialLocalizacion();
        horaFinalLocalizacion();
        ExperienceInGroupCredit();
        Campaign();
        Status();
        return view;

    }

    private SpannableString[] diasColorGris(String [] diasArray){
        SpannableString [] diasGris = new SpannableString[7];
        for (int i=0;i<diasArray.length;i++){
            SpannableString ss =new SpannableString(diasArray[i]);
            int color = ContextCompat.getColor(getContext(),R.color.colorText);
            ss.setSpan(new ForegroundColorSpan(Color.GRAY),0,0,0);
            diasGris[i]=ss;

        }

        return diasGris;

    }
    private void bindUI(View view) {

        txtIntialTime = (EditText) view.findViewById(R.id.editText_prospect_initial_time);
        txtFinalTime = (EditText) view.findViewById(R.id.editText_prospect_final_time);
        //Spinners
        spinnerExperienceInGroupCredit = (Spinner) view.findViewById(R.id.spinnerExperienceInGroupCredit);
        spinnerCampaign = (Spinner) view.findViewById(R.id.spinnerCampaign);
        spinnerStatus = (Spinner) view.findViewById(R.id.spinnerStatus);

        multiSpinner = (MultiSpinner) view.findViewById(R.id.multi_spinner);


        //diasArray
        dias = new ArrayList<>(Arrays.asList(Constantes.DIAS_SEMANA));
        listener = new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                Toast.makeText
                        (getContext(), "Lunes : " + selected[0] + " Martes : " + selected[1]
                                + " Miercoles : " + selected[2] + " Jueves : " + selected[3]
                                + " Viernes : " + selected[4] + " Sabado : " + selected[5]
                                + " Domingo : " + selected[6], Toast.LENGTH_SHORT)
                        .show();
            }
        };

        multiSpinner.setItems(dias, getString(R.string.prospect_locationDays_for_all), listener);

        //Provider
        adicionalesProvider = new AdicionalesProvider(new AdicionalesProviderListenerImpl(getContext()));
        dias_semana = new ArrayList<String>();

    }

    private void horaInicialLocalizacion() {
        //Aqui se debe mostrar el timepicker

        txtIntialTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.bindEditText(txtIntialTime);
                newFragment.show(getFragmentManager(), "TimePickerInitial");
            }
        });


    }

    private void horaFinalLocalizacion() {
        //Aqui se debe mostrar el timepicker

        txtFinalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.bindEditText(txtFinalTime);
                newFragment.show(getFragmentManager(), "TimePickerFinal");
            }
        });
    }

    private void ExperienceInGroupCredit() {
        //material spinner Genero INICIO
        //arrayGrupalExperience
        spinnerArrayAdapterGrupalExperience = new ArrayAdapter<String>(
                getContext(), R.layout.spinner_item,Constantes.EXPERIENCIA_GRUPAL ) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        spinnerArrayAdapterGrupalExperience.setDropDownViewResource(R.layout.spinner_item);
        spinnerExperienceInGroupCredit.setAdapter(spinnerArrayAdapterGrupalExperience);

        spinnerExperienceInGroupCredit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //material spinner Genero FIN
    }

    private void Campaign() {
        //material spinner Genero INICIO

        //arrayCampaign
        spinnerArrayAdapterCampaign = new ArrayAdapter<String>(
                getContext(), R.layout.spinner_item, Constantes.CAMPANA) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        spinnerArrayAdapterCampaign.setDropDownViewResource(R.layout.spinner_item);
        spinnerCampaign.setAdapter(spinnerArrayAdapterCampaign);

        spinnerCampaign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //material spinner Genero FIN
    }

    private void Status() {
        //material spinner Genero INICIO
        //arrayStatus
        spinnerArrayAdapterStatus = new ArrayAdapter<String>(
                getContext(), R.layout.spinner_item,Constantes.ESTATUS ) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapterStatus.setDropDownViewResource(R.layout.spinner_item);
        spinnerStatus.setAdapter(spinnerArrayAdapterStatus);

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //material spinner Genero FIN
    }

    private void checkValues() {
        validar= new Validaciones();



    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkValues();
                if(validar.CORRECTO) {

                    //falta provider
                    //falta listener
                    callback.goToNextStep();
                }
            }
        },2000L);
    }



    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        //callback.complete();//ya no es el ultimo fragment
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void setAdicionales(Adicionales adicionales);
    }
}
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Direccion;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.DireccionProvider;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.DireccionProviderListenerImpl;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Validaciones;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClientAddressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClientAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientAddressFragment extends Fragment implements BlockingStep {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Model
    Direccion mDireccionModel;

    //Elements
    private Spinner spinnerColonia;
    private List<String> listColonys;
    private ArrayAdapter<String> spinnerArrayAdapterColony;
    private EditText txtProspectDireccionCalle,txtProspectOutdoorNumber,txtProspectIndoorNumber,txtProspectCp,txtProspectEmail;

    //Validar
    private DireccionProvider direccionProvider;
    private Validaciones validar;
    private String calle_,num_ext,num_int,cp_,colonia_,correo_;

    public ClientAddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientAddressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientAddressFragment newInstance(String param1, String param2) {
        ClientAddressFragment fragment = new ClientAddressFragment();
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
        View view= inflater.inflate(R.layout.fragment_client_address, container, false);
        bindUI(view);
        colonia();
        //Prueba


        return view;
    }
    private void bindUI(View view){

        listColonys = new ArrayList<>();
        listColonys.add("Buscar colonia");
        spinnerColonia=(Spinner)view.findViewById(R.id.spinnerColony);
        spinnerColonia.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.abc_edit_text_material));
        //Edittext

        txtProspectDireccionCalle=(EditText)view.findViewById(R.id.editText_prospect_Direccion_Calle);
        txtProspectOutdoorNumber =(EditText)view.findViewById(R.id.editText_prospect_Outdoor_Number);
        txtProspectIndoorNumber=(EditText)view.findViewById(R.id.editText_prospect_Indoor_Number);
        txtProspectCp=(EditText)view.findViewById(R.id.editText_prospect_Cp);
        txtProspectEmail=(EditText)view.findViewById(R.id.editText_prospect_Email);

        //Provider
        direccionProvider= new DireccionProvider(new DireccionProviderListenerImpl(getContext()));


    }
    private void colonia(){
        //material spinner Colina INICIO
        spinnerArrayAdapterColony = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,listColonys){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapterColony.setDropDownViewResource(R.layout.spinner_item);
        spinnerColonia.setAdapter(spinnerArrayAdapterColony);

        spinnerColonia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
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

    }



    private void checkValues(){
        validar= new Validaciones();
        calle_ = validar.checkValue(txtProspectDireccionCalle);
        num_ext=validar.checkValue(txtProspectOutdoorNumber);
        num_int=txtProspectOutdoorNumber.getText().toString();
        cp_=validar.checkValue(txtProspectCp);
        //Es prueba
        colonia_="Puesta del Sol";
        correo_=validar.checkValue(txtProspectEmail);
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
                    direccionProvider.setDireccion(calle_,num_ext,num_int,cp_,colonia_,correo_);
                    mListener.setDireccion(direccionProvider.getDireccion());
                    callback.goToNextStep();
                }
            }
        },2000L);
    }



    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

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
    public void onError(@NonNull VerificationError error) {
        Toast.makeText(getContext(), "onError! 2-> " + error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSelected() {
        Toast.makeText(getContext(), "onSelected Fragment 2", Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void fragmentClientData();
        void setDireccion(Direccion direccion);



    }
}

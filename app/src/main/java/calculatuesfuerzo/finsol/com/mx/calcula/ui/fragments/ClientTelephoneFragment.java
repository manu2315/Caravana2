package calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Telefono;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.TelefonoProvider;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.TelefonoProviderListenerImpl;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Constantes;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Validaciones;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClientTelephoneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClientTelephoneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientTelephoneFragment extends Fragment implements BlockingStep {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Var
    String[] telephonetype = {"Seleccione un tipo de teléfono...","Directo","Celular"};
    private ArrayAdapter<String> spinnerArrayAdapterTelephoneType;
    Drawable img;
    Spinner sprTelephoneType;
    EditText txtTelephone;
    TextInputLayout txtInputLayouttelefono;
    Button btnBack,btnNext;
    ImageView imgvwTelehpone;

    //Validar
    private TelefonoProvider telefonoProvider;
    private Validaciones validar;
    private String tipo_="",numero_;


    public ClientTelephoneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientTelephoneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientTelephoneFragment newInstance(String param1, String param2) {
        ClientTelephoneFragment fragment = new ClientTelephoneFragment();
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
        View view = inflater.inflate(R.layout.fragment_client_telephone, container, false);
        bindUI(view);
        telephoneType();
        return view;
    }
    private void bindUI(View view){
        sprTelephoneType= (Spinner)view.findViewById(R.id.spinnerTelephoneType);
        txtTelephone=(EditText)view.findViewById(R.id.editText_prospect_telephone);
        txtInputLayouttelefono=(TextInputLayout)view.findViewById(R.id.textInputLayoutTelephone);
        imgvwTelehpone = (ImageView)view.findViewById(R.id.imgvwTelehpone);
        //Provider
        telefonoProvider=new TelefonoProvider(new TelefonoProviderListenerImpl(getContext()));
    }

    private void telephoneType(){
        //material spinner Tipo Telefono INICIO
        spinnerArrayAdapterTelephoneType= new ArrayAdapter<String>(getContext(),
                R.layout.spinner_item,telephonetype){
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

        spinnerArrayAdapterTelephoneType.setDropDownViewResource(R.layout.spinner_item);
        sprTelephoneType.setAdapter(spinnerArrayAdapterTelephoneType);

        sprTelephoneType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                switch(position){
                    case 0:
                        txtTelephone.setVisibility(View.INVISIBLE);
                        imgvwTelehpone.setVisibility(View.INVISIBLE);
                        txtTelephone.setHint("");
                        txtInputLayouttelefono.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        txtTelephone.setVisibility(View.VISIBLE);
                        imgvwTelehpone.setVisibility(View.VISIBLE);
                        txtInputLayouttelefono.setVisibility(View.VISIBLE);
                        //txtTelephone.setHint(R.string.prospect_telephone);
                        img = getContext().getResources().getDrawable( R.drawable.ic_phone_black_24dp,null );
                        imgvwTelehpone.setImageDrawable(img);
                        //Tipo
                        tipo_=selectedItemText;

                        /*img.setBounds( 0, 0, 60, 60 );
                        txtTelephone.setCompoundDrawables( img, null, null, null );*/
                        break;
                    case 2:
                        txtTelephone.setVisibility(View.VISIBLE);
                        imgvwTelehpone.setVisibility(View.VISIBLE);
                        txtInputLayouttelefono.setVisibility(View.VISIBLE);
                        //txtTelephone.setHint(R.string.prospect_cellphone);
                        img = getContext().getResources().getDrawable( R.drawable.ic_smartphone_android_black_24dp,null );
                        imgvwTelehpone.setImageDrawable(img);
                        //Tipo
                        tipo_=selectedItemText;
                        /*img.setBounds( 0, 0, 60, 60 );
                        txtTelephone.setCompoundDrawables( img, null, null, null );*/
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //material spinner Tipo Telefono FIN
    }

    private void checkValues(){
        validar= new Validaciones();
        tipo_=validar.checkValue(tipo_);
        numero_=validar.checkValue(txtTelephone);
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
                    telefonoProvider.setTelefono(tipo_,numero_);
                    mListener.setTelefono(telefonoProvider.getTelefono());
                    callback.goToNextStep();
                }
                else
                    Util.errorAlert(getFragmentManager(), Constantes.ERROR_MESSAGE_TELEPHONE);
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
        Toast.makeText(getContext(), "onError! 3-> " + error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSelected() {
        Toast.makeText(getContext(), "onSelected Fragment 3", Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void setTelefono(Telefono telefono);
    }
}

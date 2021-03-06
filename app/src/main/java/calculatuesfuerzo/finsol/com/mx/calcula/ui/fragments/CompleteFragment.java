package calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.adapters.ReviewAdapter;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Cliente;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Direccion;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.ClienteProvider;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompleteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompleteFragment extends Fragment implements BlockingStep {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    //Cardviews
    ReviewAdapter reviewAdapter;
    RecyclerView recyclerView;
    List<Cliente> clientes;
    Cliente cte;
    Direccion dir;
    public CompleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompleteFragment newInstance(String param1, String param2) {
        CompleteFragment fragment = new CompleteFragment();
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
        View view= inflater.inflate(R.layout.fragment_complete, container, false);
        bindUI(view);
        return view;
    }

    private void bindUI(View view){

        cte = new Cliente("pat","mat","nom","08/08/08","abcdefg","mas");
        dir =new Direccion("A","180","","23090","Obregon","a@b.c");
        cte.setDireccion(dir);
        clientes=new ArrayList<>();
        //clientes.add(mListener.getClienteProvider().getCliente());
        clientes.add(cte);
        reviewAdapter= new ReviewAdapter(getActivity(),clientes);
        recyclerView= (RecyclerView)view.findViewById(R.id.recycler_view_all_data);
        recyclerView.setAdapter(reviewAdapter);

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
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
        //finish
        //gotomain
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
        ClienteProvider getClienteProvider();
    }
}

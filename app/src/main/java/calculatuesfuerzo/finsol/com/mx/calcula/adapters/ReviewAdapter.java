package calculatuesfuerzo.finsol.com.mx.calcula.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Cliente;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Direccion;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Cliente> results;

    public ReviewAdapter(Context context, List<Cliente> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case 0:
                view = View.inflate(parent.getContext(), R.layout.card_view_client_data,null);
                viewHolder = new ViewHolder(view);
                break;
            case 1:
                view=View.inflate(parent.getContext(),R.layout.card_view_client_address,null);
                viewHolder = new ViewHolder(view);
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case 0:
                Cliente cliente = results.get(position);
                ViewHolder viewHolder = (ViewHolder) holder;

                break;
            case 1:
                Cliente direccion =results.get(position);
                ViewHolder viewHolderDireccion = (ViewHolder) holder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return results.size() > 0 ? results.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        return results.size() == 0 ? 0 : 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

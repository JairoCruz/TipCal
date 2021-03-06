package edu.jairo.android.tipcal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.jairo.android.tipcal.R;
import edu.jairo.android.tipcal.models.TipRecord;

/**
 * Created by TSE on 01/06/2016.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private Context context;
    private List<TipRecord> dataset;
    private OnItemClickListener onItemClickListener;


    public TipAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(Context context, List<TipRecord> dataset,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TipRecord element = dataset.get(position);
        String strTip = String.format(context.getString(R.string.global_message_tip), element.getTip());
        String strDate = String.format(element.getDateFormatted());

        holder.txtContent.setText(strTip);
        holder.txtDataText.setText(strDate);
        holder.setOnItemClickListener(element, onItemClickListener);

    }


    // ecapsulo la logica para agregar un item al recyclerview
    public void add(TipRecord record){
        dataset.add(0,record);
        // notifico de los cambios
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        // Utilizo ButterKnife
        @Bind(R.id.txtContent)
        TextView txtContent;
        @Bind(R.id.txtDataText)
        TextView txtDataText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }
}

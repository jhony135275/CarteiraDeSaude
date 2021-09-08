package com.example.carteiradesaude;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adaptadorArrayLista extends RecyclerView.Adapter<adaptadorArrayLista.ViewHolder> {

    private ArrayList<baseArrayLista> listaNomes;
    private LayoutInflater layout;
    private ClickItem eventoClick;

    public adaptadorArrayLista(Context contextoC, ArrayList<baseArrayLista> listaNomesC){
        this.layout     = LayoutInflater.from(contextoC);
        this.listaNomes = listaNomesC;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tela = layout.inflate(R.layout.layout_lista, parent, false);
        return new ViewHolder(tela);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtHolderId.setText(listaNomes.get(position).id);
        holder.txtHolderNome.setText(listaNomes.get(position).nome);
        holder.txtHolderUsername.setText(listaNomes.get(position).username);
    }

    @Override
    public int getItemCount() {
        return listaNomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtHolderId, txtHolderNome, txtHolderUsername;

        public ViewHolder(View itemView) {
            super(itemView);
            txtHolderId    = itemView.findViewById(R.id.txtLayoutId);
            txtHolderNome  = itemView.findViewById(R.id.txtLayoutNome);
            txtHolderUsername = itemView.findViewById(R.id.txtLayoutUsername);

            // colocar o onClick das linhas
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(eventoClick != null){
                eventoClick.onItemClick(v, getAdapterPosition());
            }
        }
    }

    String getItem(int id){
        return listaNomes.get(id).id;
    }

    void setClickListener(ClickItem clickDoItem){
        this.eventoClick = clickDoItem;
    }

    public interface ClickItem{
        void onItemClick(View v, int position);
    }
}

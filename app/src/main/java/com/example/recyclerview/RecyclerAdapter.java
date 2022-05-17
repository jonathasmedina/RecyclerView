package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

   ArrayList<Usuario> usuarioArrayList = new ArrayList<>();

   //construtor
   public RecyclerAdapter(ArrayList<Usuario> usuarioArrayList) {
      this.usuarioArrayList = usuarioArrayList;
   }


   //inner class do viewholder
   //funciona mais ou menos como a onCreate da MainActivity
   public class MyViewHolder extends RecyclerView.ViewHolder{

      TextView mTextViewNome;
      TextView mTextViewEmail;
      ImageView mImageView;

      public MyViewHolder(@NonNull View itemView) {
         super(itemView);
         mTextViewNome = itemView.findViewById(R.id.textViewNome);
         mTextViewEmail = itemView.findViewById(R.id.textViewEmail);
         mImageView = itemView.findViewById(R.id.imageView);

      }
   }

   @NonNull
   @Override
   public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //método para inflar o xml dos itens
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
      return new MyViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
      //método para pegar o valor e jogar nas views da tela;
      String nome = usuarioArrayList.get(position).getNome();
      String email = usuarioArrayList.get(position).getEmail();
      int imagem = usuarioArrayList.get(position).getImage();

      holder.mTextViewNome.setText(nome);
      holder.mTextViewEmail.setText(email);
      holder.mImageView.setImageResource(imagem);

   }

   @Override
   public int getItemCount() {
      //tamanho da lista de itens
      return usuarioArrayList.size();
   }
}

package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Usuario> usuarioArrayList_ = new ArrayList<>();
    int[] imagensUsuarios = {R.drawable.ic_baseline_person_outline_24};
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView01);

        setUserInfo();

        setAdapter();
    }


    private void setUserInfo() {
        usuarioArrayList_.add(new Usuario("João", "joao@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("Maria", "maria@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("José", "jose@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("Pedro", "pedro@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("João", "joao@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("Maria", "maria@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("José", "jose@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("Pedro", "pedro@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("João", "joao@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("Maria", "maria@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("José", "jose@ifms.edu.br", imagensUsuarios[0]));
        usuarioArrayList_.add(new Usuario("Pedro", "pedro@ifms.edu.br", imagensUsuarios[0]));
    }

    private void setAdapter() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(usuarioArrayList_);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        //***
        //início exemplo de swipe para excluir um item
            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    //Remove swiped item from list and notify the RecyclerView
                    int position = viewHolder.getAdapterPosition();
                    usuarioArrayList_.remove(position);
                    recyclerAdapter.notifyDataSetChanged();

                }
            };

            ItemTouchHelper touchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            touchHelper.attachToRecyclerView(recyclerView);
        //final código swipe para excluir um item
        //***

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }


}
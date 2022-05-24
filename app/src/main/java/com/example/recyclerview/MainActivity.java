package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

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

//        Recycler View com grid ao invés de lista:
//        ***
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        ***

        //***
        //início exemplo de swipe para excluir um item
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //remove o item da lista e atualiza o RecyclerView
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
package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;


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
                ItemTouchHelper.LEFT) {

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

                /*
                Como remover do banco de dados com swipe
                 */

                //receber objeto clicado: todo fazer isso antes de remover na linha 89
                //Usuario usuario = recyclerAdapter.usuarioArrayList.get(position);

                // deletar objeto do banco:
                //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                //databaseReference.child("Usuario").child(usuario.getId()).setValue(null); //todo testar com removeValue
            }

            @Override
            public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
                return 1f;
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                setDeleteIcon(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            private void setDeleteIcon(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Paint mClearPaint = new Paint();
                mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

                ColorDrawable mBackground = new ColorDrawable();

                int backgroundColor = Color.parseColor("#00FFFF");
                Drawable deleteDrawable = ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.ic_delete);

                int intrinsicWidth = deleteDrawable.getIntrinsicWidth();
                int intrinsicHeight = deleteDrawable.getIntrinsicHeight();

                View itemView = viewHolder.itemView;
                int itemHeight = itemView.getHeight();

                boolean isCancelled = dX == 0 && !isCurrentlyActive;

                if(isCancelled){
                    c.drawRect(itemView.getRight()+dX,
                            (float) itemView.getTop(),
                            (float) itemView.getRight(),
                            (float) itemView.getBottom(),
                            mClearPaint);
                    return;
                }

                mBackground.setColor(backgroundColor);

                mBackground.setBounds(itemView.getRight() + (int) dX,
                        itemView.getTop()+70, //valores opcionais, posições do background em relação ao item
                        itemView.getRight(),
                        itemView.getBottom()-50);
                mBackground.draw(c);

                int deleteIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                int deleteIconMargin = (itemHeight-intrinsicHeight)/2;
                int deleteIconLeft = itemView.getRight() - deleteIconMargin - intrinsicWidth;
                int deleteIconRight = itemView.getRight() - deleteIconMargin;
                int deleteItonBottom = deleteIconTop + intrinsicHeight;

                deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteItonBottom);
                deleteDrawable.draw(c);

            }

        };

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        touchHelper.attachToRecyclerView(recyclerView);
        //final código swipe para excluir um item
        //***


        //editado para swipe
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //aqui
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

}
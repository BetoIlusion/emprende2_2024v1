package com.app.emprende2_2024.view.VNotaVenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.emprende2_2024.R;
import com.app.emprende2_2024.controller.CNotaVenta.CNotaVenta;
import com.app.emprende2_2024.model.MNotaVenta.NotaVenta;
import com.app.emprende2_2024.model.MPersona.Persona;
import com.app.emprende2_2024.view.VPersona.VPersonaMain;
import com.app.emprende2_2024.view.VProducto.VProductoMain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnPersona, btnProductos;
    ImageButton btnCrear;
    RecyclerView recyclerView;
    ListaNotaVentaAdapter adapter;

    public RecyclerView getRecyclerView() {
        return findViewById(R.id.rvMain);
    }
    public ImageButton getBtnCrear() {
        return findViewById(R.id.btnPedidoCrear);
    }

    public Button getBtnProductos() {
        return findViewById(R.id.btnProducto);
    }

    public void setBtnProductos(Button btnProductos) {
        this.btnProductos = btnProductos;
    }

    public Button getBtnPersona() {
        return findViewById(R.id.btnFactura_Persona);
    }

    public void setBtnPersona(Button btnPersona) {
        this.btnPersona = btnPersona;
    }

    CNotaVenta controller = new CNotaVenta(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        llenar();
        btnPersona = findViewById(R.id.btnFactura_Persona);
        btnPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VPersona();
            }
        });
        getBtnProductos().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VProducto();
            }
        });
        getBtnCrear().setOnClickListener(v -> vInsertar());
    }

    private void llenar() {
        controller.read();
    }

    private void vInsertar() {
        Intent intent = new Intent(this, VNotaVentaInsertar.class);
        startActivity(intent);
    }

    private void VProducto() {
        Intent intent = new Intent(this, VProductoMain.class);
        startActivity(intent);
    }

    private void VPersona() {
        Intent intent = new Intent(this, VPersonaMain.class);
        startActivity(intent);
    }

    public void read(ArrayList<NotaVenta> facturas, ArrayList<Persona> personas) {
        adapter = new ListaNotaVentaAdapter(facturas,personas);
        try {
            getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
            getRecyclerView().setAdapter(adapter);
        }catch (Exception e){
            mensaje("ERROR EN RECICLERVIEW");
            e.printStackTrace();
        }
    }

    private void mensaje(String errorEnReciclerview) {
        Toast.makeText(this, errorEnReciclerview, Toast.LENGTH_LONG).show();
    }
}
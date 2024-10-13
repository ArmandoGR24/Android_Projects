package com.codigomaestro.tienda;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Products> productos = new ArrayList<>();
        productos.add(new Products("Google Pixel 7 Pro", "Google Pixel 7 Pro 128 GB nieve 8 GB RAM", "https://www.mercadolibre.com.mx/google-pixel-7-pro-128-gb-nieve-8-gb-ram/p/MLM19891901#wid%3DMLM2151833147%26sid%3Dsearch%26searchVariation%3DMLM19891901%26position%3D4%26search_layout%3Dstack%26type%3Dproduct%26tracking_id%3D749cc5e1-0f8b-40d7-af54-149078f92acf", R.drawable.pixel7));
        productos.add(new Products("Galaxy Tab S9 Ultra", "Tablet Samsung Galaxy Tab S9 Ultra Sm-x910nzeamxo De 256gb", "https://www.mercadolibre.com.mx/tablet-samsung-galaxy-tab-s9-ultra-sm-x910nzeamxo-de-256gb/p/MLM25956299#wid%3DMLM2018653093%26sid%3Dsearch%26searchVariation%3DMLM25956299%26position%3D2%26search_layout%3Dstack%26type%3Dproduct%26tracking_id%3Dccf168cc-4215-47b0-81a5-e6abb5b245d7", R.drawable.s9ultra));
        productos.add(new Products("Consola Asus Rog Ally", "Consola Asus Rog Ally Amd Ryzen Z1 Extreme 16gb Ram 512 Ssd Color Blanco", "https://www.mercadolibre.com.mx/consola-asus-rog-ally-amd-ryzen-z1-extreme-16gb-ram-512-ssd-color-blanco/p/MLM24285410#wid%3DMLM2537405344%26sid%3Dsearch%26searchVariation%3DMLM24285410%26position%3D2%26search_layout%3Dstack%26type%3Dproduct%26tracking_id%3Dc1b474d1-b17a-44ec-a8c2-b05ed66087d7", R.drawable.rog));
        productos.add(new Products("Laptop Asus Tuf Gaming", "Laptop Asus Tuf Gaming 15.6 Rtx 4070 I7 13620h 16gb 1tb W11h Color Gris", "https://www.mercadolibre.com.mx/laptop-asus-tuf-gaming-156-rtx-4070-i7-13620h-16gb-1tb-w11h-color-gris/p/MLM37758355#wid%3DMLM3372183024%26sid%3Dsearch%26searchVariation%3DMLM37758355%26position%3D5%26search_layout%3Dstack%26type%3Dproduct%26tracking_id%3Da3abd7ea-71ef-4d51-b526-550333b18839", R.drawable.f15));
        productos.add(new Products("Silla de escritorio", "Silla de escritorio Asus ROG Chariot gamer ergonómica negra con tapizado de cuero sintético", "https://www.mercadolibre.com.mx/silla-de-escritorio-asus-rog-chariot-gamer-ergonomica-negra-con-tapizado-de-cuero-sintetico/p/MLM18335959#wid%3DMLM3377945772%26sid%3Dsearch%26searchVariation%3DMLM18335959%26position%3D1%26search_layout%3Dstack%26type%3Dproduct%26tracking_id%3D9c6cdfb1-0315-47de-b05f-79909de313a6", R.drawable.silla));
        ProductoAdapter adapter = new ProductoAdapter(productos, this);
        recyclerView.setAdapter(adapter);
    }
}
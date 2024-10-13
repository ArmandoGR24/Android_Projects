package com.codigomaestro.tienda;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Products> productos;
    private Context context;

    public ProductoAdapter(List<Products> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Products producto = productos.get(position);
        holder.nombreProducto.setText(producto.getNombre());
        holder.descripcionProducto.setText(producto.getDescripcion());
        holder.imagenProducto.setImageResource(producto.getImagen());
        holder.urlProducto.setText(producto.getUrl());
        holder.urlProducto.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(producto.getUrl()));
            context.startActivity(browserIntent);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenProducto;
        TextView nombreProducto;
        TextView descripcionProducto;
        TextView urlProducto;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenProducto = itemView.findViewById(R.id.imagenProducto);
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            descripcionProducto = itemView.findViewById(R.id.descripcionProducto);
            urlProducto = itemView.findViewById(R.id.urlProducto);
        }
    }
}
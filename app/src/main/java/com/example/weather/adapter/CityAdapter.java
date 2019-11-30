package com.example.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.models.CityModel;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CitiesViewHolder> {

    private List<CityModel> cities;
    private final Context context;

    public CityAdapter(Context context, List<CityModel> cities) {
        this.context = context;
        this.cities = cities;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_cities, parent, false);
        CitiesViewHolder viewHolder = new CitiesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        CityModel city = cities.get(position);
        holder.textViewCityNome.setText(city.getName());//atualizar esse campo adaptar para o nosso contexto
        holder.textViewCityTemp.setText(city.getMain().getTemp().toString());//atualizar esse campo adaptar para o nosso contexto
    }


    @Override
    public int getItemCount() {
        return this.cities != null ? this.cities.size() : 0;
    }

    public static class CitiesViewHolder extends RecyclerView.ViewHolder {
            TextView textViewCityNome;
            TextView textViewCityTemp;

            public CitiesViewHolder(View view){
                super(view);
                textViewCityNome = (TextView) view.findViewById(R.id.city_nome);
                textViewCityTemp = (TextView) view.findViewById(R.id.city_temp);
            }

    }
}

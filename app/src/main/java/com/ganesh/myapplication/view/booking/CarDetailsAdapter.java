package com.ganesh.myapplication.view.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;

import com.ganesh.myapplication.R;
import com.ganesh.myapplication.databinding.AdapterCarDetailsBinding;
import com.ganesh.myapplication.model.CarData;

import java.util.ArrayList;

//
public class CarDetailsAdapter extends RecyclerView.Adapter<CarDetailsAdapter.CarHolder> {

    ArrayList<CarData> carDtatLits;

    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterCarDetailsBinding adapterCarDetailsBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.adapter_car_details, parent, false);
        return new CarHolder(adapterCarDetailsBinding);
    }

    @Override
    public int getItemCount() {
        return carDtatLits == null ? 0 : carDtatLits.size();
    }


    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, final int position) {
        final CarData carData = carDtatLits.get(position);
        holder.carDetailsBinding.setModel(carData);

    }

    public void updateData(ArrayList<CarData> carModel) {
        this.carDtatLits = carModel;
        notifyDataSetChanged();
    }

    class CarHolder extends RecyclerView.ViewHolder {

        private AdapterCarDetailsBinding carDetailsBinding;

        public CarHolder(@NonNull AdapterCarDetailsBinding carDetailsBinding) {
            super(carDetailsBinding.getRoot());
            this.carDetailsBinding = carDetailsBinding;
        }
    }


}

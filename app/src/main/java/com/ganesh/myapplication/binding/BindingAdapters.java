package com.ganesh.myapplication.binding;
//
// Created by  on 2020-01-02.
//

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter("imageFromRemote")
    public static void showHide(ImageView view, String imageURL) {
        if (imageURL != null)
            Picasso.with(view.getContext()).load(imageURL).into(view);
    }

    @BindingAdapter("viewVisibilites")
    public static void viewVisibilites(View view, Boolean visibilitesSatus) {
        if (visibilitesSatus == null) {
            view.setVisibility(View.GONE);
        } else if (visibilitesSatus) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}



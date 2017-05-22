package io.toru.instagramsearch.detail;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.databinding.RowSelectedImageBinding;
import io.toru.instagramsearch.main.model.InstagramItemModel;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    private static final String TAG = DetailAdapter.class.getSimpleName();

    private InstagramModel model;
    private ArrayList<InstagramItemModel> itemModelList;

    public DetailAdapter(InstagramModel model) {
        this.model = model;
        if(model.getItemList() != null){
            itemModelList = new ArrayList<>(Arrays.asList(model.getItemList()));
        }
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowSelectedImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.row_selected_image, parent, false);
        return new DetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        holder.bind(itemModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder{
        RowSelectedImageBinding binding;

        public DetailViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = (RowSelectedImageBinding)viewDataBinding;
        }

        public void bind(InstagramItemModel itemModel){
            binding.setInstagramModel(itemModel);
            binding.executePendingBindings();
            loadImage();
        }

        private void loadImage(){
            Glide.with(binding.getRoot().getContext())
                    .load(binding.getInstagramModel().getImages().getStandardResolution().getUrl())
                    .into(binding.imgSelected);
        }
    }
}

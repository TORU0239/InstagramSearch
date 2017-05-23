package io.toru.instagramsearch.detail.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.listener.OnInfiniteScrollListener;
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

    private OnInfiniteScrollListener infiniteScrollListener;

    public DetailAdapter(InstagramModel model) {
        this.model = model;
        if(model.getItemList() != null){
            itemModelList = new ArrayList<>(Arrays.asList(model.getItemList()));
        }
    }

    public DetailAdapter(ArrayList<InstagramItemModel> itemModelList, OnInfiniteScrollListener infiniteScrollListener) {
        this.itemModelList = itemModelList;
        this.infiniteScrollListener = infiniteScrollListener;
    }

    public DetailAdapter(InstagramModel model, OnInfiniteScrollListener infiniteScrollListener) {
        this.model = model;
        if(model.getItemList() != null){
            itemModelList = new ArrayList<>(Arrays.asList(model.getItemList()));
        }
        this.infiniteScrollListener = infiniteScrollListener;
    }

    public void setInstagramModel(InstagramModel model){
        itemModelList.addAll(new ArrayList<>(Arrays.asList(model.getItemList())));
        notifyDataSetChanged();
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowSelectedImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.row_selected_image, parent, false);
        return new DetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        if(position == getItemCount() - 1){
            if(infiniteScrollListener != null){
                infiniteScrollListener.onLoadMore(itemModelList.get(position).getId());
            }
        }

        holder.bind(itemModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder{
        public RowSelectedImageBinding binding;

        public DetailViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = (RowSelectedImageBinding)viewDataBinding;
        }

        public void bind(InstagramItemModel itemModel){
            binding.setInstagramModel(itemModel);
            binding.executePendingBindings();
            loadImage();
        }

        public RowSelectedImageBinding getBinding() {
            return binding;
        }

        private void loadImage(){
            Glide.with(binding.getRoot().getContext())
                    .load(binding.getInstagramModel().getImages().getStandardResolution().getUrl())
                    .into(binding.imgSelected);
        }
    }
}

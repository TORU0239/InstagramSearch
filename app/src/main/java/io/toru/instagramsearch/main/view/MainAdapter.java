package io.toru.instagramsearch.main.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.base.listener.OnInfiniteScrollListener;
import io.toru.instagramsearch.databinding.RowSearchedImageBinding;
import io.toru.instagramsearch.detail.DetailActivity;
import io.toru.instagramsearch.main.model.InstagramItemModel;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private RowSearchedImageBinding dataBinding;

    private ArrayList<InstagramItemModel> itemModelList;
    private OnInfiniteScrollListener infiniteScrollListener;


    public MainAdapter(ArrayList<InstagramItemModel> itemModelList, OnInfiniteScrollListener infiniteScrollListener) {
        this.itemModelList = itemModelList;
        this.infiniteScrollListener = infiniteScrollListener;
    }

    public void setInfiniteScrollListener(OnInfiniteScrollListener infiniteScrollListener) {
        this.infiniteScrollListener = infiniteScrollListener;
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_searched_image, parent, false);
        return new MainViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
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

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private final RowSearchedImageBinding binding;

        public MainViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = (RowSearchedImageBinding)viewDataBinding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(DetailActivity.getDetailActivityIntent(v.getContext(),
                            binding.getInstagramModel()));
                }
            });
        }

        public RowSearchedImageBinding getBinding() {
            return binding;
        }

        public void bind(InstagramItemModel model) {
            binding.setInstagramModel(model);
            binding.executePendingBindings();
            loadImage();
        }

        private void loadImage(){
            Glide.with(binding.getRoot().getContext())
                    .load(binding.getInstagramModel().getImages().getStandardResolution().getUrl())
                    .into(binding.imgSearched);
        }
    }
}
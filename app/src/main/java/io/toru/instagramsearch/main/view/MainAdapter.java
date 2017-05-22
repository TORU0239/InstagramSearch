package io.toru.instagramsearch.main.view;

import android.content.Intent;
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
import io.toru.instagramsearch.base.listener.OnInfiniteScrollListener;
import io.toru.instagramsearch.databinding.RowSearchedImageBinding;
import io.toru.instagramsearch.detail.DetailActivity;
import io.toru.instagramsearch.main.model.InstagramItemModel;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private static final String TAG = MainAdapter.class.getSimpleName();

    private InstagramModel instagramModel;
    private ArrayList<InstagramItemModel> itemModelList;
    private OnInfiniteScrollListener infiniteScrollListener;

    public MainAdapter(OnInfiniteScrollListener infiniteScrollListener) {
        this.infiniteScrollListener = infiniteScrollListener;
    }

    public void setInstagramModel(InstagramModel instagramModel) {
        this.instagramModel = instagramModel;
        this.itemModelList = new ArrayList<>(Arrays.asList(instagramModel.getItemList()));
        notifyDataSetChanged();
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowSearchedImageBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_searched_image, parent, false);
        return new MainViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
        if(position == getItemCount() - 1){
            if(infiniteScrollListener != null){
                infiniteScrollListener.onLoadMore(itemModelList.get(position).getId());
            }
        }

        if(instagramModel != null){
            holder.bind(instagramModel, itemModelList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(itemModelList == null) {
            return 0;
        }
        return itemModelList.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = MainViewHolder.class.getSimpleName();
        private final RowSearchedImageBinding binding;

        public MainViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = (RowSearchedImageBinding)viewDataBinding;
        }

        public RowSearchedImageBinding getBinding() {
            return binding;
        }

        public void bind(final InstagramModel totalModel, final InstagramItemModel model) {
            binding.setInstagramTotalModel(totalModel);
            binding.setInstagramModel(model);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    v.getContext().startActivity(DetailActivity.getDetailActivityIntent(v.getContext(),
//                            binding.getInstagramTotalModel()));
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);

                    ArrayList<InstagramItemModel> modelList = new ArrayList<InstagramItemModel>(Arrays.asList(binding.getInstagramTotalModel().getItemList()));
                    intent.putExtra("models", modelList);
                    v.getContext().startActivity(intent);
                }
            });

            loadImage();
        }

        private void loadImage(){
            Glide.with(binding.getRoot().getContext())
                    .load(binding.getInstagramModel().getImages().getStandardResolution().getUrl())
                    .into(binding.imgSearched);
        }
    }
}
package io.toru.instagramsearch.main.view;

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
import io.toru.instagramsearch.detail.view.DetailActivity;
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
    private String searchedId;

    public MainAdapter(OnInfiniteScrollListener infiniteScrollListener) {
        this.itemModelList = new ArrayList<>();
        this.infiniteScrollListener = infiniteScrollListener;
    }

    public void setInstagramModel(String searchedId, InstagramModel instagramModel) {
        setInstagramModel(searchedId, instagramModel, false);
    }

    public void setInstagramModel(String searchedId, InstagramModel instagramModel, boolean clear){
        this.searchedId = searchedId;
        this.instagramModel = instagramModel;
        if(clear){
            this.itemModelList.clear();
        }
        this.itemModelList.addAll(new ArrayList<>(Arrays.asList(instagramModel.getItemList())));
        notifyDataSetChanged();
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowSearchedImageBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_searched_image, parent, false);
        return new MainViewHolder(dataBinding, itemModelList);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
        if(position == getItemCount() - 1){
            if(infiniteScrollListener != null){
                infiniteScrollListener.onLoadMore(itemModelList.get(position).getId());
            }
        }

        if(instagramModel != null){
            holder.bind(searchedId, itemModelList.get(position), instagramModel);
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
        private ArrayList<InstagramItemModel> itemModelList;

        public MainViewHolder(ViewDataBinding viewDataBinding, ArrayList<InstagramItemModel> modelList) {
            super(viewDataBinding.getRoot());
            binding = (RowSearchedImageBinding)viewDataBinding;
            itemModelList = modelList;
        }

        public RowSearchedImageBinding getBinding() {
            return binding;
        }

        public void bind(final String searchedID, final InstagramItemModel itemModel, final InstagramModel model) {
            binding.setSearchedID(searchedID);
            binding.setInstagramItemModel(itemModel);
            binding.setInstagramModel(model);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w(TAG, "current id : " + itemModel.getId());
                    v.getContext().startActivity(DetailActivity.getDetailActivityIntent(v.getContext(),
                            searchedID, itemModelList, model.isMoreAvailable(), itemModel.getId()));
                }
            });

            loadImage();
        }

        private void loadImage(){
            Glide.with(binding.getRoot().getContext())
                    .load(binding.getInstagramItemModel().getImages().getStandardResolution().getUrl())
                    .into(binding.imgSearched);
        }
    }
}
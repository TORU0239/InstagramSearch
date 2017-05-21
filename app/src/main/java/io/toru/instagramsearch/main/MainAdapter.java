package io.toru.instagramsearch.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.toru.instagramsearch.R;
import io.toru.instagramsearch.databinding.RowSearchedImageBinding;
import io.toru.instagramsearch.main.model.InstagramItemModel;
import io.toru.instagramsearch.main.model.InstagramModel;

/**
 * Created by wonyoung on 2017. 5. 19..
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private RowSearchedImageBinding dataBinding;
    private InstagramModel model;

    public MainAdapter(InstagramModel model) {
        this.model = model;
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_searched_image, parent, false);
        return new MainViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
        holder.bind(model.getItemList()[position]);
    }

    @Override
    public int getItemCount() {
        return model.getItemList().length;
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private RowSearchedImageBinding binding;

        public MainViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (RowSearchedImageBinding) binding;
        }

        public RowSearchedImageBinding getBinding() {
            return binding;
        }

        public void bind(InstagramItemModel model) {
            binding.setInstagramModel(model);
            binding.executePendingBindings();
        }
    }
}
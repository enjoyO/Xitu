package com.lulee007.xitu.adapter;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lulee007.xitu.R;
import com.lulee007.xitu.base.XTBaseAdapter;
import com.lulee007.xitu.models.Author;
import com.marshalchen.ultimaterecyclerview.animators.internal.ViewHelper;

/**
 * User: lulee007@live.com
 * Date: 2015-12-09
 * Time: 17:27
 */
public class AuthorAdapter extends XTBaseAdapter<Author> {


    @Override
    public ViewHolder getViewHolder(View view) {
        AuthorViewHolder authorViewHolder = new AuthorViewHolder(view);
        return authorViewHolder;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_item, null);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isItemViewHolder(position)) {
            AuthorViewHolder authorViewHolder = (AuthorViewHolder) holder;
            Author author = getItem(position);
            if (author != null) {
                authorViewHolder.name.setText(author.getUsername());
                authorViewHolder.description.setText(author.getSelf_description());
                Glide.with(authorViewHolder.icon.getContext())
                        .load(author.getAvatar_large())
                        .crossFade()
                        .into(authorViewHolder.icon);
                if (!isFirstOnly || position > mLastPosition) {
                    for (Animator anim : getAdapterAnimations(holder.itemView, AdapterAnimationType.ScaleIn)) {
                        anim.setDuration(200).start();
                        anim.setInterpolator(new LinearInterpolator());
                    }
                    mLastPosition = position;
                } else {
                    ViewHelper.clear(holder.itemView);
                }
            }
        }
    }


    @Override
    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder, int position) {

    }

    public class AuthorViewHolder extends ViewHolder {

        public TextView name;
        public TextView description;

        public ImageView icon;

        public AuthorViewHolder(View itemView) {


            super(itemView);
            name = (TextView) itemView.findViewById(R.id.author_name);
            icon = (ImageView) itemView.findViewById(R.id.author_icon);
            description = (TextView) itemView.findViewById(R.id.author_description);

        }
    }


}
package com.wtl.right.widget.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wtl.right.R;
import com.wtl.right.widget.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Function : 底部弹出的列表项 有宫格和列表两种
 * Remarks  :
 * Created by Mr.C on 2018/3/26 0026.
 */

public class BottomSeetDialog extends DialogFragment {

    public View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes(); //不放在onActivityCreated，避免闪耀bug
        layoutParams.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutParams);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.bottom_dialog_anim;
        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    public static class Builder {
        private ArrayList<Item> itemArrayList = new ArrayList<>();
        private RecyclerItemClickListener listener;
        private Context mContext;
        private LayoutInflater layoutInflater;

        public Builder(Context mContext) {
            this.mContext = mContext;
            layoutInflater = LayoutInflater.from(mContext);
        }

        public Builder addItem(CharSequence content) {
            return addItem(content, 0);
        }

        public Builder addItem(CharSequence content, int icon) {
            Drawable image = icon != 0 ? ContextCompat.getDrawable(mContext, icon) : null;
            Item item = new Item(content, image);
            return addItem(item);
        }

        public Builder addItem(Item item) {
            itemArrayList.add(item);
            return this;
        }

        public Builder setOnItemClickListener(RecyclerItemClickListener.OnItemClickListener onItemClickListener) {
            listener = new RecyclerItemClickListener(mContext, onItemClickListener);
            return this;
        }

        BottomSeetDialog dialog;

        public BottomSeetDialog create() {
            dialog = createDialog();
            if (listener != null) {
                listener.setDialog(dialog);
            }
            return dialog;
        }

        private BottomSeetDialog createDialog() {
            BottomSeetDialog dialog = new BottomSeetDialog();
            View layoutView = layoutInflater.inflate(R.layout.dg_bottomseet, null);
            dialog.contentView = layoutView;
            LinearLayout linearLayout = layoutView.findViewById(R.id.bottom_dg_layout);
            RecyclerView recyclerView = new RecyclerView(mContext);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            recyclerView.setLayoutParams(layoutParams);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            if (listener != null) {
                recyclerView.addOnItemTouchListener(listener);
            }
            if (itemArrayList != null && itemArrayList.size() > 0) {
                recyclerView.setAdapter(new ItemAdapter(itemArrayList));
            }
            linearLayout.addView(recyclerView);
            return dialog;
        }

        public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

            private ArrayList<Item> itemArrayList;

            public ItemAdapter(ArrayList<Item> itemArrayList) {
                this.itemArrayList = itemArrayList;
            }

            @Override
            public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottomseet, parent, false);
                view.setOnClickListener(null); //不这样设置, 点击没有效果
                return new ItemHolder(view);
            }

            @Override
            public void onBindViewHolder(ItemHolder holder, int position) {
                Item itembean = itemArrayList.get(position);
                if (itembean.getImage() != null) {
                    holder.item_img.setImageDrawable(itembean.getImage());
                } else {
                    holder.item_img.setVisibility(View.GONE);
                }
                holder.tem_title.setText(itembean.getContent());
            }

            @Override
            public int getItemCount() {
                return itemArrayList == null ? 0 : itemArrayList.size();
            }

            public class ItemHolder extends RecyclerView.ViewHolder {

                ImageView item_img;
                TextView tem_title;

                public ItemHolder(View itemView) {
                    super(itemView);
                    item_img = itemView.findViewById(R.id.bottom_dialog_list_item_img);
                    tem_title = itemView.findViewById(R.id.bottom_dialog_list_item_title);
                }
            }
        }

        public class Item {
            private CharSequence content;
            private Drawable image;
            private boolean isEnable;

            public Item(CharSequence content) {
                this.content = content;
            }

            public Item(CharSequence content, Drawable image) {
                this.content = content;
                this.image = image;
            }

            public CharSequence getContent() {
                return content;
            }

            public void setContent(CharSequence content) {
                this.content = content;
            }

            public Drawable getImage() {
                return image;
            }

            public void setImage(Drawable image) {
                this.image = image;
            }

            public boolean isEnable() {
                return isEnable;
            }

            public void setEnable(boolean enable) {
                isEnable = enable;
            }
        }
    }


}

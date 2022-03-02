package de.hshl.isd.mensa.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import de.hshl.isd.mensa.R;

class MainListAdapter extends ListAdapter<ItemViewModel, MainListAdapter.ItemViewHolder> {

    public MainListAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<ItemViewModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ItemViewModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull ItemViewModel oldItem,
                        @NonNull ItemViewModel newItem) {
                    return oldItem.getText().equals(newItem.getText());
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull ItemViewModel oldItem,
                        @NonNull ItemViewModel newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
            int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_header,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemViewModel item = getItem(position);
        holder.getText().setText(item.getText());
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }

        public TextView getText() {
            return text;
        }
    }
}

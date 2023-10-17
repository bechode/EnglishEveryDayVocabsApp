package com.lenguage.englisheverydayvocabsapp.ui.vocabulary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenguage.englisheverydayvocabsapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenguage.englisheverydayvocabsapp.data.entity.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // region Constants
    protected static final int ITEM_LEFT = 0;
    protected static final int ITEM_RIGHT = 1;
    // endregion

    // region Member Variables
    protected List<Vocabulary> items;

    public VocabularyAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case ITEM_LEFT:
                viewHolder = createItemLeftViewHolder(parent);
                break;
            case ITEM_RIGHT:
                viewHolder = createItemRightViewHolder(parent);
                break;
            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_LEFT:
                bindItemLeftViewHolder(holder, position);
                break;
            case ITEM_RIGHT:
                bindItemRightViewHolder(holder, position);
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        //Log.d("ItemViewType", "Valor item es: " + ((isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM) + ", position: " + position);
        return (position%2 == 0.0) ? ITEM_LEFT : ITEM_RIGHT;
    }


    protected RecyclerView.ViewHolder createItemLeftViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocab_list_item_left, parent, false);
        VocabularyLeftViewHolder leftViewHolder = new VocabularyLeftViewHolder(view);
        return leftViewHolder;
    }

    protected RecyclerView.ViewHolder createItemRightViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocab_list_item_right, parent, false);
        VocabularyRightViewHolder rightViewHolder = new VocabularyRightViewHolder(view);
        return rightViewHolder;
    }

    protected void bindItemLeftViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        final Vocabulary v = getItem(position);
        //if(viewHolder.getItemViewType() == ITEM){
        VocabularyLeftViewHolder itemViewHolder = (VocabularyLeftViewHolder)viewHolder;
        itemViewHolder.bind(v);
        //}
        //Log.d("Item","Es el bind");
    }

    protected void bindItemRightViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        final Vocabulary v = getItem(position);
        VocabularyRightViewHolder itemViewHolder = (VocabularyRightViewHolder)viewHolder;
        itemViewHolder.bind(v);
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    // region Helper Methods
    public Vocabulary getItem(int position) {
        try {
            return items.get(position);
        } catch (Exception ex) {
            return null;
        }

    }

    public List<Vocabulary> getAllItems() {
        return items;
    }

    public void add(Vocabulary item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void add(int index, Vocabulary item) {
        items.add(index, item);
        //notifyItemInserted(index);
        notifyDataSetChanged();
    }

    public void setFilter(List<Vocabulary> itemsNuevos) {
        items = new ArrayList<>();
        if(itemsNuevos != null) {
            items.addAll(itemsNuevos);
            notifyDataSetChanged();
        }
    }

    public void addAll(List<Vocabulary> items) {
        for (Vocabulary item : items) {
            add(item);
        }
    }

    public void remove(Vocabulary item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void remove(int position) {
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    /*
    Permite limpiar todos los elementos del recycler
     */
    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public boolean isLastPosition(int position) {
        return (position == items.size()-1);
    }

    public class VocabularyLeftViewHolder extends RecyclerView.ViewHolder {

        private TextView textoVocabulary;
        private TextView textoTranslate;

        public VocabularyLeftViewHolder(@NonNull View itemView) {
            super(itemView);

            textoVocabulary = (TextView) itemView.findViewById(R.id.wordTextView);
            textoTranslate = (TextView) itemView.findViewById(R.id.translateTextView);
        }

        public void bind(Vocabulary v) {
            if(v != null) {
                textoVocabulary.setText(v.getWord());
                textoTranslate.setText(v.getSpanishTranslation());
            }
        }
    }

    public class VocabularyRightViewHolder extends RecyclerView.ViewHolder {

        private TextView textoVocabulary;
        private TextView textoTranslate;

        public VocabularyRightViewHolder(@NonNull View itemView) {
            super(itemView);

            textoVocabulary = (TextView) itemView.findViewById(R.id.wordTextView);
            textoTranslate = (TextView) itemView.findViewById(R.id.translateTextView);
        }

        public void bind(Vocabulary v) {
            if(v != null) {
                textoVocabulary.setText(v.getWord());
                textoTranslate.setText(v.getSpanishTranslation());
            }
        }
    }
}

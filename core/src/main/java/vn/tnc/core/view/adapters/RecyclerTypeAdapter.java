package vn.tnc.core.view.adapters;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 5/28/2015.
 */
public abstract class RecyclerTypeAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final String TAG;
    private List<T> dataSet = new ArrayList<T>();

    public RecyclerTypeAdapter() {
        TAG = getClass().getSimpleName();
    }

    public List<T> getDataSet() {
        return dataSet;
    }

    public void changeDataSet(List<T> newDataSet) {
        dataSet.clear();

        if (newDataSet != null && newDataSet.size() > 0) {
            dataSet.addAll(newDataSet);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @SuppressWarnings("SimplifiableIfStatement")
    public boolean update(T data) {
        int indexOfData = dataSet.indexOf(data);
        if (indexOfData == -1) return false;
        return update(data, indexOfData);
    }

    public boolean update(T data, int position) {
        if (data == null || position < 0 || position >= dataSet.size()) {
            return false;
        }
        T oldData = dataSet.set(position, data);
        notifyItemChanged(position);
        return oldData != null;
    }

    public void add(T item) {
        if (item == null) {
            return;
        }
        dataSet.add(item);
        notifyItemInserted(dataSet.indexOf(item));
    }

    public void add(T item, int position) {
        if (item == null || position < 0 || position > dataSet.size()) {
            return;
        }
        dataSet.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(int position, List<T> items) {
        if (items == null || items.size() == 0 || position < 0 || position > dataSet.size()) {
            return;
        }
        dataSet.addAll(position, items);
        notifyItemRangeInserted(position, items.size());
    }

    public void remove(T data) {
        if (dataSet.contains(data)) {
            remove(dataSet.indexOf(data));
        }
    }

    public void remove(int position) {
        if (position >= 0 && position < getItemCount()) {
            dataSet.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void remove(int position, int size) {
        if (position < 0 || size <= 0 || position >= dataSet.size()) {
            return;
        }
        int removeSize = Math.min(size, dataSet.size() - position);
        for (int i = 0; i < removeSize; i++) {
            dataSet.remove(position);
        }
        notifyItemRangeRemoved(position, removeSize);
    }

    public T getItem(int position) {
        if (position < 0 || position >= dataSet.size()) return null;
        return dataSet.get(position);
    }

    public int getItemIndex(T item) {
        return dataSet.indexOf(item);
    }

    public void clearItems() {
        dataSet.clear();
        notifyDataSetChanged();
    }

}
package com.intern.kartcorner.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.intern.kartcorner.R;
import com.intern.kartcorner.model.ProductCommonClass;

public class AutoCompleteAdapter extends ArrayAdapter<ProductCommonClass> {

    private ArrayList<String> fullList;
    private ArrayList<String> mOriginalValues;
    private ArrayFilter mFilter;
    private ArrayList<ProductCommonClass> productCommonClasses;

    private int resourceId;
    private Context context;
    private List<ProductCommonClass> items, tempItems, suggestions;

    public AutoCompleteAdapter(Context context, int resource, ArrayList<ProductCommonClass> productCommonClasses) {

        super(context, resource, productCommonClasses);
        this.items = productCommonClasses;
        this.context = context;
        this.resourceId = resource;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ProductCommonClass getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
            }
            ProductCommonClass commonClass = getItem(position);
            TextView name = (TextView) view.findViewById(R.id.singleTextviewSpinner);
            ImageView imageView = view.findViewById(R.id.singleImageSpinner);
            name.setText(commonClass.getProductname());
            Glide.with(context).load(commonClass.getProductimage()).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (fruitFilter == null) {
            fruitFilter = new ArrayFilter();
        }
        return fruitFilter;
    }

    private Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            ProductCommonClass fruit = (ProductCommonClass) resultValue;
            return fruit.getProductname();
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (ProductCommonClass fruit: tempItems) {
                    if (fruit.getProductname().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        suggestions.add(fruit);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<ProductCommonClass> tempValues = (ArrayList<ProductCommonClass>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (ProductCommonClass fruitObj : tempValues) {
                    add(fruitObj);
                }
                notifyDataSetChanged();
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
    private class ArrayFilter extends Filter {
        private Object lock;

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                synchronized (lock) {
                    mOriginalValues = new ArrayList<String>(fullList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    ArrayList<String> list = new ArrayList<String>(mOriginalValues);
                    results.values = list;
                    results.count = list.size();
                }
            } else {
                final String prefixString = prefix.toString().toLowerCase();

                ArrayList<String> values = mOriginalValues;
                int count = values.size();

                ArrayList<String> newValues = new ArrayList<String>(count);

                for (int i = 0; i < count; i++) {
                    String item = values.get(i);
                    if (item.toLowerCase().contains(prefixString)) {
                        newValues.add(item);
                    }

                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if(results.values!=null){
                fullList = (ArrayList<String>) results.values;
            }else{
                fullList = new ArrayList<String>();
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}

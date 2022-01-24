package com.example.demo.jsonpersoncat.mvvmtest1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.jsonpersoncat.R;
import com.example.demo.jsonpersoncat.databinding.AdapterMainBinding;

import java.util.ArrayList;
import java.util.List;

public class Test1MainAdapter extends RecyclerView.Adapter<Test1MainAdapter.ViewHolder> {
    private List<MpBean> list = new ArrayList<>();
    private LayoutInflater inflater;

    public Test1MainAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public Test1MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterMainBinding adapterMainBinding = DataBindingUtil.inflate(inflater, R.layout.adapter_main, parent, false);
        return new ViewHolder(adapterMainBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull Test1MainAdapter.ViewHolder holder, int position) {
        // 不再需要單獨對控制元件賦值，通過DataBinding資料繫結
        MpBean bean = list.get(position);
        if (bean != null) {
            holder.binding.setMp(bean);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), " ID : " + list.get(position).getOrder() + " \n " + " NAME : " + list.get(position).getName() ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MpBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AdapterMainBinding binding;

        // 不需要使用itemView進行findViewById，而是直接使用DataBinding
        ViewHolder(@NonNull AdapterMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.codexsstorm.finance.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codexsstorm.finance.Entity.BillDetailsEntity;
import com.example.codexsstorm.finance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder> {
    List<BillDetailsEntity> billList=new ArrayList<>();
    Context context;
    private CallBack mCallBack;

    public interface CallBack{
        void CallBillDetailsActivity(BillDetailsEntity billDetailsEntity);
    }

    public BillListAdapter(List<BillDetailsEntity> billList, Context context, CallBack mCallBack) {
        this.billList = billList;
        this.context = context;
        this.mCallBack = mCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_card,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final BillDetailsEntity billDetailsEntity = billList.get(position);
        holder.tvReason.setText(billDetailsEntity.getReason());
        holder.tvPaidFor.setText(billDetailsEntity.getPaid_for());
        holder.tvAmount.setText("$ "+billDetailsEntity.getAmount()+"");
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.CallBillDetailsActivity(billList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmount;
        TextView tvPaidFor;
        TextView tvReason;
        View cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            cardview = itemView;
            tvAmount = (TextView)itemView.findViewById(R.id.tvAmount);
            tvPaidFor = (TextView)itemView.findViewById(R.id.tvPaidTo);
            tvReason = (TextView)itemView.findViewById(R.id.tvReason);
        }
    }
}

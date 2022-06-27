package com.anas.cashier.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.cashier.Interface.OnClickNotification;
import com.anas.cashier.R;
import com.anas.cashier.RoomDB.Tables.Notification;
import com.anas.cashier.databinding.CustomItemNotificationBinding;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class ListNotificationAdapter extends RecyclerView.Adapter<ListNotificationAdapter.NotificationViewHolder> {

    List<com.anas.cashier.RoomDB.Tables.Notification> list;
    OnClickNotification listener;
    private final ViewBinderHelper viewBinderHelper=new ViewBinderHelper();



    public ListNotificationAdapter(List<com.anas.cashier.RoomDB.Tables.Notification> list,OnClickNotification listener) {
        this.list = list;
        this.listener=listener;
    }

    public List<com.anas.cashier.RoomDB.Tables.Notification> getList() {
        return list;
    }

    public void setList(List<com.anas.cashier.RoomDB.Tables.Notification> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_notification,parent,false);
        NotificationViewHolder holder=new NotificationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        com.anas.cashier.RoomDB.Tables.Notification notification=list.get(position);
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(holder.binding.swipe,String.valueOf(list.get(position).getID()));
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        CustomItemNotificationBinding binding;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomItemNotificationBinding.bind(itemView);

        }

        void bind( com.anas.cashier.RoomDB.Tables.Notification notification){

            binding.image.setImageResource(notification.getImage());
            binding.text.setText(notification.getDetails());

            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClickItem(notification);
                }
            });
        }


    }
}

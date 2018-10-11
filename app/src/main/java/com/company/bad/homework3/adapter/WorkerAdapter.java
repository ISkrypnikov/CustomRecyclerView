package com.company.bad.homework3.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.bad.homework3.R;
import com.company.bad.homework3.data.Worker;

import java.util.Collections;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.PersonViewHolder>{

    List<Worker> workers;

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public WorkerAdapter(@NonNull List<Worker> workers){
        this.workers = workers;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        TextView age;
        TextView position;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.worker_name);
            imageView = (ImageView) itemView.findViewById(R.id.worker_photo);
            age = (TextView)itemView.findViewById(R.id.worker_age);
            position = (TextView)itemView.findViewById(R.id.worker_position);
        }
    }

    @NonNull
    @Override
    public WorkerAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.worker_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerAdapter.PersonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(workers.get(i).getName());
        personViewHolder.age.setText("Age : "+workers.get(i).getAge());
        personViewHolder.position.setText(workers.get(i).getPosition());
        personViewHolder.imageView.setImageResource(workers.get(i).getPhoto());
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    public void onItemDismiss(int position) {
        workers.remove(position);
        notifyItemRemoved(position);
    }

    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(workers,fromPosition,toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}

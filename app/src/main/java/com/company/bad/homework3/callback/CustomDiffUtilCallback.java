package com.company.bad.homework3.callback;

import android.support.v7.util.DiffUtil;

import com.company.bad.homework3.data.Worker;

import java.util.List;
import java.util.Objects;

public class CustomDiffUtilCallback extends DiffUtil.Callback{


    private final List<Worker> oldWorkers;
    private final List<Worker> newWorkers;
    public CustomDiffUtilCallback(List<Worker> oldWorkers, List<Worker> newWorkers){
        this.oldWorkers = oldWorkers;
        this.newWorkers = newWorkers;
    }

    @Override
    public int getOldListSize() {
        return oldWorkers.size();
    }

    @Override
    public int getNewListSize() {
        return newWorkers.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItem, int newItem) {
        return oldWorkers.get(oldItem).getId() == newWorkers.get(newItem).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItem, int newItem) {
        return
                (Objects.equals(oldWorkers.get(oldItem).getAge(), newWorkers.get(newItem).getAge())
                && Objects.equals(oldWorkers.get(oldItem).getName(), newWorkers.get(newItem).getName())
                && Objects.equals(oldWorkers.get(oldItem).getPosition(), newWorkers.get(newItem).getPosition())
                && Objects.equals(oldWorkers.get(oldItem).getPhoto(), newWorkers.get(newItem).getPhoto()));
    }
}

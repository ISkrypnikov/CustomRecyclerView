package com.company.bad.homework3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.company.bad.homework3.adapter.WorkerAdapter;
import com.company.bad.homework3.calback.CustomDiffUtilCallback;
import com.company.bad.homework3.calback.CustomItemTouchHelperCallback;
import com.company.bad.homework3.data.Worker;
import com.company.bad.homework3.data.WorkerGenerator;
import com.company.bad.homework3.decoration.CustomItemDecoration;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        final List<Worker> workers = WorkerGenerator.generateWorkers(30);
        final WorkerAdapter adapter = new WorkerAdapter(workers);
        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);

        CustomItemDecoration itemDecoration = new CustomItemDecoration(30);
        rv.addItemDecoration(itemDecoration);
        ItemTouchHelper.Callback callback = new CustomItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Worker> workers = WorkerGenerator.generateWorkers(30);

                Single<DiffUtil.DiffResult> single = Single.create(emitter -> {
                    CustomDiffUtilCallback diffCallback = new CustomDiffUtilCallback(adapter.getWorkers(),workers);
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, false);
                    emitter.onSuccess(diffResult);
                });
                single.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(diffResult -> {
                            adapter.setWorkers(workers);
                            diffResult.dispatchUpdatesTo(adapter);});
            }
        });
    }
}

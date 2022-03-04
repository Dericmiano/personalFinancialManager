package com.example.personalfinancialmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.personalfinancialmanager.adapter.OnTodoClickListener;
import com.example.personalfinancialmanager.adapter.RecycleViewAdapter;
import com.example.personalfinancialmanager.model.SharedViewModel;
import com.example.personalfinancialmanager.model.Spend;
import com.example.personalfinancialmanager.model.SpendViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnTodoClickListener {
    private SpendViewModel spendViewModel;
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    Bottom_Navigation_layout bottom_navigation_layout;
    private SharedViewModel sharedViewModel;
    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        bottom_navigation_layout = new Bottom_Navigation_layout();
        ConstraintLayout  constraintLayout = findViewById(R.id.bottom_sheet);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.
                from(constraintLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
        counter = 0;
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new  LinearLayoutManager(this));

        spendViewModel = new ViewModelProvider.AndroidViewModelFactory(
                MainActivity.this.getApplication())
                .create(SpendViewModel.class);
        sharedViewModel = new ViewModelProvider(this)
                .get(SharedViewModel.class);
        spendViewModel.getAllSpends().observe(this, spends ->{
//            for (Spend spend:spends){
//                Log.d("TAG", "onCreate: "+spend.getTarget());
//            }
            recycleViewAdapter = new RecycleViewAdapter(spends,this);
            recyclerView.setAdapter(recycleViewAdapter);

        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            bottomNavigationLayout();
//            Spend spend = new Spend(Calendar.getInstance().getTime(),350,34,45,23,
//                    76,5,5,8,240,
//            207,6);
//            SpendViewModel.insert(spend);

//            Task task = new Task("Task"+ counter++, Priority.MEDIUM, Calendar.getInstance().getTime(),
//                    Calendar.getInstance().getTime(), false);
//            TaskViewModel.insert(task);

        });


    }

    private void bottomNavigationLayout() {
        bottom_navigation_layout.show(getSupportFragmentManager(),bottom_navigation_layout.getTag());
    }

    @Override
    public void onTodoClick(Spend spend) {
        sharedViewModel.selectItem(spend);
        sharedViewModel.setIsEdit(true);
        Log.d("fet", "onTodoClick: "+spend.getFee());
        bottomNavigationLayout();

    }

    @Override
    public void onTodoRadioButtonClick(Spend spend) {
        Log.d("del", "onTodoRadioButtonClick: deleted ");
        SpendViewModel.delete(spend);
        recycleViewAdapter.notifyDataSetChanged();


    }
}
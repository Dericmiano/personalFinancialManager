package com.example.personalfinancialmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.personalfinancialmanager.model.SharedViewModel;
import com.example.personalfinancialmanager.model.Spend;
import com.example.personalfinancialmanager.model.SpendViewModel;
import com.example.personalfinancialmanager.utils.Util;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class Bottom_Navigation_layout extends BottomSheetDialogFragment implements View.OnClickListener {
    private ImageButton calendarButton;
//    private Spend spend;
    private TextView targetTextview;
    private TextView feeTextview;
    private TextView foodTextview;
    private TextView airtimeTextview;
    private TextView offeringTextview;
    private TextView otherBasicTextview;
    private TextView entertainmentTextview;
    private TextView calendarTextview;
    private TextView gfTextView;
    private Button saveButton;
    private Date todayDate;
    Calendar calendar = Calendar.getInstance();
    private SharedViewModel sharedViewModel;
    private Button endDayButton;
    private boolean isEdit;



    public Bottom_Navigation_layout(){

    }



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ){
        View view = inflater.inflate(R.layout.activity_bottom_navigation_layout, container,false);

        feeTextview = view.findViewById(R.id.bot_fee_textentry);
        foodTextview = view.findViewById(R.id.bot_food_textentry);
        airtimeTextview = view.findViewById(R.id.bot_airtime_textentry);
        offeringTextview = view.findViewById(R.id.bot_offering_textentry);
        otherBasicTextview = view.findViewById(R.id.bot_others_textentry);
        entertainmentTextview = view.findViewById(R.id.bot_entertainment_textentry);
        gfTextView = view.findViewById(R.id.bot_gf_textentry);
        saveButton = view.findViewById(R.id.bot_save_button);
        endDayButton = view.findViewById(R.id.bot_end_button);
        targetTextview = view.findViewById(R.id.bot_target_textentry);
        calendarTextview = view.findViewById(R.id.bot_calendar_textview);

        calendarButton= view.findViewById(R.id.bot_calendar_image_button);
        calendarButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sharedViewModel.getSelectedItem().getValue() != null){

            isEdit = sharedViewModel.getIsEdit();
            Spend spend = sharedViewModel.getSelectedItem().getValue();
            String formatted = Util.formatDate(spend.dateToday);
            otherBasicTextview.setText(String.valueOf(spend.getOtherNeed()));
            gfTextView.setText(String.valueOf(spend.getGf()));
            entertainmentTextview.setText(String.valueOf(spend.getEntertainment()));
            offeringTextview.setText(String.valueOf(spend.getCharity()));
            targetTextview.setText(String.valueOf(spend.getTarget()));
            feeTextview.setText(String.valueOf(spend.getFee()));
            foodTextview.setText(String.valueOf(spend.getFood()));
            airtimeTextview.setText(String.valueOf(spend.getAirtime()));
            calendarTextview.setText(formatted);
//            calendarTextview.setText(String.valueOf(spend.dateToday));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity())
                .get(SharedViewModel.class);
        saveButton.setOnClickListener(v -> {
//            String task = enterTodo.getText().toString().trim();
//            int val = Integer.parseInt( num.getText().toString() );


            int target =  Integer.parseInt(targetTextview.getText().toString());
            int food = Integer.parseInt(foodTextview.getText().toString());
            int fee = Integer.parseInt(feeTextview.getText().toString());
            int gFriend = Integer.parseInt(gfTextView.getText().toString());
            int offering = Integer.parseInt(offeringTextview.getText().toString());
            int airtime = Integer.parseInt(airtimeTextview.getText().toString());
            int others = Integer.parseInt(otherBasicTextview.getText().toString());
            int entertainment = Integer.parseInt(entertainmentTextview.getText().toString());

            //helps to test empty inputs
            String foodText= foodTextview.getText().toString();
            String targetText = targetTextview.getText().toString();
            String feeText= feeTextview.getText().toString();
            String gFriendText= gfTextView.getText().toString();
            String offeringText= offeringTextview.getText().toString();
            String airtimeText= airtimeTextview.getText().toString();
            String othersText= otherBasicTextview.getText().toString();
            String entertainmentText= entertainmentTextview.getText().toString();
            int entry = 0;

            if (TextUtils.isEmpty(foodText) || TextUtils.isEmpty(targetText) || TextUtils.isEmpty(feeText)
            || TextUtils.isEmpty(gFriendText) || TextUtils.isEmpty(offeringText) || TextUtils.isEmpty(airtimeText)
            || TextUtils.isEmpty(othersText) || TextUtils.isEmpty(entertainmentText)){
                ///not yet built

            }else if (!TextUtils.isEmpty(foodText) || !TextUtils.isEmpty(targetText) || !TextUtils.isEmpty(feeText)
                    || !TextUtils.isEmpty(gFriendText) || !TextUtils.isEmpty(offeringText) || !TextUtils.isEmpty(airtimeText)
                    || !TextUtils.isEmpty(othersText) || !TextUtils.isEmpty(entertainmentText)){
                Spend mySpend = new Spend(todayDate,target,food,fee,gFriend,offering,
                        entertainment,airtime, others);

                if (isEdit){
                    Spend updateSpend = sharedViewModel.getSelectedItem().getValue();
                    assert updateSpend != null;
                    updateSpend.setDateToday(todayDate);
                    updateSpend.setTarget(target);
                    updateSpend.setFood(food);
                    updateSpend.setFee(fee);
                    updateSpend.setGf(gFriend);
                    updateSpend.setCharity(offering);
                    updateSpend.setEntertainment(entertainment);
                    updateSpend.setAirtime(airtime);
                    updateSpend.setOtherNeed(others);
                    SpendViewModel.update(updateSpend);
                    sharedViewModel.setIsEdit(false);

                }else {
                    SpendViewModel.insert(mySpend);
                }
                feeTextview.setText("");
                foodTextview.setText("");
                airtimeTextview.setText("");
                gfTextView.setText("");
                entertainmentTextview.setText("");
                offeringTextview.setText("");
                otherBasicTextview.setText("");
                targetTextview.setText("");
                calendarTextview.setText("");
                if (this.isVisible()){
                    this.dismiss();
                }

            }
            else {
                Snackbar.make(saveButton, R.string.empty_field, Snackbar.LENGTH_LONG)
                        .show();
            }
//            String ageText = etAge.getText().toString();
//            int age = 0;
//
//            if(! TextUtils.isEmpty(ageText)) // If EditText is not empty
//                age = Integer.parseInt(ageText); // parse its content to integer

// Continue validation...
//            String food1 = foodTextview.getText().toString();
//            int foo = 0;
//
//            if (TextUtils.isEmpty(food1)){
//                foo = Integer.parseInt(food1);
//                foo =0;
//
//            }

        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.bot_calendar_image_button){
            calendar.add(Calendar.DAY_OF_MONTH, 0);
            todayDate = calendar.getTime();
            Log.d("time", "onClick: "+todayDate.toString());
            String formatted = Util.formatDate(todayDate);
            calendarTextview.setText(formatted);



        }

    }
}
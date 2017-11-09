package com.example.tyoung_audet.bitrater;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class SelectionFragment extends Fragment {

    OnGradeSelectedListener callback;
    private int mLastSpinnerPosition = 0;


    public interface OnGradeSelectedListener{
        void onGradeSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //Make sure that the container Activity has implemented
        //the interface. if not, throw an exception so we can fix it
        try{
            callback = (OnGradeSelectedListener) activity;
        }catch(ClassCastException e ){
            throw new ClassCastException(activity.toString() + "must implement OnHeadlineSelectedListener");
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.selection_fragment, container, false);

        String [] values = Content.Ratings;
        Spinner spinner = v.findViewById(R.id.spinnerSelection);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(mLastSpinnerPosition == position){
                    return; //do nothing
                }

                mLastSpinnerPosition = position;
                //do the rest of your code now
                //Notify the parent of the selected item
                callback.onGradeSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}

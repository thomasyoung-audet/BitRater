package com.example.tyoung_audet.bitrater;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class NumberFragment extends Fragment {

    final static String ARG_POSITION = "position";
    private int currentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if(savedInstanceState != null){
            //if we recreated this Fragment (for instance from a screen rotate)
            //restore the previous article selection by getting it here
            currentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        //inflate the view for this fragment
        View myFragmentView = inflater.inflate(R.layout.grade_fragment,container,false);
        return myFragmentView;

    }

    public void updateGradeView(int position){
        Random rand = new Random();
        int  n = rand.nextInt(2);

        View v = getView();
        TextView grade = v.findViewById(R.id.grade);
        String[] data = Content.Ratings;
        grade.setText(data[position]);
        currentPosition = position;
        TextView comment = v.findViewById(R.id.grade_text);
        comment.setText(Content.GradeLines[position][n]);

    }

    @Override
    public void onStart() {
        super.onStart();

        //During startup, we should check if there are arguments (data)
        //passed to this Fragment. We know the layout has already been
        //applied to the Fragment so we can safely call the method that
        //sets the article text

        Bundle args = getArguments();
        if(args != null){
            //set the article based on the argument passed in
            updateGradeView(args.getInt(ARG_POSITION));
        }else if (currentPosition != -1){
            //set the grade based on the saved instance state defined during onCreateView
            updateGradeView(currentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the current selection for later recreation of this Fragment
        outState.putInt(ARG_POSITION, currentPosition);
    }
}

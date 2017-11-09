package com.example.tyoung_audet.bitrater;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends Activity implements SelectionFragment.OnGradeSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if the activity is using the layout version
        //with the FrameLayout.  if so, we have to add the fragment
        //(it wont be done automatically )
        if(findViewById(R.id.container) != null){

            //However if were being restored from a previous state,
            //then dont do anything
            if(savedInstanceState != null){
                return;
            }

            //Crate an instance of the Headline Fragment
            SelectionFragment selectionFragment = new SelectionFragment();

            //In the case this activity was started with special instructions from an Intent,
            //pass the Intent's extras to the fragment as arguments
            selectionFragment.setArguments(getIntent().getExtras());

            //Ask the Fragment manager to add it to the FrameLayout
            getFragmentManager().beginTransaction()
                    .add(R.id.container, selectionFragment)
                    .commit();
        }

    }

    @Override
    public void onGradeSelected(int position) {
        //Capture the article fragment from the activity's dual-pane layout
        NumberFragment numberFragment = (NumberFragment) getFragmentManager().findFragmentById(R.id.grade_fragment);

        //if we dont find one, we must not be in two pane mode
        //lets swap the Fragments instead
        if(numberFragment != null){

            //we must be in two pane layout
            numberFragment.updateGradeView(position);

        }else{
            //we must be in one pane layout

            //Create Fragment and give it an arguement for the selected article right away
            NumberFragment swapFragment = new NumberFragment();
            Bundle args = new Bundle();
            args.putInt(NumberFragment.ARG_POSITION,position);
            swapFragment.setArguments(args);

            //now that the Fragment is prepared, swap it

            getFragmentManager().beginTransaction()
                    .replace(R.id.container, swapFragment)
                    .addToBackStack(null)
                    .commit();


        }
    }
}
package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeplayer=0;

    int[] gamestate= {2,2,2,2,2,2,2,2,2};

    int[] [] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameactive=true;

    public void dropin(View view){

        ImageView counter= (ImageView) view;

        int Tappedcounter = Integer.parseInt(counter.getTag().toString());

       if(gamestate[Tappedcounter]==2 && gameactive) {


           gamestate[Tappedcounter] = activeplayer;

           counter.setTranslationY(-1500);

           if (activeplayer == 0) {

               activeplayer = 1;

               counter.setImageResource(R.drawable.zero);

           } else {

               counter.setImageResource(R.drawable.cros);

               activeplayer = 0;

           }

           counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

           for (int[] winningposition : winningpos) {

               if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                   gameactive= false;
                   String winner = " ";
                   if (activeplayer == 1) {

                       winner = "Zero has won";
                   } else {

                       winner = "X has won";
                   }
                   Toast.makeText(this, winner+" has won", Toast.LENGTH_LONG).show();

                   Button playagainButton=(Button) findViewById(R.id.playagainbutton);

                   TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

                   winnerTextView.setText(winner + " has won");

                   playagainButton.setVisibility(View.VISIBLE);

                   winnerTextView.setVisibility(View.VISIBLE);
               }
           }
       }else{

           Toast.makeText(this,"OOPS!Position is already filled",Toast.LENGTH_LONG).show();
       }



    }
    public void playagain(View view) {

        Button playagainButton = (Button) findViewById(R.id.playagainbutton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playagainButton.setVisibility(View.VISIBLE);

        winnerTextView.setVisibility(View.VISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);


        }
        activeplayer=0;

        for(int i=0;i<gamestate.length;i++){

            gamestate[i]=2;

        }


        gameactive=true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

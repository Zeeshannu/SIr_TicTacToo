package com.example.zeeshan.sir_tictactoo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DbHelper dbHelper;
    public static   int player_1_Score=0;
    public static   int player_2_Score=0;

    int score1=0,score2=0;
    int activePlayer=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2,2};
    int[][] winingPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public ImageView[] mImageView=new ImageView[9];

    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView[0] = findViewById(R.id.imageView0);
        mImageView[1] = (ImageView) findViewById(R.id.imageView1);
        mImageView[2] = (ImageView) findViewById(R.id.imageView2);
        mImageView[3] = (ImageView) findViewById(R.id.imageView3);
        mImageView[4] = (ImageView) findViewById(R.id.imageView4);
        mImageView[5] = (ImageView) findViewById(R.id.imageView5);
        mImageView[6] = (ImageView) findViewById(R.id.imageView6);
        mImageView[7] = (ImageView) findViewById(R.id.imageView7);
        mImageView[8] = (ImageView) findViewById(R.id.imageView8);

        dbHelper=new DbHelper(this);
    }







    public void drop(View view)
    {
        ImageView imageView = (ImageView) view;

        int tappedposition=Integer.parseInt(view.getTag().toString());
        if(gamestate[tappedposition]==2 && !flag) {
            gamestate[tappedposition]=activePlayer;
            imageView.setTranslationY(-1000);
            imageView.animate().translationYBy(1000).setDuration(500);
            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.red);
                activePlayer = 1;
            }
            else if (activePlayer == 1) {
                imageView.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            for(int[]winningPosition:winingPosition)
            {
                if(gamestate[winningPosition[0]]==gamestate[winningPosition[1]]
                        && gamestate[winningPosition[1]]==gamestate[winningPosition[2]]
                        && gamestate[winningPosition[0]]!=2)
                {
                    if(activePlayer==0)
                    {
                        player_1_Score++;
                        customDialog();
                        Toast.makeText(getApplicationContext(),"Yellow Win",Toast.LENGTH_SHORT).show();

                    }
                    if(activePlayer==1)
                    {
                        player_2_Score++;
                        customDialog();
                        Toast.makeText(getApplicationContext(),"Red Win", Toast.LENGTH_SHORT).show();
                    }
                    flag=true;
                }
            }
        }
    }



    public void setDrawableNull(){
        for(int i=0;i<9;i++){
            gamestate[i]=2;
            mImageView[i].setImageDrawable(null);
            //drawable[i]=i;
        }
        flag=false;
    }

    public void customDialog(){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Result !!!");
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Player 1 Score :"+player_1_Score+"\n"+"Player 2 " +
                "Score : "+player_2_Score+"\n"+"Do You " +
                "want " +
                "to" +
                " Try Again");

        //This component has boolean value i.e true/false.
        // If set to false it allows to cancel the dialog box by clicking on area outside the dialog else it allows.
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setDrawableNull();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"You clicked over No",Toast.LENGTH_SHORT).show();
            }
        });
//        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(),"You clicked on Cancel",Toast.LENGTH_SHORT).show();
//            }
//        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void scoreFun(View view) {




    Intent intent=new Intent(getApplicationContext(),Result.class);

    intent.putExtra("player_1_Score",player_1_Score);
    intent.putExtra("player_2_Score",player_2_Score);

    startActivity(intent);


 }

    @Override
    protected void onPause() {
        super.onPause();


        dbHelper=new DbHelper(this);

        try{
            Cursor cursor=dbHelper.lisetRecord();
            if(cursor.moveToFirst()){

                score1= Integer.valueOf(cursor.getString(cursor.getColumnIndex("player1")));
                score2= Integer.valueOf(cursor.getString(cursor.getColumnIndex("player2")));


            }
        }catch (Exception e){

        }

        player_1_Score=player_1_Score+score1;
        player_2_Score=player_2_Score+score2;

        //inserts score into database
        Integer value =dbHelper.insertScore(player_1_Score,player_2_Score);
        if(value>0)
            Toast.makeText(this, "Successfully inserted", Toast.LENGTH_SHORT).show();

        // Intent intent=new Intent(getApplicationContext(),Result.class);
        // /startActivity(intent);

    }

    public void resetFun(View view) {
        setDrawableNull();
    }
}

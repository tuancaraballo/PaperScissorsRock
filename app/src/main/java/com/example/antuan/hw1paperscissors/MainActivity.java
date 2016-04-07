package com.example.antuan.hw1paperscissors;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends ActionBarActivity {
    private int yScore = 0;
    private int cScore =0;
    private int computer_choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView human_score = (TextView) findViewById(R.id.your_score);
        TextView the_Cscore =  (TextView) findViewById(R.id.computer_score);
        human_score.setText("Your Score: "+ yScore);
        the_Cscore.setText("Computer Score: " + cScore);
        computer_choice = pickRandomChoice();
    }

    public int pickRandomChoice() {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public void freezeAllButtons(){
        ImageButton pap = (ImageButton) findViewById(R.id.paperButton);
        pap.setEnabled(false);
        ImageButton sis = (ImageButton) findViewById(R.id.scissorsButton);
        sis.setEnabled(false);
        ImageButton rok = (ImageButton) findViewById(R.id.rockButton);
        rok.setEnabled(false);
    }

    public void unfreezeAllButtons(){
        ImageButton pap = (ImageButton) findViewById(R.id.paperButton);
        pap.setEnabled(true);
        ImageButton sis = (ImageButton) findViewById(R.id.scissorsButton);
        sis.setEnabled(true);
        ImageButton rok = (ImageButton) findViewById(R.id.rockButton);
        rok.setEnabled(true);
    }

    public void checkFinish(int youScore, int compScore){
        if(compScore == 5){
            Toast toast = Toast.makeText(this, "You suck at this game homie! Click Reset to start the game", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 400);
            toast.show();
            freezeAllButtons();
        } else if (youScore == 5){
            Toast toast = Toast.makeText(this, "You rock! Click Reset to restart the game", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 400);
            toast.show();
            freezeAllButtons();
        }
    }

    int checkWin(int user_choice, int computer_choice ) {
        if (user_choice == computer_choice) return 0; // there is a tie
        if (user_choice == 0) {  // user chose PAPER
            if (computer_choice == 1) return -1; // computer selected scissors, user loses
            if (computer_choice == 2) return 1; // computer selected rock, user wins
        } else if (user_choice == 1) { // user chose SCISSORS
            if (computer_choice == 0) return 1; // computer selected paper, user wins
            if (computer_choice == 2) return -1; // computer selected rock, user loses
        }
        // the user chose ROCK
        if (computer_choice == 0) return -1; // computer selected paper, user loses
        // computer selected scissors, the user wins
        return 1;
    }

    public String answer(int choice){
        if(choice == 0){
            return "paper";
        } else if (choice == 1){
            return "scissors";
        }
        return "rock";
    }

    public void playGame(int user_score){
        int result = checkWin(user_score,computer_choice);
        Toast toast = Toast.makeText(this, "The computer selected " + answer(computer_choice), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 400);
        toast.show();

        if(result == -1){ // computer won, update score
            cScore ++;
            TextView the_Cscore =  (TextView) findViewById(R.id.computer_score);
            the_Cscore.setText("Computer Score: " + cScore);
            Toast toast1 = Toast.makeText(this, "You lost this time!",Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 400);
            toast1.show();
        }else if(result == 1){
            yScore++;
            TextView the_Yscore =  (TextView) findViewById(R.id.your_score);
            the_Yscore.setText("Your Score: " + yScore);
            Toast toast2 =  Toast.makeText(this, "You won this time!", Toast.LENGTH_SHORT);
            toast2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 400);
            toast2.show();
        }else{ // result is equal to zero so it's tied
            Toast toast3 =  Toast.makeText(this, "It's tied! ", Toast.LENGTH_SHORT);
            toast3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 400);
            toast3.show();
        }
        checkFinish(yScore, cScore);

    }
    public void paperClicked(View view) {
        playGame(0);
        computer_choice = pickRandomChoice();
    }

    public void scissorsClicked(View view) {
        playGame(1);
        computer_choice = pickRandomChoice();
    }

    public void rockClicked(View view) {
        playGame(2);
        computer_choice = pickRandomChoice();
    }

    public void resetGame(View view) {
        unfreezeAllButtons();
        yScore = 0;
        cScore = 0;
        TextView human_score = (TextView) findViewById(R.id.your_score);
        TextView the_Cscore =  (TextView) findViewById(R.id.computer_score);
        human_score.setText("Your Score: "+ yScore);
        the_Cscore.setText("Computer Score: " + cScore);
        Toast.makeText(this, "You start a new game!", Toast.LENGTH_SHORT);
    }
}
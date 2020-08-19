package myapp.fach.a11_connect3game;

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

    // 0:yellow, 1:red

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {0, 4, 8}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn (View view){

        ImageView muncul = (ImageView) view;

        //Log.i("Tag", muncul.getTag().toString());

        int tappedCounter = Integer.parseInt(muncul.getTag().toString()); //posisi yang dipencet

        //biar kolom yang udah keisi ga bisa diganti warna lagi
        if(gameState[tappedCounter] == 2 && gameActive){

            gameState[tappedCounter] = activePlayer;

            muncul.setTranslationY(-1500);

            if (activePlayer == 0) {

                muncul.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            }else {

                muncul.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            muncul.animate().translationYBy(1500).rotation(3600).setDuration(1000);

            for(int[] winningPositions : winningPositions) {

                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {

                    //someone has won

                    gameActive = false; //gamenya di non-aktifin karena udah dapet pemenangnya

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Yellow";

                    } else {

                        winner = "Red";

                    }

                    //Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();

                    Button playAgainButton = findViewById(R.id.playAgainButton);

                    TextView textView = findViewById(R.id.winnerTextView);

                    textView.setText(winner + " has won!"); //ngeset tulisan

                    playAgainButton.setVisibility(View.VISIBLE);

                    textView.setVisibility(View.VISIBLE);

                }
            }

        }

    }

    public void playAgain (View view){
        Button playAgainButton = findViewById(R.id.playAgainButton);

        TextView textView = findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        //android loop through all objects in layout

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView ilang = (ImageView) gridLayout.getChildAt(i);

            // android java remove imageview source
            ilang.setImageDrawable(null);
        }

        //cuma diupdate jadi gausah pake tipe data
        for(int i = 0; i < gameState.length; i++){

            gameState[i] = 2;
        }

        activePlayer = 0;

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

package com.example.tictactoe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFrag extends Fragment {

    ImageView tile00, tile01, tile02, tile10, tile11, tile12, tile20, tile21, tile22;
    TextView tvHeading;
    final static int[][] winnings = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    char[] status = {'n','n','n','n','n','n','n','n','n'};
    int chance = 0;
    boolean gameActive = true;
    boolean filled = false;
    Bundle bundle;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tile00 = (ImageView) getActivity().findViewById(R.id.tile00);
        tile01 = (ImageView) getActivity().findViewById(R.id.tile01);
        tile02 = (ImageView) getActivity().findViewById(R.id.tile02);
        tile10 = (ImageView) getActivity().findViewById(R.id.tile10);
        tile11 = (ImageView) getActivity().findViewById(R.id.tile11);
        tile12 = (ImageView) getActivity().findViewById(R.id.tile12);
        tile20 = (ImageView) getActivity().findViewById(R.id.tile20);
        tile21 = (ImageView) getActivity().findViewById(R.id.tile21);
        tile22 = (ImageView) getActivity().findViewById(R.id.tile22);

        tvHeading = (TextView) getActivity().findViewById(R.id.tvHeading);

        tile00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 0);
            }
        });

        tile01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 1);
            }
        });

        tile02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 2);
            }
        });

        tile10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 3);
            }
        });

        tile11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 4);
            }
        });

        tile12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 5);
            }
        });

        tile20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 6);
            }
        });

        tile21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 7);
            }
        });

        tile22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(v, 8);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity.turn = 0;

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    public void clicked(View view, int position)
    {
        MainActivity.turn = 0;

        if (filled)
        {
            FragmentManager manager = getFragmentManager();

            manager.beginTransaction()
                    .replace(R.id.fragHolder, new GameFrag())
                    .commit();

        }
        if(gameActive) {

            if (status[position] == 'n') {
                ((ImageView) view).setTranslationY(-500f);

                if (chance == 0) {
                    status[position] = 'x';
                    ((ImageView) view).setImageResource(R.drawable.x);

                    if (check()) {

                        Toast.makeText(getActivity(), "            " + MainActivity.playerX +
                                " wins\nPress on any tile to play again", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    status[position] = 'o';
                    ((ImageView) view).setImageResource(R.drawable.o);

                    if (check()) {
                        Toast.makeText(getActivity(), "            " + MainActivity.playerO +
                                " wins \nPress on any tile to play again", Toast.LENGTH_LONG).show();
                    }
                }

                ((ImageView) view).animate().translationYBy(500f);
                chance = ++chance % 2;
            }
        }
        else {

            FragmentManager manager = getFragmentManager();

            manager.beginTransaction()
                    .replace(R.id.fragHolder, new LoginFrag())
                    .commit();

            tvHeading.setText(R.string.welcome_to_tic_tac_toe);

        }

    }

    public boolean check()
    {
        for(int[] combination : winnings)
        {
            if(status[combination[0]] != 'n' && status[combination[0]] == status[combination[1]] && status[combination[1]] == status[combination[2]])
            {
                gameActive = false;
                return true;
            }
        }

        for(int i = 0 ; i <= 8 ; i++)
        {
            if(status[i] == 'n')
            {
                return false;
            }
        }

        filled = true;
        Toast.makeText(getActivity(), "       Ohh that's a tie!\nPress any tile to restart", Toast.LENGTH_LONG).show();

        return false;
    }

}
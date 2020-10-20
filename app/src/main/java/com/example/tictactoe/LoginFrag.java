package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFrag extends Fragment {

    Button btnGameOn;
    EditText etPlayerX, etPlayerO;
    TextView tvHeading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initialization();
        etPlayerX = (EditText) this.getActivity().findViewById(R.id.etPlayerX);
        tvHeading = (TextView) this.getActivity().findViewById(R.id.tvHeading);

        btnGameOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String playerX = etPlayerX.getText().toString().trim();
                String playerO = etPlayerO.getText().toString().trim();

                if(playerX.isEmpty() || playerO.isEmpty())
                {
                    Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                }
                else {

                    MainActivity.playerO = playerO;
                    MainActivity.playerX = playerX;

                    FragmentManager manager = getFragmentManager();

                    manager.beginTransaction()
                            .replace(R.id.fragHolder, new GameFrag())
                            .commit();

                    tvHeading.setText("Let's Play");
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    public void initialization(){
        btnGameOn = (Button) this.getActivity().findViewById(R.id.btnGameOn);
         etPlayerO = (EditText) this.getActivity().findViewById(R.id.etPlayerO);
    }     
}

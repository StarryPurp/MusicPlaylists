package bstar128.example.hansung.musicplaylists;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView listV;
    Button butPlay,butStop;
    TextView textMusic;
    ProgressBar progress;
    String[] musicArray={"movie","sometime","secret"};
    int[] musiResid={R.raw.movie,R.raw.sometime,R.raw.secret};
    int selectedMusicid;
    MediaPlayer Mplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listV=(ListView)findViewById(R.id.list_music);
        butPlay=(Button)findViewById(R.id.but_play);
        butStop=(Button)findViewById(R.id.but_stop);
        textMusic=(TextView)findViewById(R.id.text_music);
        progress=(ProgressBar)findViewById(R.id.prograss_ing);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,musicArray);
        listV.setAdapter(adapter);
        listV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listV.setItemChecked(0,true);
        selectedMusicid=musiResid[0];
        Mplayer=MediaPlayer.create(this,selectedMusicid);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMusicid=musiResid[position];

            }
        });
        butPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer.create(MainActivity.this,selectedMusicid);
                Mplayer.start();
            }
        });
        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mplayer.stop();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Mplayer.stop();
    }
}

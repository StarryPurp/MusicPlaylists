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
    Button butPlay,butStop,butPause;
    TextView textMusic;
    ProgressBar progress;
    String[] musicArray={"movie","sometime","secret"};
    int[] musiResid={R.raw.movie,R.raw.sometime,R.raw.secret};
    int selectedMusicid;
    MediaPlayer Mplayer;
    boolean selectPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listV=(ListView)findViewById(R.id.list_music);
        butPlay=(Button)findViewById(R.id.but_play);
        butStop=(Button)findViewById(R.id.but_stop);
        butPause=(Button)findViewById(R.id.but_pause);
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
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Mplayer.stop();
                selectedMusicid=musiResid[i];
                progress.setVisibility(View.INVISIBLE);
            }
        });
        butPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectPause) {
                    Mplayer.start();
                    selectPause=false;//시작하고 ㄱㄱ~
                }
                else
                    Mplayer=MediaPlayer.create(MainActivity.this,selectedMusicid);
                Mplayer.start();
                progress.setVisibility(View.VISIBLE);
            }
        });
        butPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPause=true;
                Mplayer.pause();
                progress.setVisibility(View.INVISIBLE);
            }
        });
        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mplayer.stop();
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Mplayer.stop();
    }
}

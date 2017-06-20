package bstar128.example.hansung.musicplaylists;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    ListView listV;
    Button butPlay,butStop,butPause;
    TextView textMusic,textTime;
    SeekBar progress;
    String[] musicArray={"movie","sometime","secret"};
    int[] musiResid={R.raw.movie,R.raw.sometime,R.raw.secret};
    int selectedMusicid;
    MediaPlayer Mplayer;
    boolean selectPause;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listV=(ListView)findViewById(R.id.list_music);
        butPlay=(Button)findViewById(R.id.but_play);
        butStop=(Button)findViewById(R.id.but_stop);
        butPause=(Button)findViewById(R.id.but_pause);
        textMusic=(TextView)findViewById(R.id.text_music);
        textTime=(TextView)findViewById(R.id.text_time);
        progress=(SeekBar) findViewById(R.id.prograss_ing);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,musicArray);
        listV.setAdapter(adapter);
        listV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listV.setItemChecked(0,true);
        selectedMusicid=musiResid[0];
        Mplayer=MediaPlayer.create(this,selectedMusicid);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                selectPause=false;
                Mplayer.stop();
                selectedMusicid=musiResid[i];
                MainActivity.this.i=i;
            }
        });
        butPlay.setOnClickListener(new View.OnClickListener() {
            SimpleDateFormat timeFormat=new SimpleDateFormat("mm:ss");
            @Override
            public void onClick(View v) {
                textMusic.setText(musicArray[i]);
                if(selectPause) {
                    selectPause=false;//시작하고 ㄱㄱ~
                }
                else
                    Mplayer=MediaPlayer.create(MainActivity.this,selectedMusicid);
                Mplayer.start();
                Thread musicthread=new Thread(){
                    @Override
                    public void run(){
                        if(Mplayer==null) return;
                        progress.setMax(Mplayer.getDuration());//전체 음악 재생길이
                        while(Mplayer.isPlaying()){
                            progress.setProgress(Mplayer.getCurrentPosition());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textTime.setText("진행시간: "+timeFormat.format(Mplayer.getCurrentPosition()));//시간보여주기
                                }
                            });
                            SystemClock.sleep(200);
                        }
                    }
                };
                musicthread.start();
            }
        });
        butPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPause=true;
                Mplayer.pause();
            }
        });
        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPause=false;
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

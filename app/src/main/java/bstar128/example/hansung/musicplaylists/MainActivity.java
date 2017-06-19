package bstar128.example.hansung.musicplaylists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView listV;
    Button butPaly,butStop;
    TextView textMusic;
    ProgressBar progress;
    String[] musicArray={"movie","sometime","secret"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listV=(ListView)findViewById(R.id.list_music);
        butPaly=(Button)findViewById(R.id.but_play);
        butStop=(Button)findViewById(R.id.but_stop);
        textMusic=(TextView)findViewById(R.id.text_music);
        progress=(ProgressBar)findViewById(R.id.prograss_ing);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,musicArray);
        listV.setAdapter(adapter);
        listV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listV.setItemChecked(0,true);
    }
}

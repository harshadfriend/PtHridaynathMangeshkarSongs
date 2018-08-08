package karjatonline.hridaynath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import com.facebook.ads.*;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    private AdView adView;
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    // YouTube player view
    private YouTubePlayerView youTubeView;

    ListView playlist;
    ArrayAdapter<String> adp;
    String[] strPlaylist={"Ti geli tevha","Tya phulanchya gandhkoshi","Hridaynath Mangeshkar sings Jivalaga and other songs",
            "Malvun Taak Dip","Shoor Aamhi Sardar","Maze Raani-Mahananda"};
    YouTubePlayer yp;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ll=findViewById(R.id.ll);
        adView = new AdView(this, "YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
//        adView = new AdView(this, "874852772698575_874852956031890", AdSize.BANNER_HEIGHT_50);


        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        playlist=findViewById(R.id.playlist);


        ll.addView(adView);
        adView.loadAd();
        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strPlaylist);
        playlist.setAdapter(adp);

        playlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:Config.YOUTUBE_VIDEO_CODE="pM3eOOP786w";
                        yp.loadVideo("pM3eOOP786w");
                        break;
                    case 1:Config.YOUTUBE_VIDEO_CODE="R-exzGL7ouQ";
                        yp.loadVideo("R-exzGL7ouQ");
                        break;
                    case 2: yp.loadVideo("rKmFFd2txAc");
                        break;
                    case 3: yp.loadVideo("8BvLj2KYvCg");
                        break;
                    case 4: yp.loadVideo("k2JRdHKS-Zw");
                        break;
                    case 5: yp.loadVideo("LJa0u9tGc3o");
                        break;
                }
            }
        });

        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        yp=player;
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
//            player.loadVideo(Config.YOUTUBE_VIDEO_CODE);
            player.cueVideo(Config.YOUTUBE_VIDEO_CODE);
            // Hiding player controls
//            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

}

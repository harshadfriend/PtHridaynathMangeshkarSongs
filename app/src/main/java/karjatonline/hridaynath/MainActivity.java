package karjatonline.hridaynath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

public class MainActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    // YouTube player view
    private YouTubePlayerView youTubeView;

    ListView playlist;
    ArrayAdapter<String> adp;
    String[] strPlaylist={"Ti geli tevha","Tya phulanchya gandhkoshi"};
    YouTubePlayer yp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        yp=new YouTubePlayer() {
            @Override
            public void release() {

            }

            @Override
            public void cueVideo(String s) {

            }

            @Override
            public void cueVideo(String s, int i) {

            }

            @Override
            public void loadVideo(String s) {

            }

            @Override
            public void loadVideo(String s, int i) {

            }

            @Override
            public void cuePlaylist(String s) {

            }

            @Override
            public void cuePlaylist(String s, int i, int i1) {

            }

            @Override
            public void loadPlaylist(String s) {

            }

            @Override
            public void loadPlaylist(String s, int i, int i1) {

            }

            @Override
            public void cueVideos(List<String> list) {

            }

            @Override
            public void cueVideos(List<String> list, int i, int i1) {

            }

            @Override
            public void loadVideos(List<String> list) {

            }

            @Override
            public void loadVideos(List<String> list, int i, int i1) {

            }

            @Override
            public void play() {

            }

            @Override
            public void pause() {

            }

            @Override
            public boolean isPlaying() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public void next() {

            }

            @Override
            public void previous() {

            }

            @Override
            public int getCurrentTimeMillis() {
                return 0;
            }

            @Override
            public int getDurationMillis() {
                return 0;
            }

            @Override
            public void seekToMillis(int i) {

            }

            @Override
            public void seekRelativeMillis(int i) {

            }

            @Override
            public void setFullscreen(boolean b) {

            }

            @Override
            public void setOnFullscreenListener(OnFullscreenListener onFullscreenListener) {

            }

            @Override
            public void setFullscreenControlFlags(int i) {

            }

            @Override
            public int getFullscreenControlFlags() {
                return 0;
            }

            @Override
            public void addFullscreenControlFlag(int i) {

            }

            @Override
            public void setPlayerStyle(PlayerStyle playerStyle) {

            }

            @Override
            public void setShowFullscreenButton(boolean b) {

            }

            @Override
            public void setManageAudioFocus(boolean b) {

            }

            @Override
            public void setPlaylistEventListener(PlaylistEventListener playlistEventListener) {

            }

            @Override
            public void setPlayerStateChangeListener(PlayerStateChangeListener playerStateChangeListener) {

            }

            @Override
            public void setPlaybackEventListener(PlaybackEventListener playbackEventListener) {

            }
        };

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        playlist=findViewById(R.id.playlist);

        adp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strPlaylist);
        playlist.setAdapter(adp);

        playlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:Config.YOUTUBE_VIDEO_CODE="pM3eOOP786w";

                        break;
                    case 1:Config.YOUTUBE_VIDEO_CODE="R-exzGL7ouQ";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

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

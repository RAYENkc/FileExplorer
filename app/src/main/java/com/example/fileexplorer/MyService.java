package com.example.fileexplorer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service
{   private MediaPlayer mediaPlayer;
    String link;

    private IBinder mBinder = new MyBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
/*
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);*/
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }

            mediaPlayer.release();
        }
    }

  /*
  @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        switch (i){
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Toast.makeText(this,
                        "MEDIA_ERROR_NOT_VALID_FOR",
                        Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Toast.makeText(this,"MEDIA_ERROR_SEROVEr",
                        Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Toast.makeText(this,"MEDIA_ERROR_UNKNOW",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
*/

    public class MyBinder extends Binder {

         MyService getService(){
             return MyService.this;
         }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        link = intent.getStringExtra("DeletFile");
        mediaPlayer.reset();


        if(!mediaPlayer.isPlaying()){
            try{
                 mediaPlayer.setDataSource(link);
                 mediaPlayer.prepareAsync();

            }catch (Exception e){

                Toast.makeText(this,"Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


        return START_STICKY;
    }

}
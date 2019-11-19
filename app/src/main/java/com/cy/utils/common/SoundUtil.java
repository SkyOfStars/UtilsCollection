package com.cy.utils.common;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * cy
 */

public class SoundUtil {

    private static SoundUtil util;

    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    private final Object poolLock = new Object();
    private final Object mediaLock = new Object();
    private AtomicBoolean isPlaying = new AtomicBoolean(false);

    private HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();

    private SoundUtil() {

    }

    public static SoundUtil get() {
        if (util == null) {
            synchronized (SoundUtil.class) {
                if (util == null) {
                    util = new SoundUtil();
                }
            }
        }
        return util;
    }

    /**
     * 加载提示音
     *
     * @param context
     * @param rowId
     * @return
     */
    public int loadHint(Context context, int rowId) {
        int id = -1;
        SoundPool playPool = createSoundPool();
        if (playPool != null) {
            synchronized (poolLock) {
                if (!soundID.containsKey(rowId)) {
                    id = playPool.load(context, rowId, 0);
                    soundID.put(rowId, id);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        LogUtil.i("loadHint error: "+e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    id = soundID.get(rowId);
                }
            }
        }
        return id;
    }

    /**
     * 播放提示音
     *
     * @param context
     * @param rowId
     * @return soundId
     */
    public int playHint(Context context, int rowId) {
        int id = loadHint(context, rowId);
        if (id >= 0) {
            SoundPool playPool = createSoundPool();
            if (playPool != null) {
                synchronized (poolLock) {
                    playPool.play(id, 1, 1, 0, 0, 1);
                }
            }
        }
        return id;
    }

    public void unloadHint(int rowId) {
        SoundPool playPool = createSoundPool();
        if (playPool != null) {
            synchronized (poolLock) {
                if (soundID.containsKey(rowId)) {
                    playPool.unload(soundID.get(rowId));
                    soundID.remove(rowId);
                }
            }
        }
    }

    /**
     * 播放背景音乐
     *
     * @param afd
     * @param isLoop
     */
    public void playBgm(AssetFileDescriptor afd, boolean isLoop) {
        MediaPlayer player = createMediaPlayer();
        synchronized (mediaLock) {
            if (player != null) {
                try {
                    if (player.isPlaying()) {
                        player.stop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                player.reset();
                try {
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.setLooping(isLoop);
                    player.setOnPreparedListener(preparedListener);
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.prepareAsync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 停止播放背景音乐
     */
    public void stopPlayBgm() {
        MediaPlayer player = createMediaPlayer();
        if (player != null) {
            synchronized (mediaLock) {
                try {
                    player.stop();
                    player.reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private SoundPool createSoundPool() {
        if (soundPool == null) {
            synchronized (poolLock) {
                if (soundPool == null) {
                    //当前系统的SDK版本大于等于21(Android 5.0)时
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        SoundPool.Builder builder = new SoundPool.Builder();
                        //传入音频数量
                        builder.setMaxStreams(2);
                        //AudioAttributes是一个封装音频各种属性的方法
                        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
                        //设置音频流的合适的属性
                        attrBuilder.setLegacyStreamType(AudioManager.STREAM_ALARM);
                        //加载一个AudioAttributes
                        builder.setAudioAttributes(attrBuilder.build());
                        soundPool = builder.build();
                    }
                    //当系统的SDK版本小于21时
                    else {//设置最多可容纳2个音频流，音频的品质为5
                        soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
                    }
                }
            }
        }
        return soundPool;
    }

    private MediaPlayer createMediaPlayer() {
        if (mediaPlayer == null) {
            synchronized (mediaLock) {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
            }
        }
        return mediaPlayer;
    }

    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            try {
                mp.start();
                mp.setVolume(0.6f, 0.6f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}

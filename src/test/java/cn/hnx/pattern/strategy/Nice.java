package cn.hnx.pattern.strategy;

/**
 * Created by viruser on 2019/9/23.
 */
public class Nice {

    private DownloadMusic downloadMusic;

    public Nice(DownloadMusic downloadMusic) {
        this.downloadMusic = downloadMusic;
    }

    public void listenSongs(){
        this.downloadMusic.download();
    }
}

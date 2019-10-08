package cn.hnx.pattern.strategy;

/**
 * Created by viruser on 2019/9/23.
 */
public class NeteaseCloudMusic implements DownloadMusic {
    @Override
    public void download() {
        System.out.println("网易云音乐下载");
    }
}

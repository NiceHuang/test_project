package cn.hnx.pattern.strategy;

/**
 * Created by viruser on 2019/9/23.
 */
public class KuGouMusic implements DownloadMusic {
    @Override
    public void download() {
        System.out.println("酷狗下载");
    }
}

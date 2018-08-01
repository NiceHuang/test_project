package cn.hnx.common.utils;

/**
 * Created by hnx on 2018/7/31.
 */
public enum UserStatus {
    APPLY(1), WAITING(2), PUBLISHING(3), ONLINE(4);

    private int status;

    UserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static UserStatus getUserStatus(int statusNum){
        switch (statusNum) {
            case 1:
                return APPLY;
            case 2:
                return WAITING;
            case 3:
                return PUBLISHING;
            case 4:
                return ONLINE;
            default:
                return APPLY;
        }
    }
}

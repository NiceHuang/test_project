package cn.hnx.service;

import cn.hnx.common.bean.DataPortralUser;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/6.
 */
public interface DataPortralUserService {

    List<DataPortralUser> fetchUser(Map<String, Object> params);

    DataPortralUser fetchUserByEmail(String email);

    void addDataPortralUser(DataPortralUser dataPortralUser);
}

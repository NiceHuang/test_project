package cn.hnx.service.impl;

import cn.hnx.common.bean.DataPortralUser;
import cn.hnx.dao.DataPortralUserDao;
import cn.hnx.service.DataPortralUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/6.
 */

@Service
public class DataPortralUserServiceImpl implements DataPortralUserService {
    @Autowired
    private DataPortralUserDao dataPortralUserDao;

    @Override
    public List<DataPortralUser> fetchUser(Map<String, Object> params) {
        return dataPortralUserDao.fetchUser(params);
    }

    @Override
    public DataPortralUser fetchUserByEmail(String email) {
        return dataPortralUserDao.fetchUserByEmail(email);
    }

    @Override
    public void addDataPortralUser(DataPortralUser dataPortralUser) {
        dataPortralUserDao.addDataPortralUser(dataPortralUser);
    }
}

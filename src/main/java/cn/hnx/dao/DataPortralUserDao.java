package cn.hnx.dao;

import cn.hnx.common.bean.DataPortralUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/6.
 */

@Mapper
public interface DataPortralUserDao {

    @Select({"<script>","SELECT * FROM `user` where 1=1 <if test='status != null'> and `status` = #{status}</if>","</script>"})
    List<DataPortralUser> fetchUser(Map<String, Object> params);

    @Select("SELECT * FROM `data_portral_user` where `email`=#{email}")
    DataPortralUser fetchUserByEmail(String email);

    @Insert("INSERT INTO `data_portral_user`(`username`, `email`, `password`, `status`) VALUES(#{username}, #{email}, #{password}, #{status});")
    void addDataPortralUser(DataPortralUser dataPortralUser);
}

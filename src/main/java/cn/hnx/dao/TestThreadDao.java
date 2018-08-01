package cn.hnx.dao;

import cn.hnx.common.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by hnx on 2018/7/30.
 */

@Mapper
public interface TestThreadDao {

    @Select({"<script>","SELECT * FROM `user` where 1=1 <if test='status != null'> and `status` = #{status}</if>","</script>"})
    List<User> fetchUser(Map<String, Object> params);


    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User fetchUserById(Map<String, Object> params);


    @Insert("INSERT INTO `user`(`name`, `status`) " +
            "VALUES(#{name},#{status})")
    void addUser(User user);

    @Update("UPDATE `user` SET `name` = #{name}, birthday = #{birthday}, " +
            "`status` = #{status} WHERE id = #{id}")
    void updateUser(User user);

}

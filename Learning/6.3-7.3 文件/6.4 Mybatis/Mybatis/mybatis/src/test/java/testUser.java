import cn.hyx.entity.User;
import cn.hyx.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis - testUser
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
public class testUser {
    public static void main(String[] args) throws IOException {
        //加载核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql语句
        //获取UserMapper接口的代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        java.text.SimpleDateFormat sf=new java.text.SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<users.size();i++){
            User user=users.get(i);
            System.out.println(user.getUsername()+" "+user.getSex()+" "+sf.format(user.getBirthday()));
        }
        //System.out.println(users);
        //释放资源
        sqlSession.close();
    }

}

package utils;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.face.UserSql;

public class GetSqlResultUtils {
	private String res="configuration/mybatis_conf.xml";
	/**
	 * 获取UseSql工具，用来调用UserSql中的函数
	 * @return
	 * @throws Exception
	 */
	public UserSql getUserSql() throws Exception{
		SqlSessionFactory fac=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(res));
		fac.getConfiguration().addMapper(UserSql.class);
		SqlSession session=fac.openSession(true);
		UserSql userSql=session.getMapper(UserSql.class);
		return userSql;
	}
}

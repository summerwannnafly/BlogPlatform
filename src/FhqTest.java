import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import service.face.UserOp;
import service.impl.UserOpImpl;
import dao.face.UserSql;
import domain.User;

/**
 * 临时Test类用于提供简单的单元测试功能
 * 可以运行一个小模块的代码判断正确性
 * @author 胡炜财
 *
 */
public class FhqTest {

	public static void main(String[] args) throws Exception{
		String res="configuration/mybatis_conf.xml";
		SqlSessionFactory fac=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(res));
		fac.getConfiguration().addMapper(UserSql.class);
		
		SqlSession session=fac.openSession();
		UserSql uo=session.getMapper(UserSql.class);
		//User a=uo.getNumOfIDAndPas("1111","111");
		int a=uo.getNumByUsernameAndEmail("1111","tom@qq.com");
		System.out.println(a);
		 
		UserOp userOp=new UserOpImpl();
		//User user2=userOp.canLogin("1111", "111");
		//System.out.println(user2.getBirth_day());
		boolean isUserNameExist=userOp.isUserIDAvailable("1111");
		boolean isUserEmailExist=userOp.isUserEmailAvailable("dasdasd");
		System.out.println(isUserNameExist);
		System.out.println(isUserEmailExist);
	}

}

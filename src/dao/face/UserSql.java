package dao.face;

import org.apache.ibatis.annotations.*;

import domain.User;

public interface UserSql {
	/**
	 * 获取表中email对应的行数
	 * @param email
	 * @return User类
	 */
	@Select("select count(*) from user where email=#{email}")
	public int getNumByEmail(String email);
	/**
	 * 通过ID获取用户信息
	 * @param userID
	 * @return User类
	 */
	@Select("select * from user where userID=#{userID}")
	public User getUserByID(String userID);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	@Select("select * from user where email=#{eamil}")
	public User getUserByEmail(@Param("eamil")String email);
	/**
	 * 获取表中与用户ID对应的行数
	 * @param username
	 * @return 行数
	 */
	@Select("select count(*) from user where userID=#{userID}")
	public int getNumOfID(String username);
	
	/**
	 * 获取表中与指定用户名及密码对应行的数量
	 * @param userID336
	 * @param password
	 * @return 如果登录成功则返回用户实体，否则为空
	 */
	@Select("select * from user where userID=#{userID} and password=#{password}")
	public User getNumOfIDAndPas(@Param("userID")String userID,@Param("password") String password);
	
	/**
	 * 往user表添加一行
	 * @param user
	 */
	@Insert("Insert into user(userID,username,email,password,sex,birth_year,birth_month,birth_day,self_introduce,isAdmin,color)"
			+ "values(#{userID},#{username},#{email},#{password},#{sex},#{birth_year},#{birth_month},#{birth_day},#{self_introduce},#{isAdmin},#{color}) ")
	public void insertUser(User user);
	
	/**
	 * 修改User
	 * @param user
	 */
	@Update("update user set username=#{username},"
			+ "email=#{email},"
			+ "password=#{password},"
			+ "sex=#{sex},"
			+ "birthday=#{birthday},"
			+ "self_introduce=#{self_introduce},"
			+ "isAdmin=#{isAdmin},"
			+ "color=#{color}"
			+ " where userID=#{userID}")
	public void updateUser(User user);
	/**
	 * 通过用户名和邮箱判断用户是否存在
	 * @param eamil
	 * @param eamil2 
	 * @return
	 * @Select("select * from user where userID=#{userID} and password=#{password}")
	public User getNumOfIDAndPas(@Param("userID")String userID,@Param("password") String password);
	 */
	@Select("select count(*) from user where userID=#{userID} and email=#{email}")
	public int getNumByUsernameAndEmail(@Param("userID")String userID,@Param("email") String email);
}

package controller;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.User;
import service.face.UserOp;
import utils.Const;

@Controller
public class UserController {
	// 换行符号
	public String enter = System.getProperty("line.separator");
	// 用线程安全的HashMap保存发送给邮箱的随机验证码
	public static Map<String, Integer> randomKeys = Collections.synchronizedMap(new HashMap<String, Integer>());
	// 邮件内容
	String content = "LONERS 收到了您的重置密码请求。" + enter + "如果要重置密码，请单击下面的链接，或者将其复制并粘贴到浏览器中：\n" + enter
			+ "如果不想重置密码，请忽略此邮件，您的密码不会改变。如有任何疑问或顾虑，请联系 LONERS团队 获取支持。" + enter + utils.Const.domain
			+ "/resetpassword?key=";

	// 给指定邮箱发送验证码
	public void sendKey(String email) throws Exception {
		int key = (int) (1000000000 * Math.random());
		// 通过Spring获取实例
		UserOp userop = (UserOp) Const.context.getBean("userop");
		// 获取用户ID
		String userID = userop.getIDByEamil(email);
		content = "尊敬的用户 " + userID + ",您好 ！" + enter + content;
		randomKeys.put(userID, key);
		utils.SendMailUtil.sendMail(email, content + key + "&userID=" + userID, "重置密码");
	}

	public static void main(String[] args) throws Exception {
		UserController i = new UserController();
		i.sendEmail("huweicai@outlook.com");
	}

	@RequestMapping("/sendemail")
	public String sendEmail(@RequestParam("email") String eamil) throws Exception {
		sendKey(eamil);
		return "afterSendEmail";
	}

	// 更改密码页面
	@RequestMapping("/resetpassword")
	public String setPasswordAgain(HttpServletRequest req) {
		String userID = req.getParameter("userID");
		String key = req.getParameter("key");
		if (userID == null || key == null) {
			System.out.println("IDorKey为空");
			return "resetPasswordFail";
		} else if (randomKeys.get(userID) - Integer.valueOf(key) != 0) {
			System.out.println("userID=" + randomKeys.get(userID) + "key=" + key);
			System.out.println((randomKeys.get(userID) - Integer.valueOf(key)));
			return "resetPasswordFail";
		} else {
			return "resetpassword";
		}
	}

	// 在数据库中更新密码
	@RequestMapping("/resetpasswordondb")
	public String resetpasswordOnDB(HttpServletRequest req) throws Throwable {
		System.out.println("被调用了！！！");
		String password = req.getParameter("reNewPassword");
		String userID = req.getParameter("userID");
		System.out.println("获取到" + "password=" + password + "userID+" + userID);
		userID = userID.substring(7);
		UserOp userop = (UserOp) Const.context.getBean("userop");
		userop.resetPassword(userID, password);
		return "resetpassword";
	}

	// 个人资料页面
	@RequestMapping("personalprofile")
	public ModelAndView goPersonalProfile(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Cookie[] cookies=request.getCookies();
	  	for(Cookie cookie:cookies){
	  		//已经登录，存在cookie信息
	  		if(cookie.getName().compareTo("userID")==0){
	  			mav.setViewName("personalprofile");
	  			return mav;
	  		}
	  	}
		mav.addObject("tip", "请先登录");
		mav.setViewName("tip");
		return mav;
	}

	// 更新个人资料
	@RequestMapping("updateprofile")
	public String updateProfile(HttpServletRequest req) {
		ApplicationContext context = new ClassPathXmlApplicationContext("configuration/beans.xml");
		UserOp userop = (UserOp) context.getBean("userop");
		User user = userop.getUserByID(req.getParameter("userID"));
		user.setUserID(req.getParameter("userID"));
		user.setEmail(req.getParameter("email"));
		user.setSex(req.getParameter("sex"));
		user.setUsername(req.getParameter("username"));
		user.setBirth_day(req.getParameter("birthday"));
		user.setSelf_introduce(req.getParameter("self_introduce"));
		userop.updateUser(user);
		System.out.println("更新用户资料：" + user);
		return "personalprofile";
	}
}

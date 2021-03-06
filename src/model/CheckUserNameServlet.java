package model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.UserOp;
import service.impl.UserOpImpl;

public class CheckUserNameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			//获取将要校验的用户名
			String userName=request.getParameter("username");
			System.out.println(userName);
			//将username传递到service层
			UserOp userOp=new UserOpImpl();
			boolean isExist=false;
			try {
				isExist=userOp.isUserIDAvailable(userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//以json的格式对页面进行相应
			response.getWriter().write("{\"isExist\":"+isExist+"}");
			System.out.println(isExist);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
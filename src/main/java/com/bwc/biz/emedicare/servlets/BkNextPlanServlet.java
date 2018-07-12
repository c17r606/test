package com.bwc.biz.emedicare.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwc.biz.emedicare.common.HashEncoder;
import com.bwc.biz.emedicare.common.JdbcUtil;
import com.bwc.biz.emedicare.form.User;

/**
 * Servlet implementation class BkNextPlanServlet
 */
public class BkNextPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BkNextPlanServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String mode = request.getParameter("mode");
		
		HttpSession session = request.getSession();
		User userinfo = (User)session.getAttribute("userinfo");
		
		if("submit".equals(mode)){
			String no=request.getParameter("no");
			String userid=userinfo.getUserId();
			String appointdate=request.getParameter("appointdate");
			String content=request.getParameter("content");
			String status=request.getParameter("status");
			String sql = "update cdata_appointments set no=?, userid=?, appointdate=?, header=NULL, content=?, status=? where userid=?";
     		Object[] params = new Object[5];
     		params[0] = no;
     		params[1] = userid;
     		params[2] = appointdate;
     		params[3] = content;
     		params[4] = status;
     		JdbcUtil.getInstance().executeUpdate(sql, params);
     		
			session.setAttribute("userinfo", userinfo);
			
			request.getRequestDispatcher("bknextplan.do").forward(request, response);
		}else{
			String userid=userinfo.getUserId();
			String sql = "select * from mstr_user where userid=? and delflg='0'";
     		Object[] params = new Object[1];
     		params[0] = userid;
     		List<Object> userinfolist = JdbcUtil.getInstance().excuteQuery(sql, params);
     		Map<String, Object> info = (Map<String, Object>)userinfolist.get(0);
     		
     		request.setAttribute("userid", userid);
     		request.setAttribute("username", info.get("username").toString());
     		request.setAttribute("langinx", Integer.parseInt(info.get("lang").toString()));
     		request.setAttribute("sex", info.get("sex").toString());
     		request.setAttribute("telnum", info.get("telnum").toString());
     		request.setAttribute("address", info.get("address").toString());
     		request.setAttribute("birthday", info.get("birthday").toString());
			request.getRequestDispatcher("personalinfo.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.bwc.biz.emedicare.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

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
//			String userid=userinfo.getUserId();
			String username=request.getParameter("username");
//			String langinx=request.getParameter("langinx");
//			langinx= langinx == null ? "0" : langinx;
			String sex=request.getParameter("sex");
//			String telnum=request.getParameter("telnum");
//			String address=request.getParameter("address");
//			String birthday=request.getParameter("birthday");
			String sql = "update mstr_user set  sex = ? where userid='u0000002'";
     		Object[] params = new Object[1];
//     		params[0] = username;
//     		params[1] = langinx;
     		params[0] = sex;
//     		params[3] = telnum;
//     		params[4] = address;
//     		params[5] = birthday;
//     		params[6] = userid;
     		JdbcUtil.getInstance().executeUpdate(sql, params);
     		
     		userinfo.setUserName(username);
//     		userinfo.setLanginx(new Integer(langinx));
//     		userinfo.setSex(sex);
//     		userinfo.setTelnum(telnum);
//     		userinfo.setAddress(address);
//			session.setAttribute("userinfo", userinfo);
			
			request.getRequestDispatcher("bknextplan.jsp").forward(request, response);
		}else{
			String userid=userinfo.getUserId();
			String sql = "select * from cdata_appointments where userid='U0000002' and no='1'";
     		Object[] params = new Object[1];
     		params[0] = userid;
     		List<Object> userinfolist = JdbcUtil.getInstance().excuteQuery(sql, params);
     		Map<String, Object> info = (Map<String, Object>)userinfolist.get(0);
     		
     		request.setAttribute("userid", userid);
     		request.setAttribute("username", info.get("username").toString());
     		request.setAttribute("appointdate", info.get("appointdate").toString());
     		request.setAttribute("content", info.get("content").toString());
     		request.setAttribute("status", info.get("status").toString());
     		
			request.getRequestDispatcher("bknextplan.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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
			String userid=request.getParameter("userid");
			String username=request.getParameter("username");
			String appointdate=request.getParameter("appointdate");
			String content=request.getParameter("content");
			String status=request.getParameter("status");
			String sql = "INSERT INTO `cdata_appointments` VALUES (?,?,?,?,?,?) ";
     		Object[] params = new Object[6];
     		params[0] = "10";
     		params[1] = userid;
     		params[2] = appointdate;
     		params[3] = " ";
     		params[4] = content;
     		params[5] = status;
     		JdbcUtil.getInstance().executeUpdate(sql, params);
			
			request.getRequestDispatcher("bknextplan.jsp").forward(request, response);
		}else{
//			String userid=userinfo.getUserId();
//			String sql = "select * from cdata_appointments where userid='U0000002' and no='1'";
//     		Object[] params = new Object[1];
//     		params[0] = userid;
//     		List<Object> userinfolist = JdbcUtil.getInstance().excuteQuery(sql, params);
//     		Map<String, Object> info = (Map<String, Object>)userinfolist.get(0);
//     		
//     		request.setAttribute("userid", userid);
//     		request.setAttribute("username", info.get("username").toString());
//     		request.setAttribute("appointdate", info.get("appointdate").toString());
//     		request.setAttribute("content", info.get("content").toString());
//     		request.setAttribute("status", info.get("status").toString());
//     		
			//request.getRequestDispatcher("bknextplan.jsp").forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

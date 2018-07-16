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
			String appointdate=request.getParameter("appointdate");
			String content=request.getParameter("content");
			String status=request.getParameter("status");
			String sql = "INSERT INTO `cdata_appointments`(userid,appointdate,header,content,status) VALUES (?,?,?,?,?) ";
     		Object[] params = new Object[5];
     		params[0] = userid;
     		params[1] = appointdate;
     		params[2] = "";
     		params[3] = content;
     		params[4] = status;
     		JdbcUtil.getInstance().executeUpdate(sql, params);
			
			request.getRequestDispatcher("bknextplan.jsp").forward(request, response);
		} else {
			this.list(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		JSONArray appointmentinfolist = new JSONArray();
		String sql = "select mstr_user.username username,cdata_appointments.appointdate appointdate,cdata_appointments.content content,cdata_appointments.status status from mstr_user inner join cdata_appointments on cdata_appointments.userid = mstr_user.userid order by cdata_appointments.no";
		List<Object> userinfodata = JdbcUtil.getInstance().excuteQuery(sql, null);
 		int i=0;
		for (Object data : userinfodata) {
			Map<String, Object> row = (Map<String, Object>) data;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("username", row.get("username").toString());
			jsonObject.put("appointdate", row.get("appointdate").toString());
			jsonObject.put("content", row.get("content").toString());
			jsonObject.put("status", row.get("status").toString());
			appointmentinfolist.put(i, jsonObject);
			i++;
		}
		
		JSONObject result = new JSONObject();
		result.put("appointmentinfolist", appointmentinfolist);
		
		response.getWriter().write(result.toString());
	}
}

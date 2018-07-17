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
		JSONArray historyinfolist = new JSONArray();

		JSONArray userlist = new JSONArray();
		String sql1 = "select mstr_user.username username,cdata_appointments.appointdate appointdate,cdata_appointments.content content,cdata_appointments.status status from mstr_user inner join cdata_appointments on cdata_appointments.userid = mstr_user.userid where status=0 order by no desc";
		String sql2 = "select mstr_user.username username,cdata_appointments.appointdate appointdate,cdata_appointments.content content,cdata_appointments.status status from mstr_user inner join cdata_appointments on cdata_appointments.userid = mstr_user.userid where status=1 order by no desc";
		String sql3 = "SELECT userid,username FROM mstr_user where userid REGEXP '^U'";
		List<Object> userinfodata1 = JdbcUtil.getInstance().excuteQuery(sql1, null);
		List<Object> userinfodata2 = JdbcUtil.getInstance().excuteQuery(sql2, null);
		List<Object> userinfodata3 = JdbcUtil.getInstance().excuteQuery(sql3, null);

 		int i=0;
		for (Object data : userinfodata1) {
			Map<String, Object> row = (Map<String, Object>) data;
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("username", row.get("username").toString());
			jsonObject1.put("appointdate", row.get("appointdate").toString());
			jsonObject1.put("content", row.get("content").toString());
			jsonObject1.put("status", row.get("status").toString());
			appointmentinfolist.put(i, jsonObject1);
			i++;
		}
		i=0;
		for (Object data : userinfodata2) {
			Map<String, Object> row = (Map<String, Object>) data;
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("username", row.get("username").toString());
			jsonObject2.put("appointdate", row.get("appointdate").toString());
			jsonObject2.put("content", row.get("content").toString());
			jsonObject2.put("status", row.get("status").toString());
			historyinfolist.put(i, jsonObject2);
			i++;
		}

		i=0;
		for (Object data : userinfodata3) {
			Map<String, Object> row = (Map<String, Object>) data;
			JSONObject jsonObject3 = new JSONObject();
			jsonObject3.put("userid", row.get("userid").toString());
			jsonObject3.put("username", row.get("username").toString());
			userlist.put(i, jsonObject3);
			i++;
		}
		
		JSONArray result = new JSONArray();
		
		JSONObject result1 = new JSONObject();
		JSONObject result2 = new JSONObject();
		JSONObject result3 = new JSONObject();
		result1.put("appointmentinfolist", appointmentinfolist);
		result2.put("historyinfolist", historyinfolist);
		result3.put("userlist", userlist);
		
		result.put(0, result1);
		result.put(1, result2);
		result.put(2, result3);

		response.getWriter().write(result.toString());
	}
}
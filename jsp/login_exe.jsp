<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
String account = request.getParameter("user_account");
String passwd = request.getParameter("user_passwd");

UserAccountMgr uam = new UserAccountMgr();
UserAccount ua = uam.login(account ,passwd);
if (ua != null) {

	//クッキー作成
	Cookie newcookie = new Cookie("user_account_id",Integer.toString( ua.getUserAccountId() ));
	newcookie.setMaxAge(60 * 60 * 24 * 1);
	response.addCookie(newcookie);

	//ログイン成功
	//一覧へ
	session.setAttribute("account" ,ua);
	application.getRequestDispatcher("/jsp/analysis_menu.jsp")
			.forward(request,response);

} else {

	//ログイン失敗
	//TOPへ
	response.sendRedirect("login.jsp?error_code=001");

}

%>
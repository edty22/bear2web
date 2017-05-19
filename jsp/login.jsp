<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//クッキーがあったら
//ユーザー情報を取得して、メニューに飛ばす
// クッキーの表示
Cookie[] cookies = request.getCookies();
if (cookies != null) {

	int userAccountId = 0;

	for(int i = 0;i < cookies.length;i++) {
		Cookie c = cookies[i];
		if (c.getName().equals("user_account_id")) {
			userAccountId = Integer.parseInt(c.getValue());
			break;
		}

	}

	UserAccountMgr uam = new UserAccountMgr();
	UserAccount ua = uam.getUserAccountById( userAccountId );

	if (ua != null) {

		//ログイン成功
		//一覧へ
		session.setAttribute("account" ,ua);
		application.getRequestDispatcher("/jsp/analysis_menu.jsp")
				.forward(request,response);

	}
}

String error_code = request.getParameter("error_code");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>分析システム ログイン</title>
</head>
<body>

<h2>分析システム ログイン</h2>

<form action="login_exe.jsp" method="post">

<%
if (error_code != null) {

	String errorMsg = "";
	if (error_code.equals("001")) {
		errorMsg = "ログインできませんでした、アカウントもしくはパスワードが間違っています。";
	} else if (error_code.equals("002")) {
		errorMsg = "ログインしていません";
	}
%>

<font color="red"><b><%= errorMsg %></b></font>

<% } %>

<table border="1">

<tr>
	<td bgcolor="#006699"><font color="#F0F8FF" size="-1"><b>アカウント</b></font></td>
	<td><input type="text" size="20" name="user_account" value=""></td>
</tr>

<tr>
	<td bgcolor="#006699"><font color="#F0F8FF" size="-1"><b>パスワード</b></font></td>
	<td><input type="password" size="20" name="user_passwd" value=""></td>
</tr>


</table>
<br>
<input type="submit" value="ログイン">

</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>

<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%@page import="java.sql.*"%>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//ログイン中のメンバーのみアクセス可能
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//ログイン失敗
	//認証していません
	response.sendRedirect("/bear2Web/jsp/login.jsp?error_code=002");
}

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");

String ptnCd = request.getParameter("pattern_cd");

//==　計算　===
PatternMgr pm = new PatternMgr();
// マルチスレッドで実行
pm.start(ptnCd);

//pm.calcPattern(ptnCd);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>データ計算</title>
</head>
<body>

<br><br>
処理を開始しました

<br>
<a href="pattern_list.jsp">パターン一覧へ</a>
<br>
<a href="/bear2Web/jsp/dashbord/statistics_list.jsp">分析一覧</a>
<br>
<a href="/bear2Web/jsp/market_admin/market_list.jsp">マーケット一覧へ</a>

</body>
</html>


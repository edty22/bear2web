<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
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
	response.sendRedirect("login.jsp?error_code=002");
}

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");
//request.setCharacterEncoding("UTF-8");


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.net.URLEncoder"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>分析システム メニュー</title>
</head>
<body>


<h2>分析システム メニュー</h2>

<!-- メニュー領域 -->

<table width="260" bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF"><b>分析管理</b></td>
  </tr>
  <tr>
    <td bgcolor="F0F8FF">
      <table>
        <tr>
          <td><a href="dashbord/statistics_list.jsp">分析リスト</a></td>
        </tr>
        <tr>
          <td><a href="dashbord/oracle_data.jsp">予測データ</a></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<table width="260" bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF"><b>管理ツール</b></td>
  </tr>
  <tr>
    <td bgcolor="F0F8FF">
      <table>
        <tr>
          <td><a href="market_admin/market_list.jsp" target="_blank">マーケットマスタ管理</a></td>
        </tr>
        <tr>
          <td><a href="pattern/pattern_list.jsp">分析パターン管理</a></td>
        </tr>

        <tr>
          <td><a href="link_list.jsp" target="_blank">リンク集</a></td>
        </tr>

      </table>
    </td>
  </tr>
</table>



</body>
</html>


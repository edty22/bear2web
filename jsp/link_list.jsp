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
<title>リンク集</title>
</head>
<body>


<h2>分析システム メニュー</h2>

<!-- メニュー領域 -->

<table width="260" bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF"><b>リンク集</b></td>
  </tr>
  <tr>
    <td bgcolor="F0F8FF">
      <table>

        <tr>
          <td><a href="https://stocks.finance.yahoo.co.jp/stocks/history/?code=998407.O" target="_blank">日経平均</a></td>
        </tr>

        <tr>
          <td><a href="http://quotes.wsj.com/index/DJIA/historical-prices#" target="_blank">ダウ</a></td>
        </tr>

        <tr>
          <td><a href="http://quotes.wsj.com/index/COMP/historical-prices" target="_blank">ナスダック</a></td>
        </tr>

        <tr>
          <td><a href="http://quotes.wsj.com/index/SPX/historical-prices" target="_blank">S&P500</a></td>
        </tr>

        <tr>
          <td><a href="https://stocks.finance.yahoo.co.jp/stocks/history/?code=USDJPY=X" target="_blank">米ドル/円</a></td>
        </tr>

        <tr>
          <td><a href="">準備中</a></td>
        </tr>

        <tr>
          <td><a href="">準備中</a></td>
        </tr>

        <tr>
          <td><a href="">準備中</a></td>
        </tr>




      </table>
    </td>
  </tr>
</table>
<br>




</body>
</html>


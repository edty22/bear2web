<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%@page import="java.text.*"%>
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

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.net.URLEncoder"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>分析システム</title>
</head>
<body>


<table width="100%">
<tr>
  <td nowrap="nowrap">
    <font size="4"><b>マーケット一覧</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>


<br>
<a href="../analysis_menu.jsp" >TOPメニュー</a>
<br>

<font>
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th nowrap>マーケットコード</th>
    <th nowrap>名称</th>
    <th nowrap>タイプ</th>
    <th nowrap>市場</th>
    <th nowrap>単位</th>
    <th width="40">&nbsp;</th>
    <th width="40">&nbsp;</th>
  </tr>

<%
//マーケット一覧表示
MarketMgr mm = new MarketMgr();

List<Market> markets = mm.getAllMarkets();
for (Iterator<Market> it = markets.iterator(); it.hasNext();) {

	Market mkt = (Market)it.next();
%>

  <tr>
    <td bgcolor="#FFFFFF" nowrap><%= mkt.getMarketCd() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= mkt.getMarketName() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= mkt.getType() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= mkt.getMarket() != null ? mkt.getMarket() : "&nbsp" %></td>
    <td bgcolor="#FFFFFF" nowrap><%= mkt.getUnit() %></td>
    <td bgcolor="#FFFFFF">
    	<input type="button" value="価格管理(一件)"
        	onClick="document.location.href='price_list.jsp?market_cd=<%= mkt.getMarketCd() %>';">
    </td>
    <td bgcolor="#FFFFFF">
    	<input type="button" value="価格管理(まとめて)"
        	onClick="document.location.href='bulk_regist_price.jsp?market_cd=<%= mkt.getMarketCd() %>';">
    </td>
  </tr>

<%
}
%>
</table>
</font>

</body>
</html>


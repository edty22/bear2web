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
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");

//== コードの取得
String marketCd = request.getParameter("market_cd");

MarketMgr mm = new MarketMgr();
Market market = mm.getMarketByCd(marketCd);

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
    <font size="4"><b>価格一覧</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>

<br>
<a href="/bear2Web/jsp/market_admin/market_list.jsp" target="_blank">マーケットマスタ管理</a>
<br>
<a href="regist_price.jsp?market_cd=<%= marketCd %>"><b>新規価格追加</b></a>

<font size="-1">
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th nowrap>マーケット<br>コード</th>
    <th nowrap>日付</th>
    <th nowrap>始値</th>
    <th nowrap>高値</th>
    <th nowrap>安値</th>
    <th nowrap>終値</th>
    <th nowrap>出来高</th>
    <th width="40">&nbsp;</th>
    <th width="40">&nbsp;</th>
  </tr>

<%
PriceMgr pm = new PriceMgr();

List<Price> prices = pm.getAllPricesByCd(marketCd);

//マーケット一覧表示

for (Iterator<Price> it = prices.iterator(); it.hasNext();) {

	Price price = (Price)it.next();
%>

  <tr>
    <td bgcolor="#FFFFFF" nowrap><%= price.getMarketCd() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getRegistDate() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getStartPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getHighPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getLowPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getEndPrice() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= price.getVolume() != 0 ? price.getVolume() : "&nbsp" %></td>

    <td bgcolor="#FFFFFF">
    	<input type="button" value="変更"
        	onClick="document.location.href='update_price.jsp?market_cd=<%= price.getMarketCd() %>&regist_date=<%= price.getRegistDate() %>';">
    </td>
    <td bgcolor="#FFFFFF">
    	<input type="button" value="削除"
        	onClick="document.location.href='delete_price.jsp?market_cd=<%= price.getMarketCd() %>&regist_date=<%= price.getRegistDate() %>';">
    </td>
  </tr>

<%
}
%>
</table>
</font>

</body>
</html>


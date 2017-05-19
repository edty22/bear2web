<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//ログイン中のメンバーのみアクセス可能
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//ログイン失敗
	//認証していません
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== 必要なデータ抽出 ===
//== コードの取得
String marketCd = request.getParameter("market_cd");
String registDate = request.getParameter("regist_date");

//== データの取得
MarketMgr mm = new MarketMgr();
Market market = mm.getMarketByCd(marketCd);

PriceMgr pm = new PriceMgr();
Price price = pm.getPriceByCdDate(marketCd, registDate);

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>価格情報更新</title>
</head>
<body>


<h2>価格情報更新</h2>
<font color="red"><b>以下の情報削除します</b></font>

<form name="form1" action="delete_price_exe.jsp" method="post">
<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- 入力フォーム左側 -->
		<td width="500">

			<!-- 基本情報 -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>マーケットコード</b></font></td>
					<td bgcolor="#FFFFFF"><%= marketCd %></td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>登録日付</b></font></td>
					<td bgcolor="#FFFFFF"><%= registDate %></td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>始値</b></font></td>
					<td bgcolor="#FFFFFF"><%= price.getStartPrice() %></td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>高値</b></font></td>
					<td bgcolor="#FFFFFF"><%= price.getHighPrice() %></td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>安値</b></font></td>
					<td bgcolor="#FFFFFF"><%= price.getLowPrice() %></td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>終値</b></font></td>
					<td bgcolor="#FFFFFF"><%= price.getEndPrice() %></td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>出来高</b></font></td>
					<td bgcolor="#FFFFFF"><%= price.getVolume() %></td>
				</tr>

			</table>

			<input type="hidden" name="market_cd" value="<%= marketCd %>">
			<input type="hidden" name="regist_date" value="<%= registDate %>">

		</td>

	</tr>

</table>
<br>
<input type="submit" value="削除">

</form>

<br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">一覧へ</a>



</body>
</html>
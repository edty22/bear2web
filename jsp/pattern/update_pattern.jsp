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
	response.sendRedirect("/near2Web/jsp/login.jsp?error_code=002");
}

//=== 必要なデータ抽出 ===
//== コードの取得
String patternCd = request.getParameter("pattern_cd");

MarketMgr mm = new MarketMgr();
List<Market> markets = mm.getAllMarkets();

PatternMgr pm = new PatternMgr();
Pattern ptn = pm.getPatternByCd(patternCd);

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>パターン更新</title>
</head>
<body>


<h2>パターン更新</h2>

<form name="form1" action="update_pattern_exe.jsp" method="post">
<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- 入力フォーム左側 -->
		<td width="500">

			<!-- 基本情報 -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>パターンコード</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="pattern_cd" size="20" value="<%= patternCd %>">
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>パターン名称</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="pattern_name" size="50" value="<%= ptn.getPatternName() %>">
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>対象マーケット<br>コード</b></font></td>
					<td bgcolor="#FFFFFF">
						<select name=target_market_cd>
						<%
						String selectStr = "";
						for (Iterator<Market> it = markets.iterator(); it.hasNext();) {

							Market market = (Market)it.next();

							if (market.getMarketCd().equals( ptn.getTargetMarketCd() )) {
								selectStr = "selected";
							} else {
								selectStr = "";
							}
						%>
						<option value="<%= market.getMarketCd() %>" <%= selectStr %> ><%= market.getMarketCd() %>:<%= market.getMarketName() %>
						<%
						}
						%>
						</select>
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>対象マーケット<br>分析パターン</b></font></td>
					<td bgcolor="#FFFFFF">
						<select name="target_atype">
						<%
						//ToDo 分析パターンの追加
						// 比率など
						//
						%>
							<option value="DIR">DIR:上げ下げ
						</select>
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>メモ</b></font></td>
					<td bgcolor="#FFFFFF">
						<textarea name="memo" rows="4" cols="40"><%= ptn.getMemo() %></textarea>
					</td>
				</tr>

			</table>

		</td>

	</tr>

</table>

<input type="submit" value="更新">

</form>

<br>
<a href="pattern_list.jsp">パターン一覧へ</a>



</body>
</html>
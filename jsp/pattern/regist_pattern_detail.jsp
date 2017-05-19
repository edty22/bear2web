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

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>パターン詳細新規追加</title>
</head>
<body>


<h2>パターン詳細新規追加</h2>

<form name="form1" action="regist_pattern_detail_exe.jsp" method="post">
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
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>番号</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="seq_id" size="20">
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>対象マーケット<br>コード</b></font></td>
					<td bgcolor="#FFFFFF">
						<select name=market_cd>
						<%
						for (Iterator<Market> it = markets.iterator(); it.hasNext();) {

							Market market = (Market)it.next();
						%>
						<option value="<%= market.getMarketCd() %>"><%= market.getMarketCd() %>:<%= market.getMarketName() %>
						<%
						}
						%>
						</select>
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>何日前</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="pre_day_cnt" size="20">
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>対象マーケット<br>分析パターン</b></font></td>
					<td bgcolor="#FFFFFF">
						<select name="pattern_atype">
						<%
						//ToDo 分析パターンの追加
						// 比率など
						//
						%>
							<option value="DIR">DIR:上げ下げ
						</select>
					</td>
				</tr>

			</table>

		</td>

	</tr>

</table>

<input type="submit" value="登録">

</form>

<br>
<a href="pattern_list.jsp">パターン一覧へ</a>



</body>
</html>
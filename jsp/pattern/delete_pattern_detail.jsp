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
int seqId = Integer.parseInt( request.getParameter("seq_id") );

MarketMgr mm = new MarketMgr();
List<Market> markets = mm.getAllMarkets();

PatternMgr pm = new PatternMgr();
PatternDetail pd = pm.getPatternDetailByKey( patternCd ,seqId );

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>パターン詳細新削除</title>
</head>
<body>


<h2>パターン詳細削除</h2>

<form name="form1" action="delete_pattern_detail_exe.jsp" method="post">

<input type="hidden" name="pattern_cd" value="<%= patternCd %>">
<input type="hidden" name="seq_id" value="<%= seqId %>">

<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- 入力フォーム左側 -->
		<td width="500">

			<!-- 基本情報 -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>パターンコード</b></font></td>
					<td bgcolor="#FFFFFF"><%= patternCd %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>番号</b></font></td>
					<td bgcolor="#FFFFFF"><%= seqId %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>対象マーケット<br>コード</b></font></td>
					<td bgcolor="#FFFFFF"><%= pd.getMarketCd() %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>何日前</b></font></td>
					<td bgcolor="#FFFFFF"><%= pd.getPreDayCnt()  %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>対象マーケット<br>分析パターン</b></font></td>
					<td bgcolor="#FFFFFF">DIR:上げ下げ</td>
				</tr>

			</table>

		</td>

	</tr>

</table>

<input type="submit" value="削除">

</form>

<br>
<a href="pattern_list.jsp">パターン一覧へ</a>



</body>
</html>
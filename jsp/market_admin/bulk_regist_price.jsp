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

MarketMgr mm = new MarketMgr();
Market market = mm.getMarketByCd(marketCd);

//== 今日の日付
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String strDate = sdf.format(cal.getTime());

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>価格一括登録</title>
</head>
<body>


<h2>価格情報一括登録</h2>

<form name="form1"  enctype="multipart/form-data"  action="bulk_regist_price_exe.jsp" method="post">
<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- 入力フォーム左側 -->
		<td width="500">

			<!-- 基本情報 -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>マーケットコード</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="market_cd" size="20" value=<%= marketCd %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>ファイル</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="file" name="file"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>アップロードオプション</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="radio" name="bulk_option" value="ALL" checked>洗い替え
						<input type="radio" name="bulk_option" value="DIFF">差分
					</td>
				</tr>

			</table>

		</td>

	</tr>

</table>
<br>
<input type="submit" value="アップロード">

</form>

<br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">価格一覧へ</a>
<br>
<a href="market_list.jsp">マーケット一覧へ</a>



</body>
</html>
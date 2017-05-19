<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.math.*"%>
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

String marketCd = request.getParameter("market_code");
if (marketCd == null) {
	marketCd = "NIKKEI";	//初期値
}

String registDate = request.getParameter("regist_date");
if (registDate == null) {
	Calendar c = Calendar.getInstance();

    //フォーマットパターンを指定して表示する
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    registDate = sdf.format(c.getTime());   //初期値
}

//== コードの取得




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
    <font size="4"><b>予測データ</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>


<br>
<a href="../analysis_menu.jsp" >TOPメニュー</a>
<br>

<form method="POST" action="oracle_data.jsp">
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF" nowrap="nowrap">対象マーケット</td>
    <td bgcolor="#FFFFFF">
      <select name="market_code">
        <option value="NIKKEI" selected>NIKKEI:日経平均
      </select>
  </tr>
  <tr>
    <td bgcolor="#CCCCFF" nowrap="nowrap">対象日付</td>
    <td bgcolor="#FFFFFF"><input size="30" type="text" name="regist_date"
    	value="<%= registDate %>"></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF" colspan="3">
      <input type="submit" value="検索">
    </td>
  </tr>
</table>
</form>

<br>

<font>
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th nowrap>パターン<br>コード</th>
    <th nowrap>トータル数</th>
    <th nowrap>対象<br>（上げ/下げ）</th>
    <th nowrap>対象数</th>
    <th nowrap>対象比率</th>
    <th nowrap>対向数</th>
    <th nowrap>対向比率</th>
    <th nowrap>パターン組合せ</th>

  </tr>

<%
OracleMgr om = new OracleMgr();

List<Statistics> stes = om.predictData( marketCd ,registDate );

//マーケット一覧表示

for (Iterator<Statistics> it = stes.iterator(); it.hasNext();) {

	Statistics st = (Statistics)it.next();

	double targetRatio = ((double)st.getTargetCnt() / (double)st.getTotalCnt() ) * 100 ;
	BigDecimal x = new BigDecimal(targetRatio);
	x = x.setScale(3, BigDecimal.ROUND_HALF_UP);
	String dispTragetRatio = x.toString();

	double pairRatio = ((double)st.getPairCnt() / (double)st.getTotalCnt() ) * 100 ;
	BigDecimal y = new BigDecimal(pairRatio);
	y = y.setScale(3, BigDecimal.ROUND_HALF_UP);
	String dispPairRatio = y.toString();

	String ratioColor = "#FFFFFF";

	if (targetRatio > 70.0) {
		ratioColor = "#F73117";
	} else if (targetRatio > 60.0) {
		ratioColor = "#F1BA2F";
	} else if (targetRatio > 55.0) {
		ratioColor = "#F4F58B";
	}

%>

  <tr>
    <td bgcolor="#FFFFFF" nowrap><%= st.getPatternCd() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= st.getTotalCnt() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= st.getTargetValue() == 0 ? "下げ" : "上げ" %></td>
    <td bgcolor="#FFFFFF" nowrap><%= st.getTargetCnt() %></td>
    <td bgcolor="<%= ratioColor %>" nowrap><%= dispTragetRatio %></td>
    <td bgcolor="#FFFFFF" nowrap><%= st.getPairCnt() %></td>
    <td bgcolor="#FFFFFF" nowrap><%= dispPairRatio %></td>
    <td bgcolor="#FFFFFF" nowrap><%= st.getSummaryValue() %></td>
  </tr>

<%
}
%>
</table>
</font>

</body>
</html>


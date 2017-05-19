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
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");

String ptnCode = request.getParameter("ptn_code");
if (ptnCode == null) {
	ptnCode = "PTN0001";	//初期値
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
    <font size="4"><b>分析一覧</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>


<br>
<a href="../analysis_menu.jsp" >TOPメニュー</a>
<br>

<form method="POST" action="statistics_list.jsp">
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF">分析パターン</td>
    <td bgcolor="#FFFFFF" colspan="3">
      <select name="ptn_code">
      <%
        PatternMgr pm = new PatternMgr();
        List<Pattern> ptns = pm.getAllPattern();

        for (Iterator<Pattern> it = ptns.iterator(); it.hasNext();) {
			Pattern ptn = (Pattern) it.next();

			String selected = "";
			if (ptn.getPatternCd().equals( ptnCode ) ) {
				selected = " selected";
			}
		%>
			<option value="<%= ptn.getPatternCd() %>" <%= selected %>><%= ptn.getPatternCd() + ":" + ptn.getPatternName() %>
		<%  } %>
      </select>
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
StatisticsMgr sm = new StatisticsMgr();

List<Statistics> stes = sm.getStatisticsByCd(ptnCode, null);

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


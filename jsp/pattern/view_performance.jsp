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

String patternCd = request.getParameter("pattern_cd");
if (patternCd == null) {
	patternCd = "PTN0001";	//初期値
}

//== 取得
PatternMgr pm = new PatternMgr();
Pattern ptn = pm.getPatternByCd(patternCd);

StatisticsMgr sm = new StatisticsMgr();

List<Statistics> stes = sm.getStatisticsByCd(patternCd, null);

//マーケット比率計算
double totalCnt = 0;
double r80OverCnt = 0;
double r70Cnt = 0;
double r60Cnt = 0;
double r55Cnt = 0;
double r50Cnt = 0;
double r45Cnt = 0;
double r40Cnt = 0;
double r30Cnt = 0;
double r20Cnt = 0;
double r20UnderCnt = 0;

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

	totalCnt += st.getTargetCnt();

	if (targetRatio > 80.0) {
		r80OverCnt += st.getTargetCnt();
	} else if (targetRatio > 70.0) {
		r70Cnt += st.getTargetCnt();
	} else if (targetRatio > 60.0) {
		r60Cnt += st.getTargetCnt();
	} else if (targetRatio > 55.0) {
		r55Cnt += st.getTargetCnt();
	} else if (targetRatio > 50.0) {
		r50Cnt += st.getTargetCnt();
	} else if (targetRatio > 45.0) {
		r45Cnt += st.getTargetCnt();
	} else if (targetRatio > 40.0) {
		r40Cnt += st.getTargetCnt();
	} else if (targetRatio > 30.0) {
		r30Cnt += st.getTargetCnt();
	} else if (targetRatio > 20.0) {
		r20Cnt += st.getTargetCnt();
	} else {
		r20UnderCnt += st.getTargetCnt();
	}

}

BigDecimal tmp1 = new BigDecimal(r80OverCnt / totalCnt * 100);
tmp1 = tmp1.setScale(3, BigDecimal.ROUND_HALF_UP);
String s80OverCnt = tmp1.toString();

BigDecimal tmp2 = new BigDecimal(r70Cnt / totalCnt * 100);
tmp2 = tmp2.setScale(3, BigDecimal.ROUND_HALF_UP);
String s70Cnt = tmp2.toString();

BigDecimal tmp3 = new BigDecimal(r60Cnt / totalCnt * 100);
tmp3 = tmp3.setScale(3, BigDecimal.ROUND_HALF_UP);
String s60Cnt = tmp3.toString();

BigDecimal tmp4 = new BigDecimal(r55Cnt / totalCnt * 100);
tmp4 = tmp4.setScale(3, BigDecimal.ROUND_HALF_UP);
String s55Cnt = tmp4.toString();

BigDecimal tmp5 = new BigDecimal(r50Cnt / totalCnt * 100);
tmp5 = tmp5.setScale(3, BigDecimal.ROUND_HALF_UP);
String s50Cnt = tmp5.toString();

BigDecimal tmp6 = new BigDecimal(r45Cnt / totalCnt * 100);
tmp6 = tmp6.setScale(3, BigDecimal.ROUND_HALF_UP);
String s45Cnt = tmp6.toString();

BigDecimal tmp7 = new BigDecimal(r40Cnt / totalCnt * 100);
tmp7 = tmp7.setScale(3, BigDecimal.ROUND_HALF_UP);
String s40Cnt = tmp7.toString();

BigDecimal tmp8 = new BigDecimal(r30Cnt / totalCnt * 100);
tmp8 = tmp8.setScale(3, BigDecimal.ROUND_HALF_UP);
String s30Cnt = tmp8.toString();

BigDecimal tmp9 = new BigDecimal(r20Cnt / totalCnt * 100);
tmp9 = tmp9.setScale(3, BigDecimal.ROUND_HALF_UP);
String s20Cnt = tmp9.toString();

BigDecimal tmp10 = new BigDecimal(r20UnderCnt / totalCnt * 100);
tmp10 = tmp10.setScale(3, BigDecimal.ROUND_HALF_UP);
String s20UnderCnt = tmp10.toString();

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
    <font size="4"><b>パターン実績分布</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>

<font size="-1">
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th>パターンコード</th>
    <td width="200" bgcolor="#FFFFFF" nowrap><%= ptn.getPatternCd() %></td>
  </tr>
  <tr bgcolor="#CCCCFF">
    <th>名称</th>
    <td width="200" bgcolor="#FFFFFF" nowrap><%= ptn.getPatternName()  %></td>
  </tr>
  <tr bgcolor="#CCCCFF">
    <th>備考</th>
    <td width="200" bgcolor="#FFFFFF" nowrap><%= ptn.getMemo()  %></td>
  </tr>
  <tr bgcolor="#CCCCFF">
    <th>総件数</th>
    <td width="200" bgcolor="#FFFFFF" nowrap><%= totalCnt  %> 件</td>
  </tr>
</table>


<br>

<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th nowrap>範囲</th>
    <th nowrap width="100">比率(%)</th>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>80%以上</td>
    <td bgcolor="#FFFFFF" nowrap><%= s80OverCnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>70%～80%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s70Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>60%～70%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s60Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>55%～60%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s55Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>50%～55%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s50Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>45%～50%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s45Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>40%～45%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s40Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>30%～40%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s30Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>20%～30%</td>
    <td bgcolor="#FFFFFF" nowrap><%= s20Cnt %>%</td>
  </tr>

  <tr>
    <td bgcolor="#FFFFFF" nowrap>20%以下</td>
    <td bgcolor="#FFFFFF" nowrap><%= s20UnderCnt %>%</td>
  </tr>


</table>
</font>

<br>
<a href="pattern_list.jsp">パターン一覧へ</a>

</body>
</html>


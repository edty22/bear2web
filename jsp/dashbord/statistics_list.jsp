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
//���O�C�����̃����o�[�̂݃A�N�Z�X�\
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//���O�C�����s
	//�F�؂��Ă��܂���
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== �p�����[�^�󂯎�� ===
request.setCharacterEncoding("Windows-31J");

String ptnCode = request.getParameter("ptn_code");
if (ptnCode == null) {
	ptnCode = "PTN0001";	//�����l
}

//== �R�[�h�̎擾




%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.net.URLEncoder"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>���̓V�X�e��</title>
</head>
<body>


<table width="100%">
<tr>
  <td nowrap="nowrap">
    <font size="4"><b>���͈ꗗ</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>


<br>
<a href="../analysis_menu.jsp" >TOP���j���[</a>
<br>

<form method="POST" action="statistics_list.jsp">
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td bgcolor="#CCCCFF">���̓p�^�[��</td>
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
      <input type="submit" value="����">
    </td>
  </tr>
</table>
</form>

<br>

<font>
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th nowrap>�p�^�[��<br>�R�[�h</th>
    <th nowrap>�g�[�^����</th>
    <th nowrap>�Ώ�<br>�i�グ/�����j</th>
    <th nowrap>�Ώې�</th>
    <th nowrap>�Ώ۔䗦</th>
    <th nowrap>�Ό���</th>
    <th nowrap>�Ό��䗦</th>
    <th nowrap>�p�^�[���g����</th>

  </tr>

<%
StatisticsMgr sm = new StatisticsMgr();

List<Statistics> stes = sm.getStatisticsByCd(ptnCode, null);

//�}�[�P�b�g�ꗗ�\��

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
    <td bgcolor="#FFFFFF" nowrap><%= st.getTargetValue() == 0 ? "����" : "�グ" %></td>
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


<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
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
	response.sendRedirect("/bear2Web/jsp/login.jsp?error_code=002");
}

//=== �p�����[�^�󂯎�� ===
request.setCharacterEncoding("Windows-31J");

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
    <font size="4"><b>���̓p�^�[���ꗗ</b></font>
  </td>
  <td width="100%" align="right"></td>
</tr>
</table>

<a href="/bear2Web/jsp/analysis_menu.jsp">TOP���j���[</a>
<br>

<br>
<a href="regist_pattern.jsp"><b>�V�K�p�^�[���ǉ�</b></a>


<font>
<table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
  <tr bgcolor="#CCCCFF">
    <th width="50">�p�^�[��<br>�R�[�h</th>
    <th width="100">�p�^�[������</th>
    <th width="50">�Ώۃ}�[�P<br>�b�g�R�[�h</th>
    <th width="50">���̓^�C�v</th>
    <th width="80">����</th>
    <th width="40">&nbsp;</th>
  </tr>

<%
//�}�[�P�b�g�ꗗ�\��
PatternMgr pm = new PatternMgr();
List<Pattern> ptns = pm.getAllPattern();


MarketMgr mm = new MarketMgr();
List<Market> markets = mm.getAllMarkets();

for (Iterator<Pattern> it = ptns.iterator(); it.hasNext();) {

	Pattern ptn = (Pattern)it.next();

%>

  <tr>
    <td bgcolor="#FFFFFF" ><%= ptn.getPatternCd() %></td>
    <td bgcolor="#FFFFFF" ><%= ptn.getPatternName() %></td>
    <td bgcolor="#FFFFFF" ><%= ptn.getTargetMarketCd() %></td>
    <td bgcolor="#FFFFFF" ><%= ptn.getTargetAtype() %></td>
    <td bgcolor="#FFFFFF" >
      <%= ptn.getMemo() %><br><br>
      <input type="button" value="�ڍדo�^"
        			onClick="document.location.href='regist_pattern_detail.jsp?pattern_cd=<%= ptn.getPatternCd() %>';">

      <table bgcolor="#9999CC" border="0" cellspacing="2" cellpadding="2">
        <tr>
			<th nowrap>�ԍ�</th>
    		<th nowrap>�}�[�P�b�g�R�[�h</th>
    		<th nowrap>�����O</th>
    		<th nowrap>���̓^�C�v</th>
    		<th width="40">&nbsp;</th>
    		<th width="40">&nbsp;</th>
		</tr>
        <%
        List<PatternDetail> pds = ptn.getPatternDetails();

        for (Iterator<PatternDetail> it2 = pds.iterator(); it2.hasNext(); ) {

        	PatternDetail pd = it2.next();

        %>

		<tr>
    		<td bgcolor="#FFFFFF" nowrap><%= pd.getSeqId() %></td>
    		<td bgcolor="#FFFFFF" nowrap><%= pd.getMarketCd() %></td>
    		<td bgcolor="#FFFFFF" nowrap><%= pd.getPreDayCnt() %></td>
    		<td bgcolor="#FFFFFF" nowrap><%= pd.getPatternAtype() %></td>
    		<td bgcolor="#FFFFFF">
    			<input type="button" value="�X�V"
        			onClick="document.location.href='update_pattern_detail.jsp?pattern_cd=<%= ptn.getPatternCd() %>&seq_id=<%= pd.getSeqId() %>';">
    		</td>
    		<td bgcolor="#FFFFFF">
    			<input type="button" value="�폜"
        			onClick="document.location.href='delete_pattern_detail.jsp?pattern_cd=<%= ptn.getPatternCd() %>&seq_id=<%= pd.getSeqId() %>';">
    		</td>
    	</tr>


      	<%
        }
        %>
     </table>


    </td>
    <td bgcolor="#FFFFFF">
    	<input type="button" value="�ꗗ"
        	onClick="document.location.href='/bear2Web/jsp/dashbord/statistics.jsp.jsp?ptn_cd=<%= ptn.getPatternCd() %>';">
    	<br><br>
    	<input type="button" value="���z"
        	onClick="document.location.href='view_performance.jsp?pattern_cd=<%= ptn.getPatternCd() %>';">
    	<br><br>
    	<input type="button" value="�v�Z"
        	onClick="document.location.href='calc_data.jsp?pattern_cd=<%= ptn.getPatternCd() %>';">
    	<br><br>
    	<input type="button" value="�X�V"
        	onClick="document.location.href='update_pattern.jsp?pattern_cd=<%= ptn.getPatternCd() %>';">
    	<br><br>
    	<input type="button" value="�폜"
        	onClick="document.location.href='delete_pattern.jsp?pattern_cd=<%= ptn.getPatternCd() %>';">
    </td>
  </tr>

<%
}
%>
</table>
</font>

</body>
</html>


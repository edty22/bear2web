<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="bear2web.util.*"%>
<%@page import="bear2web.model.*"%>
<%@page import="bear2web.mgr.*"%>
<%
//���O�C�����̃����o�[�̂݃A�N�Z�X�\
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//���O�C�����s
	//�F�؂��Ă��܂���
	response.sendRedirect("/near2Web/jsp/login.jsp?error_code=002");
}

//=== �K�v�ȃf�[�^���o ===
//== �R�[�h�̎擾
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
<title>�p�^�[���ڍאV�폜</title>
</head>
<body>


<h2>�p�^�[���ڍ׍폜</h2>

<form name="form1" action="delete_pattern_detail_exe.jsp" method="post">

<input type="hidden" name="pattern_cd" value="<%= patternCd %>">
<input type="hidden" name="seq_id" value="<%= seqId %>">

<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- ���̓t�H�[������ -->
		<td width="500">

			<!-- ��{��� -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�p�^�[���R�[�h</b></font></td>
					<td bgcolor="#FFFFFF"><%= patternCd %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�ԍ�</b></font></td>
					<td bgcolor="#FFFFFF"><%= seqId %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�Ώۃ}�[�P�b�g<br>�R�[�h</b></font></td>
					<td bgcolor="#FFFFFF"><%= pd.getMarketCd() %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�����O</b></font></td>
					<td bgcolor="#FFFFFF"><%= pd.getPreDayCnt()  %></td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�Ώۃ}�[�P�b�g<br>���̓p�^�[��</b></font></td>
					<td bgcolor="#FFFFFF">DIR:�グ����</td>
				</tr>

			</table>

		</td>

	</tr>

</table>

<input type="submit" value="�폜">

</form>

<br>
<a href="pattern_list.jsp">�p�^�[���ꗗ��</a>



</body>
</html>
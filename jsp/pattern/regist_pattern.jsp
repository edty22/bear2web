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
String marketCd = request.getParameter("market_cd");

MarketMgr mm = new MarketMgr();
List<Market> markets = mm.getAllMarkets();

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>�p�^�[���V�K�ǉ�</title>
</head>
<body>


<h2>�p�^�[���V�K�ǉ�</h2>

<form name="form1" action="regist_pattern_exe.jsp" method="post">
<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- ���̓t�H�[������ -->
		<td width="500">

			<!-- ��{��� -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�p�^�[���R�[�h</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="pattern_cd" size="20">
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�p�^�[������</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="pattern_name" size="50">
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�Ώۃ}�[�P�b�g<br>�R�[�h</b></font></td>
					<td bgcolor="#FFFFFF">
						<select name=target_market_cd>
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
					<td bgcolor="#CCCCFF" nowrap><font size="-1"><b>�Ώۃ}�[�P�b�g<br>���̓p�^�[��</b></font></td>
					<td bgcolor="#FFFFFF">
						<select name="target_atype">
						<%
						//ToDo ���̓p�^�[���̒ǉ�
						// �䗦�Ȃ�
						//
						%>
							<option value="DIR">DIR:�グ����
						</select>
					</td>
				</tr>

				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>����</b></font></td>
					<td bgcolor="#FFFFFF">
						<textarea name="memo" rows="4" cols="40"></textarea>
					</td>
				</tr>

			</table>

		</td>

	</tr>

</table>

<input type="submit" value="�o�^">

</form>

<br>
<a href="pattern_list.jsp">�p�^�[���ꗗ��</a>



</body>
</html>
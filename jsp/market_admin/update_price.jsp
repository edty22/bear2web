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
	response.sendRedirect("/jsp/login.jsp?error_code=002");
}

//=== �K�v�ȃf�[�^���o ===
//== �R�[�h�̎擾
String marketCd = request.getParameter("market_cd");
String registDate = request.getParameter("regist_date");

//== �f�[�^�̎擾
MarketMgr mm = new MarketMgr();
Market market = mm.getMarketByCd(marketCd);

PriceMgr pm = new PriceMgr();
Price price = pm.getPriceByCdDate(marketCd, registDate);

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>���i���X�V</title>
</head>
<body>


<h2>���i���X�V</h2>

<form name="form1" action="update_price_exe.jsp" method="post">
<table border="0" cellspacing="5">

	<tr valign="top">
		<!-- ���̓t�H�[������ -->
		<td width="500">

			<!-- ��{��� -->
			<table bgcolor="#9999CC" width="95%">

				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�}�[�P�b�g�R�[�h</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="market_cd" size="20" value=<%= marketCd %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�o�^���t</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="regist_date" size="10" value=<%= registDate %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�n�l</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="start_price" size="20" value=<%= price.getStartPrice() %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>���l</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="high_price" size="20" value=<%= price.getHighPrice() %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>���l</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="low_price" size="20" value=<%= price.getLowPrice() %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�I�l</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="end_price" size="20" value=<%= price.getEndPrice() %>>
					</td>
				</tr>
				<tr>
					<td bgcolor="#CCCCFF"><font size="-1"><b>�o����</b></font></td>
					<td bgcolor="#FFFFFF">
						<input type="text" name="volume" size="20" value=<%= price.getVolume() %>>
					</td>
				</tr>

			</table>

		</td>

	</tr>

</table>
<br>
<input type="submit" value="�X�V">

</form>

<br>
<a href="price_list.jsp?market_cd=<%= marketCd %>">�ꗗ��</a>



</body>
</html>
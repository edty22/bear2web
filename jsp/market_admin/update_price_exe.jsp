<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
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

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");

String marketCd = request.getParameter("market_cd");
String registDate = request.getParameter("regist_date");
String startPrice = request.getParameter("start_price");
String highPrice = request.getParameter("high_price");
String lowPrice = request.getParameter("low_price");
String endPrice = request.getParameter("end_price");
String volume = request.getParameter("volume");


//=== オブジェクトにセット ===
Price price = new Price();
price.setMarketCd( marketCd );
price.setRegistDate( registDate  );
price.setStartPrice( Double.parseDouble( startPrice ) );
price.setHighPrice( Double.parseDouble( highPrice ) );
price.setLowPrice( Double.parseDouble( lowPrice ) );
price.setEndPrice( Double.parseDouble( endPrice ) );
price.setVolume( Double.parseDouble( volume ) );

//=== データ登録 ===
PriceMgr pm = new PriceMgr();

try {

	pm.updatePrice(price);

	//登録完了 一覧画面へ
	response.sendRedirect("price_list.jsp?market_cd=" + marketCd + "&msg_code=001");

} catch (Exception e) {
	e.printStackTrace();

	//TODO エラー処理
}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
</head>
<body>




<font color="red"><b>登録に失敗しました</b></font>

</body>
</html>
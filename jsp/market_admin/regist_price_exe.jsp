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
	response.sendRedirect("/bear2Web/jsp/login.jsp?error_code=002");
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
double tmpStartPrice = Double.parseDouble( startPrice );
double tmpEndPrice = Double.parseDouble( endPrice );

// 高値、安値がない場合は始値、終値から選出
double tmpHighPrice = 0;
if (highPrice.isEmpty()) {

	if (tmpStartPrice > tmpEndPrice) {
		tmpHighPrice = tmpStartPrice;
	} else {
		tmpHighPrice = tmpEndPrice;
	}

} else {
	tmpHighPrice = Double.parseDouble( highPrice );
}

double tmpLowPrice = 0;
if (lowPrice.isEmpty()) {

	if (tmpStartPrice < tmpEndPrice) {
		tmpLowPrice = tmpStartPrice;
	} else {
		tmpLowPrice = tmpEndPrice;
	}

} else {
	tmpLowPrice = Double.parseDouble( lowPrice );
}

// 出来高がNULLの時
double tmpVolume = 0;
if (!volume.isEmpty()) {
	tmpVolume = Double.parseDouble( volume );
}


Price price = new Price();
price.setMarketCd( marketCd );
price.setRegistDate( registDate  );
price.setStartPrice( tmpStartPrice );
price.setHighPrice( tmpHighPrice );
price.setLowPrice( tmpLowPrice );
price.setEndPrice( tmpEndPrice );
price.setVolume( tmpVolume );

//=== データ登録 ===
PriceMgr pm = new PriceMgr();

try {

	pm.registPrice(price);

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
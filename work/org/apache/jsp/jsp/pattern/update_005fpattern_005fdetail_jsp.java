/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.42
 * Generated at: 2017-05-16 14:35:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.pattern;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import bear2web.util.*;
import bear2web.model.*;
import bear2web.mgr.*;

public final class update_005fpattern_005fdetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("bear2web.model");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("bear2web.util");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("bear2web.mgr");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.text.SimpleDateFormat");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=windows-31j");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

//ログイン中のメンバーのみアクセス可能
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//ログイン失敗
	//認証していません
	response.sendRedirect("/near2Web/jsp/login.jsp?error_code=002");
}

//=== 必要なデータ抽出 ===
//== コードの取得
String patternCd = request.getParameter("pattern_cd");
int seqId = Integer.parseInt( request.getParameter("seq_id") );

MarketMgr mm = new MarketMgr();
List<Market> markets = mm.getAllMarkets();

PatternMgr pm = new PatternMgr();
PatternDetail pd = pm.getPatternDetailByKey( patternCd ,seqId );


      out.write("\r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-31j\">\n");
      out.write("<title>パターン詳細新更新</title>\n");
      out.write("</head>\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<h2>パターン詳細更新</h2>\n");
      out.write("\r\n");
      out.write("<form name=\"form1\" action=\"update_pattern_detail_exe.jsp\" method=\"post\">\r\n");
      out.write("<table border=\"0\" cellspacing=\"5\">\r\n");
      out.write("\r\n");
      out.write("\t<tr valign=\"top\">\r\n");
      out.write("\t\t<!-- 入力フォーム左側 -->\r\n");
      out.write("\t\t<td width=\"500\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- 基本情報 -->\r\n");
      out.write("\t\t\t<table bgcolor=\"#9999CC\" width=\"95%\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#CCCCFF\" nowrap><font size=\"-1\"><b>パターンコード</b></font></td>\r\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#FFFFFF\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"pattern_cd\" size=\"20\" value=\"");
      out.print( patternCd );
      out.write("\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#CCCCFF\" nowrap><font size=\"-1\"><b>番号</b></font></td>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#FFFFFF\">\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"seq_id\" size=\"20\" value=");
      out.print( seqId );
      out.write(">\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#CCCCFF\" nowrap><font size=\"-1\"><b>対象マーケット<br>コード</b></font></td>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#FFFFFF\">\n");
      out.write("\t\t\t\t\t\t<select name=market_cd>\n");
      out.write("\t\t\t\t\t\t");

						String selectStr = "";
						for (Iterator<Market> it = markets.iterator(); it.hasNext();) {

							Market market = (Market)it.next();

							if (market.getMarketCd().equals( pd.getMarketCd() )) {
								selectStr = "selected";
							} else {
								selectStr = "";
							}

						
      out.write("\n");
      out.write("\t\t\t\t\t\t<option value=\"");
      out.print( market.getMarketCd() );
      out.write('"');
      out.write(' ');
      out.print( selectStr );
      out.write(' ');
      out.write('>');
      out.print( market.getMarketCd() );
      out.write(':');
      out.print( market.getMarketName() );
      out.write("\n");
      out.write("\t\t\t\t\t\t");

						}
						
      out.write("\n");
      out.write("\t\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#CCCCFF\" nowrap><font size=\"-1\"><b>何日前</b></font></td>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#FFFFFF\">\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"pre_day_cnt\" size=\"20\" value=\"");
      out.print( pd.getPreDayCnt()  );
      out.write("\" >\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#CCCCFF\" nowrap><font size=\"-1\"><b>対象マーケット<br>分析パターン</b></font></td>\n");
      out.write("\t\t\t\t\t<td bgcolor=\"#FFFFFF\">\n");
      out.write("\t\t\t\t\t\t<select name=\"pattern_atype\">\n");
      out.write("\t\t\t\t\t\t");

						//ToDo 分析パターンの追加
						// 比率など
						//
						
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<option value=\"DIR\">DIR:上げ下げ\n");
      out.write("\t\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\r\n");
      out.write("\t</tr>\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<input type=\"submit\" value=\"更新\">\r\n");
      out.write("\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("<a href=\"pattern_list.jsp\">パターン一覧へ</a>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

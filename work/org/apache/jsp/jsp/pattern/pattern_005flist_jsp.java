/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.42
 * Generated at: 2017-05-17 13:28:55 UTC
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
import bear2web.util.*;
import bear2web.model.*;
import bear2web.mgr.*;
import java.text.*;
import java.net.URLEncoder;

public final class pattern_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("java.text");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("bear2web.util");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("bear2web.mgr");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.net.URLEncoder");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

//ログイン中のメンバーのみアクセス可能
UserAccount ua = (UserAccount) session.getAttribute("account");
if (ua == null) {

	//ログイン失敗
	//認証していません
	response.sendRedirect("/bear2Web/jsp/login.jsp?error_code=002");
}

//=== パラメータ受け取り ===
request.setCharacterEncoding("Windows-31J");


      out.write("\r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\r\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-31j\">\n");
      out.write("<title>分析システム</title>\n");
      out.write("</head>\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\">\r\n");
      out.write("<tr>\r\n");
      out.write("  <td nowrap=\"nowrap\">\r\n");
      out.write("    <font size=\"4\"><b>分析パターン一覧</b></font>\r\n");
      out.write("  </td>\r\n");
      out.write("  <td width=\"100%\" align=\"right\"></td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("<a href=\"/bear2Web/jsp/analysis_menu.jsp\">TOPメニュー</a>\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");
      out.write("<br>\n");
      out.write("<a href=\"regist_pattern.jsp\"><b>新規パターン追加</b></a>\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<font>\r\n");
      out.write("<table bgcolor=\"#9999CC\" border=\"0\" cellspacing=\"2\" cellpadding=\"2\">\r\n");
      out.write("  <tr bgcolor=\"#CCCCFF\">\r\n");
      out.write("    <th width=\"50\">パターン<br>コード</th>\r\n");
      out.write("    <th width=\"100\">パターン名称</th>\r\n");
      out.write("    <th width=\"50\">対象マーケ<br>ットコード</th>\r\n");
      out.write("    <th width=\"50\">分析タイプ</th>\n");
      out.write("    <th width=\"80\">メモ</th>\r\n");
      out.write("    <th width=\"40\">&nbsp;</th>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");

//マーケット一覧表示
PatternMgr pm = new PatternMgr();
List<Pattern> ptns = pm.getAllPattern();


MarketMgr mm = new MarketMgr();
List<Market> markets = mm.getAllMarkets();

for (Iterator<Pattern> it = ptns.iterator(); it.hasNext();) {

	Pattern ptn = (Pattern)it.next();


      out.write("\r\n");
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td bgcolor=\"#FFFFFF\" >");
      out.print( ptn.getPatternCd() );
      out.write("</td>\n");
      out.write("    <td bgcolor=\"#FFFFFF\" >");
      out.print( ptn.getPatternName() );
      out.write("</td>\n");
      out.write("    <td bgcolor=\"#FFFFFF\" >");
      out.print( ptn.getTargetMarketCd() );
      out.write("</td>\n");
      out.write("    <td bgcolor=\"#FFFFFF\" >");
      out.print( ptn.getTargetAtype() );
      out.write("</td>\n");
      out.write("    <td bgcolor=\"#FFFFFF\" >\n");
      out.write("      ");
      out.print( ptn.getMemo() );
      out.write("<br><br>\n");
      out.write("      <input type=\"button\" value=\"詳細登録\"\n");
      out.write("        \t\t\tonClick=\"document.location.href='regist_pattern_detail.jsp?pattern_cd=");
      out.print( ptn.getPatternCd() );
      out.write("';\">\n");
      out.write("\n");
      out.write("      <table bgcolor=\"#9999CC\" border=\"0\" cellspacing=\"2\" cellpadding=\"2\">\n");
      out.write("        <tr>\n");
      out.write("\t\t\t<th nowrap>番号</th>\n");
      out.write("    \t\t<th nowrap>マーケットコード</th>\n");
      out.write("    \t\t<th nowrap>何日前</th>\n");
      out.write("    \t\t<th nowrap>分析タイプ</th>\n");
      out.write("    \t\t<th width=\"40\">&nbsp;</th>\n");
      out.write("    \t\t<th width=\"40\">&nbsp;</th>\n");
      out.write("\t\t</tr>\n");
      out.write("        ");

        List<PatternDetail> pds = ptn.getPatternDetails();

        for (Iterator<PatternDetail> it2 = pds.iterator(); it2.hasNext(); ) {

        	PatternDetail pd = it2.next();

        
      out.write("\n");
      out.write("\n");
      out.write("\t\t<tr>\n");
      out.write("    \t\t<td bgcolor=\"#FFFFFF\" nowrap>");
      out.print( pd.getSeqId() );
      out.write("</td>\n");
      out.write("    \t\t<td bgcolor=\"#FFFFFF\" nowrap>");
      out.print( pd.getMarketCd() );
      out.write("</td>\n");
      out.write("    \t\t<td bgcolor=\"#FFFFFF\" nowrap>");
      out.print( pd.getPreDayCnt() );
      out.write("</td>\n");
      out.write("    \t\t<td bgcolor=\"#FFFFFF\" nowrap>");
      out.print( pd.getPatternAtype() );
      out.write("</td>\n");
      out.write("    \t\t<td bgcolor=\"#FFFFFF\">\n");
      out.write("    \t\t\t<input type=\"button\" value=\"更新\"\n");
      out.write("        \t\t\tonClick=\"document.location.href='update_pattern_detail.jsp?pattern_cd=");
      out.print( ptn.getPatternCd() );
      out.write("&seq_id=");
      out.print( pd.getSeqId() );
      out.write("';\">\n");
      out.write("    \t\t</td>\n");
      out.write("    \t\t<td bgcolor=\"#FFFFFF\">\n");
      out.write("    \t\t\t<input type=\"button\" value=\"削除\"\n");
      out.write("        \t\t\tonClick=\"document.location.href='delete_pattern_detail.jsp?pattern_cd=");
      out.print( ptn.getPatternCd() );
      out.write("&seq_id=");
      out.print( pd.getSeqId() );
      out.write("';\">\n");
      out.write("    \t\t</td>\n");
      out.write("    \t</tr>\n");
      out.write("\n");
      out.write("\n");
      out.write("      \t");

        }
        
      out.write("\n");
      out.write("     </table>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </td>\n");
      out.write("    <td bgcolor=\"#FFFFFF\">\n");
      out.write("    \t<input type=\"button\" value=\"一覧\"\n");
      out.write("        \tonClick=\"document.location.href='/bear2Web/jsp/dashbord/statistics.jsp.jsp?ptn_cd=");
      out.print( ptn.getPatternCd() );
      out.write("';\">\n");
      out.write("    \t<br><br>\n");
      out.write("    \t<input type=\"button\" value=\"分布\"\n");
      out.write("        \tonClick=\"document.location.href='view_performance.jsp?pattern_cd=");
      out.print( ptn.getPatternCd() );
      out.write("';\">\n");
      out.write("    \t<br><br>\n");
      out.write("    \t<input type=\"button\" value=\"計算\"\n");
      out.write("        \tonClick=\"document.location.href='calc_data.jsp?pattern_cd=");
      out.print( ptn.getPatternCd() );
      out.write("';\">\n");
      out.write("    \t<br><br>\r\n");
      out.write("    \t<input type=\"button\" value=\"更新\"\r\n");
      out.write("        \tonClick=\"document.location.href='update_pattern.jsp?pattern_cd=");
      out.print( ptn.getPatternCd() );
      out.write("';\">\r\n");
      out.write("    \t<br><br>\n");
      out.write("    \t<input type=\"button\" value=\"削除\"\n");
      out.write("        \tonClick=\"document.location.href='delete_pattern.jsp?pattern_cd=");
      out.print( ptn.getPatternCd() );
      out.write("';\">\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");

}

      out.write("\n");
      out.write("</table>\r\n");
      out.write("</font>\r\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\r\n");
      out.write("\r\n");
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

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_set_var_value_nobody.release();
    _jspx_tagPool_c_if_test.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <title>SWP team project</title>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <!-- Bootstrap -->\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/styleindex.css\">\n");
      out.write("\n");
      out.write("        <link rel=\"shortcut icon\" href=\"images/logo.png\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"icon fronts/font-awesome-4.7.0/css/font-awesome.min.css\">\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <!---------HEADER-------->\n");
      out.write("        <header>\n");
      out.write("            <div class=\"logo\">\n");
      out.write("                <img  src=\"images/logo-circle.png\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"menu\">\n");
      out.write("                <li><a href=\"\">Male</a>\n");
      out.write("                    <ul class=\"sub-menu\">\n");
      out.write("                        <li><a href=\"\">New products</a></li>\n");
      out.write("                        <li><a href=\"\">Collection</a></li>\n");
      out.write("                        <li><a href=\"\">Men's shirt</a>\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"\">Shirt</a></li>\n");
      out.write("                                <li><a href=\"\">T-shirt</a></li>\n");
      out.write("                                <li><a href=\"\">Vest</a></li>\n");
      out.write("                                <li><a href=\"\">Sweater</a></li>\n");
      out.write("                                <li><a href=\"\">Coat</a></li>\n");
      out.write("                            </ul>\t\t\t\t\t\n");
      out.write("                        </li>\n");
      out.write("                        <li><a href=\"\">Men's pants</a>\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"\">Jeans</a></li>\n");
      out.write("                                <li><a href=\"\">Short pant</a></li>\n");
      out.write("                                <li><a href=\"\">Trouser</a></li>\n");
      out.write("                            </ul>\t\t\t\t\t\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("\n");
      out.write("                </li>\n");
      out.write("                <li><a href=\"\">Female</a></li>\n");
      out.write("                <li><a href=\"\">Children</a></li>\n");
      out.write("                <li><a href=\"\">Sale</a></li>\n");
      out.write("                <li><a href=\"\">Collection</a></li>\n");
      out.write("                <li><a href=\"\">Information</a></li>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"orther\">\n");
      out.write("                <li><input placeholder=\"Search\" type=\"text\"><i class=\"fa fa-search\"></i></li>\n");
      out.write("                <li><a class=\"fa fa-user\" href=\"login.html\"></a></li>\n");
      out.write("                <li>\n");
      out.write("                    <form action=\"viewCartPage\" method=\"POST\">\n");
      out.write("                        <input type=\"submit\" value=\"view_your_cart\" name=\"btAction\" />\n");
      out.write("                    </form>\n");
      out.write("                </li>\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        <!---------Banner-slider-------->\n");
      out.write("        <section id=\"slider\">\n");
      out.write("            <div class=\"aspect-ratio-169\">\n");
      out.write("                <img src=\"images/banner1.jpg\">\n");
      out.write("                <img src=\"images/banner2.jpg\">\n");
      out.write("                <img src=\"images/banner3.jpg\">\n");
      out.write("                <img src=\"images/banner4.jpg\">\n");
      out.write("                <img src=\"images/banner5.jpg\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"dot-container\">\n");
      out.write("                <div class=\"dot active\"></div>\n");
      out.write("                <div class=\"dot\"></div>\n");
      out.write("                <div class=\"dot\"></div>\n");
      out.write("                <div class=\"dot\"></div>\n");
      out.write("                <div class=\"dot\"></div>\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("        <!---------Item-------->\n");
      out.write("\n");
      out.write("\n");
      out.write("        <section class=\"cartegory\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"cartegory-left\">\n");
      out.write("                        <ul>\n");
      out.write("                            <li class=\"cartegory-left-li\"><a href=\"#\">Female</a>\n");
      out.write("                                <ul>\n");
      out.write("                                    <li><a href=\"\">New arrivals women's goods</a></li>\n");
      out.write("                                    <li><a href=\"\">Female jeans</a></li>\n");
      out.write("                                    <li><a href=\"\">Jeans for joy</a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"cartegory-left-li\"><a href=\"#\">Male</a>\n");
      out.write("                                <ul>\n");
      out.write("                                    <li><a href=\"\">New arrivals men's goods</a></li>\n");
      out.write("                                    <li><a href=\"\">Male jeans</a></li>\n");
      out.write("                                    <li><a href=\"\">Jeans for joy</a></li>\n");
      out.write("                                </ul>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"cartegory-left-li\"><a href=\"\">Children</a></li>\n");
      out.write("                            <li class=\"cartegory-left-li\"><a href=\"\">Sale</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"cartegory-right row\">\n");
      out.write("                        <div class=\"cartegory-right-top-item\">\n");
      out.write("                            <p>New products</p>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"cartegory-right-top-item\">\n");
      out.write("                            <button><span>Filter</span><i class=\"fa fa-sort-down\"></i></button>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"cartegory-right-top-item\">\n");
      out.write("                            <select name=\"\" id=\"\">\n");
      out.write("                                <option value=\"\">Arrange</option>\n");
      out.write("                                <option value=\"\">High to low price</option>\n");
      out.write("                                <option value=\"\">Low to high price</option>\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        ");
      if (_jspx_meth_c_set_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                        ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                        ");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("                        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        ");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"cartegory-right-bottom row\">\n");
      out.write("                        <div class=\"cartegory-right-bottom-item\">\n");
      out.write("                            <p>Display 2<span>|</span>4 product</p>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"cartegory-right-bottom-item2\">\n");
      out.write("                            <p><span>&#171;<span>1 2 3 4 5</span>&#187;</span>Last page</p>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\t\t\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </section>\n");
      out.write("    <!---------Footer-------->\n");
      out.write("    <footer>\n");
      out.write("        <div class=\"footer-top\">\n");
      out.write("            <li><a href=\"\">Contact</a></li>\n");
      out.write("            <li><a href=\"\">Recruit</a></li>\n");
      out.write("            <li><a href=\"\">Introduce</a></li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"\" class=\"fa fa-facebook\"></a>\n");
      out.write("                <a href=\"\" class=\"fa fa-twitter\"></a>\n");
      out.write("                <a href=\"\" class=\"fa fa-youtube\"></a>\n");
      out.write("            </li>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"footer-center\">\n");
      out.write("            <p>\n");
      out.write("                Contact phone number: 0111111111 <br>\n");
      out.write("                Registration address: ??????????? <br>\n");
      out.write("                Order online: <b>022222222</b>\n");
      out.write("            </p>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"footer-bottom\">\n");
      out.write("            ©IVYmoda All rights reserved\n");
      out.write("        </div>\n");
      out.write("    </footer>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("<script>\n");
      out.write("    //---------------------sticky-header---------------\n");
      out.write("    const header = document.querySelector(\"header\")\n");
      out.write("    window.addEventListener(\"scroll\", function () {\n");
      out.write("        x = window.pageYOffset\n");
      out.write("        if (x > 0) {\n");
      out.write("            header.classList.add(\"sticky\")\n");
      out.write("        } else {\n");
      out.write("            header.classList.remove(\"sticky\")\n");
      out.write("        }\n");
      out.write("        //console.log(x)\n");
      out.write("    })\n");
      out.write("\n");
      out.write("    //---------------------sliderbanner-dotcontroller---------------\n");
      out.write("    const imgPosition = document.querySelectorAll(\".aspect-ratio-169 img\")\n");
      out.write("    const imgContainer = document.querySelector('.aspect-ratio-169')\n");
      out.write("    const dotItem = document.querySelectorAll(\".dot\")\n");
      out.write("    let imgNumber = imgPosition.length\n");
      out.write("    let index = 0\n");
      out.write("    //console.log(imgPosition)\n");
      out.write("    imgPosition.forEach(function (images, index) {\n");
      out.write("        images.style.left = index * 100 + \"%\"\n");
      out.write("        dotItem[index].addEventListener(\"click\", function () {\n");
      out.write("            slider(index)\n");
      out.write("        })\n");
      out.write("    })\n");
      out.write("    function imgSlide() {\n");
      out.write("        index++;\n");
      out.write("        console.log(index)\n");
      out.write("        if (index >= imgNumber) {\n");
      out.write("            index = 0\n");
      out.write("        }\n");
      out.write("        slider(index)\n");
      out.write("    }\n");
      out.write("    function slider(index) {\n");
      out.write("        imgContainer.style.left = \"-\" + index * 100 + \"%\"\n");
      out.write("        const dotActive = document.querySelector('.active')\n");
      out.write("        dotActive.classList.remove(\"active\")\n");
      out.write("        dotItem[index].classList.add(\"active\")\n");
      out.write("    }\n");
      out.write("    setInterval(imgSlide, 5000)\n");
      out.write("</script>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_set_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(_jspx_page_context);
    _jspx_th_c_set_0.setParent(null);
    _jspx_th_c_set_0.setVar("result");
    _jspx_th_c_set_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.ITEMS_RESULT}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
    return false;
  }

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty result}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \n");
        out.write("                            <div class=\"cartegory-right-content row\">\n");
        out.write("                                ");
        if (_jspx_meth_c_forEach_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return true;
        out.write("                                \n");
        out.write("                            </div>\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_forEach_0.setVar("dto");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${result}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                    <form action=\"addToCartController\" method=\"POST\">             \n");
          out.write("                                        <form action=\"showDetailController\" method=\"POST\">             \n");
          out.write("\n");
          out.write("                                            <div class=\"cartegory-right-content-item\">\n");
          out.write("                                                \n");
          out.write("                                                <a href=\"showDetailProduct.jsp\"> <img src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.image}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"></a>\n");
          out.write("                                                <input  type=\"hidden\" href=\"showDetailProduct.jsp\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" ></input>\n");
          out.write("                                                <h1><a type=\"\" href=\"showDetailProduct.jsp\" >");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</a>\n");
          out.write("                                                        <input type=\"hidden\" name=\"txtProductName\" \n");
          out.write("                                                               value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"/>        \n");
          out.write("                                                    </h1>\n");
          out.write("                                                \n");
          out.write("                                                <p>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</p>\n");
          out.write("                                                <input type=\"text\" name=\"txtQuantity\" value=\"1\" />\n");
          out.write("                                                <input type=\"submit\" value=\"AddToCart\"/> \n");
          out.write("                                            </div>\n");
          out.write("                                        </form>\n");
          out.write("                                    </form>\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ empty result}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("  \n");
        out.write("                            Dang lam sai\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }
}

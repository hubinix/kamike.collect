/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch;

import cn.exinhua.fetch.entity.db.ContentCreator;
import cn.exinhua.fetch.entity.db.DescriptionCreator;
import cn.exinhua.fetch.entity.db.FetchCreator;
import cn.exinhua.fetch.entity.db.LinkCreator;
import com.kamike.config.SystemConfig;
import com.kamike.db.Transaction;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Brin
 */
@WebServlet(name = "InstallServlet", urlPatterns = {"/install"})
public class InstallServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!InstallUtils.existDatabase(SystemConfig.DB_NAME)) {
            Transaction ts = new Transaction();
            boolean ret = InstallUtils.createDatabase(ts, SystemConfig.DB_NAME);
            ts.save();
        }

        if (!InstallUtils.existTable("fetch_content")) {
            Transaction ts = new Transaction();
            ContentCreator creator = new ContentCreator(ts);
            boolean ret = creator.createTable();
            ts.save();
        }

        if (!InstallUtils.existTable("fetch_fetch")) {
            Transaction ts = new Transaction();
            FetchCreator creator = new FetchCreator(ts);
            boolean ret = creator.createTable();
            ts.save();
        }
        if (!InstallUtils.existTable("fetch_description")) {
            Transaction ts = new Transaction();
            DescriptionCreator creator = new DescriptionCreator(ts);
            boolean ret = creator.createTable();
            ts.save();
        }
        if (!InstallUtils.existTable("fetch_link")) {
            Transaction ts = new Transaction();
            LinkCreator creator = new LinkCreator(ts);
            boolean ret = creator.createTable();
            ts.save();
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>安装服务</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>数据库安装成功！</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

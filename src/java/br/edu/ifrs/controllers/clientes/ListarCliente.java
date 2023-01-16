/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controllers.clientes;

import br.edu.ifrs.entities.Cliente;
import br.edu.ifrs.repository.ClientesRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SAMSUNG
 */
@WebServlet(name = "ListarClientes", urlPatterns = {"/ListarClientes"})
public class ListarCliente extends HttpServlet {
    
    private ClientesRepository clientesRepository;
    
    public ListarCliente() {
        this.clientesRepository = new ClientesRepository();
    }

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListarCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Listar Clientes</h1>");
            out.println("<hr>");
            
            out.println("<a href=\"index.html\">Voltar ao Menu</a>");
            out.println("<a href=\"FormCliente\">Inserir Novo Cliente</a><br><br>");
            
            out.println("<table border=\"1\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>CPF</th>");
            out.println("<th>Nome</th>");
            out.println("<th>Ações</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
                                    
            Cliente[] clientes = clientesRepository.findAll();
            
            for (Cliente cliente: clientes) {
                out.println("<tr>");
                    out.println("<td>"+ cliente.getCpf() +"</td>");
                    out.println("<td>"+ cliente.getNome() +"</td>");
                    out.println(
                        "<td>" +
                            String.format("[<a href=\"FormCliente?id=" + cliente.getId() + "\">Editar</a>]") +
                            String.format("[<a href=\"ExcluirCliente?id=" + cliente.getId() + "\">Excluir</a>]") +
                        "</td>");                    
                out.println("</tr>");
            }
            
            out.println("</tbody>");
            out.println("</table>");
            
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

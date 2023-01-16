/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.edu.ifrs.controllers.clientes;

import br.edu.ifrs.entities.Cliente;
import br.edu.ifrs.repository.ClientesRepository;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mathe
 */
@WebServlet(name = "EditarCliente", urlPatterns = {"/EditarCliente"})
public class EditarCliente extends HttpServlet {

    private ClientesRepository clientesRepository;
    
    public EditarCliente() {
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
            out.println("<title>Edição de Cliente</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Edição de Cliente</h1>");
            out.println("<hr>");
            
            long formId = Long.parseLong(request.getParameter("id"));
            String formCpf = request.getParameter("cpf");
            String formNome = request.getParameter("nome");
            
            Cliente cliente = clientesRepository.findById(formId);
            
            cliente.setNome(formNome);
            cliente.setCpf(formCpf);
            
            clientesRepository.update(cliente);
            
            out.printf("<p>Cliente %s editado com sucesso!!!</p>", cliente.getNome());
            out.println("<a href=\"ListarClientes\">Listar Clientes</a>");
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

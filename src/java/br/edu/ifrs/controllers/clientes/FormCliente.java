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
@WebServlet(name = "FormCliente", urlPatterns = {"/FormCliente"})
public class FormCliente extends HttpServlet {
    
    private ClientesRepository clientesRepository;
    
    public FormCliente() {
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
            
            boolean editando = (request.getParameter("id") != null);
            long formId = editando ? Long.parseLong(request.getParameter("id")) : 0;
            String formCpf = null;
            String formNome = null;
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            if (editando) {
                out.println("<title>Edição de Cliente</title>");
            } else {
                out.println("<title>Cadastro de Cliente</title>");
            }
            out.println("</head>");
            out.println("<body>");
            
            if (editando) {
                out.println("<h1>Edição de Cliente</h1>");
            } else {
                out.println("<h1>Cadastro de Cliente</h1>");
            }
            
            out.println("<hr>");
            
            if (editando) {
                Cliente cliente = this.clientesRepository.findById(formId);
                formCpf = cliente.getCpf();
                formNome = cliente.getNome();
                
                out.println("<form action=\"EditarCliente\">");  
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + formId + "\" />");
            } else {
                out.println("<form action=\"CadastrarCliente\">");            
            }

            out.println("<label for=\"nome\">Nome Completo:</label>");
            out.println("<input type=\"text\" name=\"nome\" id=\"nome\" size=\"45\" maxlength=\"45\" " + (formNome != null ? "value=\"" + formNome + "\"" : "") + " required><br><br>");

            out.println("<label for=\"cpf\">CPF:</label><br>");
            out.println("<input type=\"text\" name=\"cpf\" id=\"cpf\" required pattern=\"[0-9]{11}\" title=\"CPF não formatado (somente numero)\" " + (formCpf != null ? "value=\"" + formCpf + "\"" : "") + "><br><br>");

            out.println("<input type=\"submit\" value=\"Salvar\">");
            out.println("<input type=\"reset\" value=\"Limpar\">");
            out.println("</form><br>");

            out.println("<a href=\"ListarClientes\">Voltar para Lista de Cliente</a>");            
            
            
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

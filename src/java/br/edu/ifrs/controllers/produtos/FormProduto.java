/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.edu.ifrs.controllers.produtos;

import br.edu.ifrs.entities.Produto;
import br.edu.ifrs.repository.ProdutosRepository;
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
@WebServlet(name = "FormProduto", urlPatterns = {"/FormProduto"})
public class FormProduto extends HttpServlet {
    private ProdutosRepository produtosRepository;
    
    public FormProduto() {
        this.produtosRepository = new ProdutosRepository();
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
            
            boolean editando = (request.getParameter("codigo_identificador") != null);
            long formCodigoIdentificador = editando ? Long.parseLong(request.getParameter("codigo_identificador")) : 0;
            String formNome = null;
            String formDescricao = null;
            float formPreco = 0.0f;
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            if (editando) {
                out.println("<title>Edição de Produto</title>");
            } else {
                out.println("<title>Cadastro de Produto</title>");
            }
            out.println("</head>");
            out.println("<body>");
            
            if (editando) {
                out.println("<h1>Edição de Produto</h1>");
            } else {
                out.println("<h1>Cadastro de Produto</h1>");
            }
            
            out.println("<hr>");
            
            if (editando) {
                Produto produto = produtosRepository.findByCodigoIdentificador(formCodigoIdentificador);
                formNome = produto.getNome();
                formDescricao = produto.getDescricaoCompleta();
                formPreco = produto.getPrecoUnitario();                
                
                out.println("<form action=\"EditarProduto\">");  
                out.println("<input type=\"hidden\" name=\"codigo_identificador\" value=\"" + formCodigoIdentificador + "\" />");
            } else {
                out.println("<form action=\"CadastrarProduto\">");            
            }

            out.println("<label for=\"nome\">Nome:</label>");
            out.println("<input type=\"text\" name=\"nome\" id=\"nome\" size=\"45\" maxlength=\"45\" " + (formNome != null ? "value=\"" + formNome + "\"" : "") + " required><br><br>");

            out.println("<label for=\"cpf\">Descrição:</label><br>");
            out.println("<textarea name=\"descricao\" id=\"descricao\" rows=\"5\" cols=\"150\">" + (formDescricao != null ? formDescricao : "") + "</textarea><br><br>");

            out.println("<label for=\"preco\">Preço unitário:</label>");
            out.println("<input type=\"number\" name=\"preco\" id=\"preco\" step=\"0.01\" " + (formPreco != 0 ? "value=\"" + formPreco + "\"" : "") + " /><br><br>");
            
            out.println("<input type=\"submit\" value=\"Salvar\">");
            out.println("<input type=\"reset\" value=\"Limpar\">");
            out.println("</form><br>");

            out.println("<a href=\"ListarProduto\">Voltar para Lista de Produto</a>");            
            

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author SAMSUNG
 */
@WebServlet(name = "ListarProduto", urlPatterns = {"/ListarProduto"})
public class ListarProduto extends HttpServlet {
    private ProdutosRepository produtosRepository;
    
    public ListarProduto() {
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListarProduto</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Listar Produtos</h1>");
            out.println("<hr>");
            
            out.println("<a href=\"index.html\">Voltar ao Menu</a>");
            out.println("<a href=\"FormProduto\">Inserir Novo Produto</a><br><br>");
            
            out.println("<table border=\"1\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Código Identificador</th>");
            out.println("<th>Nome</th>");
            out.println("<th>Descrição</th>");
            out.println("<th>Preço</th>");
            out.println("<th>Ações</th>");            
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
            Produto[] produtos = produtosRepository.findAll();
            
            for (Produto produto : produtos) {
                out.println("<tr>");
                    out.println("<td>"+ produto.getCodigoIdentificador() +"</td>");
                    out.println("<td>"+ produto.getNome() +"</td>");
                    out.println("<td>"+ produto.getDescricaoCompleta() +"</td>");
                    out.println("<td>"+ produto.getPrecoUnitario() +"</td>");
                    out.println(
                        "<td>" +
                            String.format("[<a href=\"FormProduto?codigo_identificador=" + produto.getCodigoIdentificador()+ "\">Editar</a>]") +
                            String.format("[<a href=\"ExcluirProduto?codigo_identificador=" + produto.getCodigoIdentificador() + "\">Excluir</a>]") +
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

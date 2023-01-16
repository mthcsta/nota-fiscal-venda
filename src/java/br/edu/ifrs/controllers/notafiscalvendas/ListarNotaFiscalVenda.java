/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controllers.notafiscalvendas;

import br.edu.ifrs.entities.NotaFiscalVenda;
import br.edu.ifrs.repository.NotaFiscalVendasRepository;
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
@WebServlet(name = "ListarNotaFiscalVenda", urlPatterns = {"/ListarNotaFiscalVenda"})
public class ListarNotaFiscalVenda extends HttpServlet {

    private NotaFiscalVendasRepository notaFiscalVendasRepository;
    
    public ListarNotaFiscalVenda() {
        notaFiscalVendasRepository = new NotaFiscalVendasRepository();        
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
            
            out.println("<h1>Listar Nota Fiscal de Venda</h1>");
            out.println("<hr>");
            
            out.println("<a href=\"index.html\">Voltar ao Menu</a>");
            out.println("<a href=\"FormNotaFiscalVenda\">Inserir Nova nota fiscal de venda</a><br><br>");
            
            out.println("<table border=\"1\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Emissão</th>");
            out.println("<th>Expedição</th>");
            out.println("<th>Cliente</th>");
            out.println("<th>Preço Total</th>");
            out.println("<th>Produtos</th>");
            out.println("<th>Ações</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
            NotaFiscalVenda[] notaFiscalVendas = notaFiscalVendasRepository.findAll();
            
            for (NotaFiscalVenda notaFiscalVenda : notaFiscalVendas) {
                out.println("<tr>");
                out.println("<td>"+ notaFiscalVenda.getEmissao() +"</td>");
                out.println("<td>"+ notaFiscalVenda.getExpedicao() +"</td>");
                out.println("<td>"+ notaFiscalVenda.getClienteIdentificacao() +"</td>");
                out.println("<td>" + notaFiscalVenda.getPrecoTotal() + "</td>");
                out.println("<td>" + notaFiscalVenda.getProdutosString()+ "</td>");
                out.println(
                    "<td>" +
                        String.format("[<a href=\"FormNotaFiscalVenda?numero_da_nota=" + notaFiscalVenda.getNumeroDaNota() + "\">Editar</a>]") +
                        String.format("[<a href=\"ExcluirNotaFiscalVenda?numero_da_nota=" + notaFiscalVenda.getNumeroDaNota() + "\">Excluir</a>]") +
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

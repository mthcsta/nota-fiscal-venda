/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controllers.notafiscalvendas;

import br.edu.ifrs.entities.Cliente;
import br.edu.ifrs.entities.NotaFiscalVenda;
import br.edu.ifrs.entities.NotaFiscalVendaProduto;
import br.edu.ifrs.repository.ClientesRepository;
import br.edu.ifrs.repository.NotaFiscalVendaProdutosRepository;
import br.edu.ifrs.repository.NotaFiscalVendasRepository;
import br.edu.ifrs.repository.ProdutosRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SAMSUNG
 */
@WebServlet(name = "CadastrarNotaFiscalVenda", urlPatterns = {"/CadastrarNotaFiscalVenda"})
public class CadastrarNotaFiscalVenda extends HttpServlet {
    
    private ClientesRepository clientesRepository;
    private ProdutosRepository produtosRepository;
    private NotaFiscalVendasRepository notaFiscalVendasRepository;
    private NotaFiscalVendaProdutosRepository notaFiscalVendaProdutosRepository;
    
    public CadastrarNotaFiscalVenda() {
        clientesRepository = new ClientesRepository();
        produtosRepository = new ProdutosRepository();
        notaFiscalVendasRepository = new NotaFiscalVendasRepository();
        notaFiscalVendaProdutosRepository = new NotaFiscalVendaProdutosRepository();
    }
    
    protected void inserirProdutos(String[] ids, String[] quantidades, long numeroDaNota) {
        long codigoIdentificador = 0;
        int unidadesVendidas = 1;
        List<NotaFiscalVendaProduto> notaFiscalVendaProdutos = new ArrayList<>();
        
        try {
            for (int indice = 0; indice < ids.length && indice < quantidades.length; indice++) {
                codigoIdentificador = Long.parseLong(ids[indice]);
                unidadesVendidas = Integer.parseInt(quantidades[indice]);
                NotaFiscalVendaProduto notaFiscalVendaProduto = new NotaFiscalVendaProduto(numeroDaNota, unidadesVendidas, codigoIdentificador);
                notaFiscalVendaProdutos.add(notaFiscalVendaProduto);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        notaFiscalVendaProdutosRepository.insertAll(notaFiscalVendaProdutos);
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
            out.println("<title>Cadastro de Nota fiscal de venda</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Cadastro de Nota fiscal de venda</h1>");
            out.println("<hr>");
                        
            NotaFiscalVenda notaFiscalVenda = new NotaFiscalVenda(request.getParameter("emissao"), request.getParameter("expedicao"), Long.parseLong(request.getParameter("cliente")));
            String[] produtoIds = request.getParameterValues("produto_id[]");
            String[] produtoQuantidades = request.getParameterValues("produto_quantidade[]");
            
            if (produtoIds == null || produtoQuantidades == null) {
                out.println("Ops... produtos não passado.");
                return;
            }
                        
            notaFiscalVenda = notaFiscalVendasRepository.insert(notaFiscalVenda);
            this.inserirProdutos(produtoIds, produtoQuantidades, notaFiscalVenda.getNumeroDaNota());
            
            out.println("<p>Nota fiscal de venda salvo com sucesso!!!</p>");
            out.println("<a href=\"FormNotaFiscalVenda\">Inserir Nova nota fiscal de venda</a>");
            
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

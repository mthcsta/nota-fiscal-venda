/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.controllers.notafiscalvendas;

import br.edu.ifrs.entities.Cliente;
import br.edu.ifrs.entities.NotaFiscalVenda;
import br.edu.ifrs.entities.NotaFiscalVendaProduto;
import br.edu.ifrs.entities.Produto;
import br.edu.ifrs.repository.ClientesRepository;
import br.edu.ifrs.repository.NotaFiscalVendaProdutosRepository;
import br.edu.ifrs.repository.NotaFiscalVendasRepository;
import br.edu.ifrs.repository.ProdutosRepository;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FormNotaFiscalVenda", urlPatterns = {"/FormNotaFiscalVenda"})
public class FormNotaFiscalVenda extends HttpServlet {
    
    private ClientesRepository clientesRepository;
    private ProdutosRepository produtosRepository;
    private NotaFiscalVendasRepository notaFiscalVendasRepository;
    private NotaFiscalVendaProdutosRepository notaFiscalVendaProdutosRepository;
    
    public FormNotaFiscalVenda() {
        clientesRepository = new ClientesRepository();
        produtosRepository = new ProdutosRepository();
        notaFiscalVendasRepository = new NotaFiscalVendasRepository();
        notaFiscalVendaProdutosRepository = new NotaFiscalVendaProdutosRepository();
    }
    
    protected void listarProdutos(PrintWriter out, List<NotaFiscalVendaProduto> formProdutos) {
        Produto[] produtos = produtosRepository.findAll();
        out.println("<label for=\"produto\">Produto:</label><br>");
        for (Produto produto : produtos) {
            out.println(String.format(
                "<div>" +
                    "<input type=\"checkbox\" id=\"produto-%d\" data-index=\"%d\" name=\"produto_id[]\" class=\"produtos\" value=\"%d\" />" +
                    "<label for=\"produto-%d\">%s [R$ %.2f]</label>" +
                    "<input type=\"number\" id=\"produto-quantidade-%d\" name=\"produto_quantidade[]\" placeholder=\"Quantidade\" disabled hidden />" + 
                "</div>", 
                produto.getCodigoIdentificador(),
                produto.getCodigoIdentificador(),
                produto.getCodigoIdentificador(),
                produto.getCodigoIdentificador(),
                produto.getNome(),
                produto.getPrecoUnitario(),
                produto.getCodigoIdentificador()
            ));            
        }
        out.println("<br><br>");
        out.println("<script>");
        out.println(
            "const produtos = document.querySelectorAll('.produtos');" +
            "produtos.forEach((produto) => {" +
                "const produtoQuantidade = document.querySelector('#produto-quantidade-' + produto.dataset.index);" +
                "produto.addEventListener('click', () => {" + 
                    "if (produtoQuantidade.hasAttribute('hidden')) { " +
                        "produtoQuantidade.removeAttribute('hidden'), produtoQuantidade.removeAttribute('disabled');" + 
                    "} else {" +
                        "produtoQuantidade.setAttribute('hidden', true), produtoQuantidade.setAttribute('disabled', true);" +
                    "}" +
                "}); " +
            "});"
        );
        for (NotaFiscalVendaProduto formProduto : formProdutos) {
            out.println(String.format(
                "document.querySelector('#produto-%d').click();" +
                "document.querySelector('#produto-quantidade-%d').value = %d;",
            formProduto.getProdutoCodigoIdentificador(),
            formProduto.getProdutoCodigoIdentificador(),
            formProduto.getUnidadesVendidas()
            ));
        }
        out.println("</script>");
    }
    
    protected void listarClientes(PrintWriter out, long formCliente) {
        Cliente[] clientes = clientesRepository.findAll();
        out.println("<label for=\"cliente\">Cliente:</label><br>");
        out.println("<select name=\"cliente\">");
        for (Cliente cliente : clientes) {
            out.println(String.format(
                "<option value=\"%d\" " + (formCliente == cliente.getId() ? "selected" : "") + " >%s (%s)</option>", 
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf()
            ));
        }
        out.println("</select><br><br>");                  
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
            
            boolean editando = (request.getParameter("numero_da_nota") != null);
            long formNumeroDaNota = editando ? Long.parseLong(request.getParameter("numero_da_nota")) : 0;
            String formEmissao = null;
            String formExpedicao = null;
            long formCliente = 0;
            List<NotaFiscalVendaProduto> formProdutos = new ArrayList<>();
                        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            if (editando) {
                out.println("<title>Edição de Nota fiscal de venda</title>");
            } else {
                out.println("<title>Cadastro de Nota fiscal de venda</title>");
            }
            out.println("</head>");
            out.println("<body>");
            
            if (editando) {
                out.println("<h1>Edição de Nota Fiscal de venda</h1>");
            } else {
                out.println("<h1>Cadastro de Nota Fiscal de venda</h1>");
            }
            
            out.println("<hr>");
                        
            if (editando) {
                NotaFiscalVenda notaFiscalVenda = notaFiscalVendasRepository.findByNumeroDaNota(formNumeroDaNota);
                if (notaFiscalVenda == null) {
                    out.println("Nota fiscal de venda inexistente");
                    return;
                }
                formEmissao = notaFiscalVenda.getEmissao();
                formExpedicao = notaFiscalVenda.getExpedicao();
                formCliente = notaFiscalVenda.getClienteId();
                formProdutos = notaFiscalVenda.getProdutos();
                
                out.println("<form action=\"EditarNotaFiscalVenda\">");
                out.println("<input type=\"hidden\" name=\"numero_da_nota\" value=\"" + formNumeroDaNota + "\" />");                
            } else {
                out.println("<form action=\"CadastrarNotaFiscalVenda\">");
            }
            out.println("<label for=\"emissao\">Emissão:</label><br>" +
                "<input type=\"date\" name=\"emissao\" id=\"emissao\" " + (formEmissao != null ? "value=\"" + formEmissao + "\"" : "") + " required /><br><br>" +
                "<label for=\"expedicao\">Expedição:</label><br>" +
                "<input type=\"date\" name=\"expedicao\" id=\"expedicao\" " + (formExpedicao != null ? "value=\"" + formExpedicao + "\"" : "") + " required /><br><br>"
            );

            listarClientes(out, formCliente);
            listarProdutos(out, formProdutos);            
            
            /*
            DBConnect dbCon = new DBConnect();
            ResultSet rs = null;
            if (editando) {
                try {
                    dbCon.prepare("select emissao, expedicao, Clientes_id from notafiscalvenda where numero_da_nota = ?");
                    dbCon.getPreparedStatement().setInt(1, Integer.parseInt(request.getParameter("numero_da_nota")));
                    rs = dbCon.getPreparedStatement().executeQuery();
                    rs.next();
                    formEmissao = rs.getString("emissao");
                    formExpedicao = rs.getString("expedicao");
                    formCliente = rs.getInt("Clientes_id");
                } catch (SQLException e) {
                    out.println("Ops... registro não encontrado");
                    out.println();
                    return;
                }
                out.println("<form action=\"EditarNotaFiscalVenda\">");
                out.println("<input type=\"hidden\" name=\"numero_da_nota\" value=\"" + request.getParameter("numero_da_nota") + "\" />");
            } else {
                out.println("<form action=\"CadastrarNotaFiscalVenda\">");
            }
            out.println("<label for=\"emissao\">Emissão:</label><br>" +
                "<input type=\"date\" name=\"emissao\" id=\"emissao\" " + (formEmissao != null ? "value=\"" + formEmissao + "\"" : "") + " required /><br><br>" +
                "<label for=\"expedicao\">Expedição:</label><br>" +
                "<input type=\"date\" name=\"expedicao\" id=\"expedicao\" " + (formExpedicao != null ? "value=\"" + formExpedicao + "\"" : "") + " required /><br><br>"
            );
            
            try {
                dbCon.prepare("select id, cpf, nome from clientes");               
                rs = dbCon.getPreparedStatement().executeQuery();
                out.println("<label for=\"cliente\">Cliente:</label><br>");
                out.println("<select name=\"cliente\">");
                while (rs.next()) {
                    out.println(String.format(
                        "<option value=\"%d\" " + (formCliente == rs.getInt("id") ? "selected" : "") + " >%s (%s)</option>", 
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf")
                    ));
                }
                out.println("</select><br><br>");
                
                                
            } catch (SQLException ex) {
                Logger.getLogger(FormNotaFiscalVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                dbCon.prepare("select codigo_identificador, nome, preco_unitario from produtos");
                rs = dbCon.getPreparedStatement().executeQuery();
                out.println("<label for=\"produto\">Produto:</label><br>");
                while (rs.next()) {
                    out.println(String.format(
                        "<div>" +
                            "<input type=\"checkbox\" id=\"produto-%d\" data-index=\"%d\" name=\"produto_id[]\" class=\"produtos\" value=\"%d\" />" +
                            "<label for=\"produto-%d\">%s [R$ %.2f]</label>" +
                            "<input type=\"number\" id=\"produto-quantidade-%d\" name=\"produto_quantidade[]\" placeholder=\"Quantidade\" disabled hidden />" + 
                        "</div>", 
                        rs.getInt("codigo_identificador"),
                        rs.getInt("codigo_identificador"),
                        rs.getInt("codigo_identificador"),
                        rs.getInt("codigo_identificador"),
                        rs.getString("nome"),
                        rs.getDouble("preco_unitario"),
                        rs.getInt("codigo_identificador")
                    ));
                }
                out.println("<br><br>");
                                
            } catch (SQLException ex) {
                Logger.getLogger(FormNotaFiscalVenda.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            try {
                if (rs != null) {
                    rs.close();
                }
                dbCon.close();                
            } catch (Exception e) {
                
            }
            
            out.println(
                    "<script>" + 
                            "const produtos = document.querySelectorAll('.produtos');" +
                            "produtos.forEach((produto) => {" +
                                "const produtoQuantidade = document.querySelector('#produto-quantidade-' + produto.dataset.index);" +
                                "produto.addEventListener('click', () => {" + 
                                    "if (produtoQuantidade.hasAttribute('hidden')) { " +
                                        "produtoQuantidade.removeAttribute('hidden'), produtoQuantidade.removeAttribute('disabled');" + 
                                    "} else {" +
                                        "produtoQuantidade.setAttribute('hidden', true), produtoQuantidade.setAttribute('disabled', true);" +
                                    "}" +
                                "}); " +
                            "});" +
                    "</script>");
            */
            out.println(
                "<input type=\"submit\" value=\"Salvar\">" +
                "<input type=\"reset\" value=\"Limpar\">" +
                "</form><br>" +
                "<a href=\"ListarNotaFiscalVenda\">Voltar para Lista de Nota fiscal de venda</a>" +
                "</body>" + 
                "</html>"
            );
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

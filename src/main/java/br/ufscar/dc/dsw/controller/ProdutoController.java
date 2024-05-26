package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.FornecedorDAO;
import br.ufscar.dc.dsw.dao.ProdutoDAO;
import br.ufscar.dc.dsw.domain.Fornecedor;
import br.ufscar.dc.dsw.domain.Produto;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/produtos/*")
public class ProdutoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProdutoDAO dao;

    @Override
    public void init() {
        dao = new ProdutoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("ADMIN")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}
		
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> listaProdutos = dao.getAll();
        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/produto/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getFornecedores() {
        Map<Long, String> fornecedores = new HashMap<>();
        for (Fornecedor fornecedor : new FornecedorDAO().getAll()) {
            fornecedores.put(fornecedor.getId(), fornecedor.getNome());
        }
        return fornecedores;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("fornecedores", getFornecedores());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/produto/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Produto produto = dao.get(id);
        request.setAttribute("produto", produto);
        request.setAttribute("fornecedores", getFornecedores());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/produto/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
        Float preco = Float.parseFloat(request.getParameter("preco"));

        Long fornecedorID = Long.parseLong(request.getParameter("fornecedor"));
        Fornecedor fornecedor = new FornecedorDAO().get(fornecedorID);

        Produto produto = new Produto(titulo, categoria, quantidade, preco, fornecedor);
        dao.insert(produto);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
        Float preco = Float.parseFloat(request.getParameter("preco"));

        Long fornecedorID = Long.parseLong(request.getParameter("fornecedor"));
        Fornecedor fornecedor = new FornecedorDAO().get(fornecedorID);

        Produto produto = new Produto(id, titulo, categoria, quantidade, preco, fornecedor);
        dao.update(produto);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Produto produto = new Produto(id);
        dao.delete(produto);
        response.sendRedirect("lista");
    }
}
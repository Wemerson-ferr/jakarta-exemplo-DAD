package org.eclipse.jakarta.formulario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.ws.rs.POST;

import java.io.*;

@WebServlet("/cadastrar")
public class ProcessFormServlet extends HttpServlet {

    @POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String idade = request.getParameter("idade");
        String telefone = request.getParameter("telefone");

        // Crie um objeto Pessoa
        Pessoa pessoa = new Pessoa(nome, sobrenome, idade, telefone);

        // Imprima o objeto Pessoa no terminal
        System.out.println(pessoa);

        // Retorne um JSON com os dados da Pessoa
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(pessoa.toJson());
    }
}


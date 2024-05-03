package org.eclipse.jakarta.formulario;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.POST;

@WebServlet("/cadastrar")
public class ProcessFormServlet extends HttpServlet {

    @POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Definindo o cabeçalho para forçar o download
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=relatorio.pdf");

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Recuperando os dados do request
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String idade = request.getParameter("idade");
            String telefone = request.getParameter("telefone");

            // Adicionando os dados ao documento
            Font font = FontFactory.getFont(FontFactory.HELVETICA, "UTF-8");
            document.addTitle("Relatório de cadastro");
            document.add(new Paragraph("Nome: " + nome, font));
            document.add(new Paragraph("Sobrenome: " + sobrenome, font));
            document.add(new Paragraph("Idade: " + idade, font));
            document.add(new Paragraph("Telefone: " + telefone, font));

            document.close();

            Pessoa user = new Pessoa(nome, sobrenome, idade, telefone);
            
            System.out.println(user.toJson());
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }
}

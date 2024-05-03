package org.eclipse.jakarta.formulario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.ws.rs.POST;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

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

        // Crie um documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Adicione o conteúdo JSON à página
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 15);

        // Codifique o texto em UTF-8
        String jsonText = pessoa.toJson();
        byte[] utf8Bytes = jsonText.getBytes("UTF-8");

        // Adicione o texto com caracteres especiais
        contentStream.beginText();
        contentStream.newLineAtOffset(50, 700); // Posição X e Y
        contentStream.showText(new String(utf8Bytes, "UTF-8"));
        contentStream.endText();
        contentStream.close();

        // Salve o documento como um arquivo PDF
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        // Defina o cabeçalho de resposta para download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=pessoa.pdf");

        // Escreva o conteúdo do PDF na resposta
        OutputStream outputStream = response.getOutputStream();
        byteArrayOutputStream.writeTo(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}

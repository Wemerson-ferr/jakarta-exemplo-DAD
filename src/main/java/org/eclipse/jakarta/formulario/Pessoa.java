package org.eclipse.jakarta.formulario;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class Pessoa {
    private String nome;
    private String sobrenome;
    private String idade;
    private String telefone;

    // Construtor
    public Pessoa(String nome, String sobrenome, String idade, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.telefone = telefone;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Método para converter o objeto Pessoa em uma string JSON
    public String toJson() {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(this);
    }

    @Override
    public String toString() {
        return "Pessoa [nome=" + nome + ", sobrenome=" + sobrenome + ", idade=" + idade + ", telefone=" + telefone + "]";
    }
}

package com.mycompany.atividade;

import java.util.ArrayList;

public class Administrador extends Usuario {
    private int nivelAcesso;
    private String departamento;

    public Administrador(int id, String nome, String email, String senha, int nivelAcesso, String departamento) {
        super(id, nome, email, senha, "ADMIN");
        this.nivelAcesso = nivelAcesso;
        this.departamento = departamento;
    }

    public boolean excluirRegistro(int id, String tipo) {
        System.out.println("Administrador " + getNome() + " excluiu o registo " + id + " do tipo " + tipo);
        return true;
    }

    public void ajustarEstoque(ItemEstoque item, int qtd) {
        System.out.println("Ajuste manual do stock pelo admin: " + item.getNome() + " para " + qtd);
        if (qtd > item.getQuantidade()) {
            item.darEntrada(qtd - item.getQuantidade(), getNome());
        }
    }

    public Relatorio gerarRelatorio(int mes, int ano) {
        Relatorio relatorio = new Relatorio(mes, ano);
        relatorio.gerar();
        return relatorio;
    }

    public void gerenciarUsuario(Usuario u) {
        System.out.println("Buscando usuário: " + u.getNome());
    }
}
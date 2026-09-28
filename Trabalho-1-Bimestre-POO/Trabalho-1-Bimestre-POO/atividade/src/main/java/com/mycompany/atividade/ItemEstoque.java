package com.mycompany.atividade;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class ItemEstoque {

    private int id;
    private String nome;
    private int quantidade;
    private int quantidadeMinima;
    private String lote;
    private String validade; 
    private boolean controlado;
    private String responsavelRetirada;
    private String categoria;

    public ItemEstoque(int id, String nome, int quantidade, int quantidadeMinima, String lote) {
        this.id = id;
        setNome(nome);
        if (quantidade < 0) {
            throw new IllegalArgumentException("quantidade não pode ser negativa");
        }
        this.quantidade = quantidade;
        this.quantidadeMinima = Math.max(quantidadeMinima, 0);
        this.lote = lote;
        this.controlado = false;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("nome do item não pode ser vazio");
        }
        this.nome = nome;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = Math.max(quantidadeMinima, 0);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getResponsavelRetirada() {
        return responsavelRetirada;
    }

    public boolean isControlado() {
        return controlado;
    }

    public void setControlado(boolean controlado) {        
        if (controlado && (lote == null || lote.trim().isEmpty() || validade == null || validade.trim().isEmpty())) {
            throw new IllegalStateException("item controlado precisa de lote e validade");
        }
        this.controlado = controlado;
    }

    public void darEntrada(int qtd, String responsavel) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("quantidade de entrada tem que ser positiva");
        }
        this.quantidade += qtd;
        System.out.println("[ESTOQUE] entrada de " + qtd + " un. de " + nome + " (responsável: " + responsavel + ")");
    }

    public boolean darSaida(int qtd, String responsavel) {
        if (qtd <= 0 || qtd > quantidade) {
            return false;
        }
        if (controlado) {
            if (responsavel == null || responsavel.trim().isEmpty() || lote == null || validade == null) {
                System.out.println("[ESTOQUE] retirada bloqueada - item controlado precisa de responsável, lote e validade");
                return false;
            }
            this.responsavelRetirada = responsavel;
        }
        this.quantidade -= qtd;
        return true;
    }

    public boolean reservar(int qtd) {
        return qtd > 0 && qtd <= quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isAbaixoMinimo() {
        return quantidade < quantidadeMinima;
    }

    public boolean isVencido() {
        if (validade == null) return false;
        try {
            return LocalDate.parse(validade).isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getLote() {
        return lote;
    }

    public void exibir() {
        String status = isAbaixoMinimo() ? "ABAIXO DO MÍNIMO" : "OK";
        System.out.println("[ESTOQUE] " + nome + " | Qtd: " + quantidade + " | Mínimo: " + quantidadeMinima + " -> " + status);
    }
}

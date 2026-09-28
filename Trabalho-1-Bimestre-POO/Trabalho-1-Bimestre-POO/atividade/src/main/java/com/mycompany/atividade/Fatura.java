package com.mycompany.atividade;

import java.time.LocalDate;

public class Fatura {
    private int id;
    private Tutor tutor;
    private double valor;
    private String dataEmissao;
    private String dataVencimento;
    private String status;
    private String descricaoServico;
    private Notificador notificador;

    public Fatura(int id, Tutor tutor, double valor, String descricaoServico, Notificador notificador) {
        this.id = id;
        this.tutor = tutor;
        this.valor = valor;
        this.descricaoServico = descricaoServico;
        this.notificador = notificador;
        this.status = "PENDENTE";
        this.dataEmissao = LocalDate.now().toString();
    }

    public void emitir() {
        System.out.println("Fatura " + id + " emitida para " + tutor.getNome());
        if (notificador != null) notificador.enviarFatura(this);
    }

    public String gerarBoleto() { return "BOLETO-12345"; }
    public String gerarLinkPagamento() { return "http://pagar.fatura/" + id; }

    public void registrarPagamento() {
        this.status = "PAGO";
        System.out.println("Pagamento da fatura " + id + " registado com sucesso.");
    }

    public boolean confirmarPagOnline() {
        return true; // RN12: Simula retorno de API sempre positivo nesta versão.
    }

    public boolean isPendente() { return status.equals("PENDENTE"); }
    public double getValor() { return valor; }
    public String getStatus() { return status; }

    public void exibir() {
        System.out.println("[FATURA] Valor: R$" + String.format("%.2f", valor) + " | Status: " + status);
        if (notificador != null) notificador.enviarFatura(this);
    }
}
package com.mycompany.atividade;

import java.util.List;

public class Notificador {

    private String canal; // EMAIL, SMS ou APP
    private String destinatario;
    private boolean ativo;

    public Notificador(String canal, String destinatario) {
        setCanal(canal);
        this.destinatario = destinatario;
        this.ativo = true;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        if (canal == null) {
            throw new IllegalArgumentException("canal não pode ser nulo");
        }
        String c = canal.trim().toUpperCase();
        if (!c.equals("EMAIL") && !c.equals("SMS") && !c.equals("APP")) {
            throw new IllegalArgumentException("canal inválido, use EMAIL, SMS ou APP");
        }
        this.canal = c;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void enviarConfirmacao(Agendamento ag) {
        enviar(destinatario, "Agendamento confirmado! Status: " + ag.getStatus());
    }

    public void enviarCancelamento(Agendamento ag) {
        enviar(destinatario, "Agendamento cancelado. Status atual: " + ag.getStatus());
    }

    public void enviarReagendamento(Agendamento ag) {
        enviar(destinatario, "Agendamento reagendado. Situação: " + ag.getStatus());
    }

    public void enviarLembreteVacina(Animal a, String dataReforco) {
        String email = a.getTutor().getEmail();
        enviar(email, "Lembrete: a vacina do(a) " + a.getNome() + " tem reforço em " + dataReforco);
    }

    public void enviarAlertaEstoque(ItemEstoque item) {
        enviar(destinatario, "Atenção: " + item.getNome() + " está abaixo do estoque mínimo");
    }

    public void enviarFatura(Fatura f) {
        enviar(destinatario, String.format("Fatura de R$%.2f - status: %s", f.getValor(), f.getStatus()));
    }

    public void enviarAlerta(String destinatarioMsg, String msg) {
        enviar(destinatarioMsg, msg);
    }

    public void enviarCampanha(List<Tutor> lista, String msg) {
        for (Tutor t : lista) {
            enviar(t.getEmail(), msg);
        }
    }

    private void enviar(String destino, String mensagem) {
        if (!ativo) return;
        System.out.println("[NOTIFICADOR] " + canal + " -> " + destino + ": " + mensagem);
    }
}

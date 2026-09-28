package com.mycompany.atividade;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<ItemEstoque> itens = new ArrayList<>();
    private Notificador notificador;

    public Estoque(Notificador notificador) {
        this.notificador = notificador;
    }

    public void adicionarItem(ItemEstoque item) { itens.add(item); }

    public void registrarEntrada(int id, int qtd, String resp) {
        for (ItemEstoque item : itens) {
            if (item.getId() == id) {
                item.darEntrada(qtd, resp);
                return;
            }
        }
    }

    public boolean registrarSaida(int id, int qtd, String resp) {
        for (ItemEstoque item : itens) {
            if (item.getId() == id) {
                boolean ok = item.darSaida(qtd, resp);
                verificarAlertas();
                return ok;
            }
        }
        return false;
    }

    public boolean reservarParaProced(Agendamento ag) { return true; }

    public void verificarAlertas() {
        for (ItemEstoque item : itens) {
            if (item.isAbaixoMinimo() && notificador != null) {
                notificador.enviarAlertaEstoque(item);
            }
        }
    }

    public ItemEstoque buscarItem(String nome) {
        for (ItemEstoque i : itens) {
            if (i.getNome().equalsIgnoreCase(nome)) return i;
        }
        return null;
    }

    public void exibir() {
        System.out.println("--- ESTOQUE ATUAL ---");
        for (ItemEstoque i : itens) i.exibir();
    }
}
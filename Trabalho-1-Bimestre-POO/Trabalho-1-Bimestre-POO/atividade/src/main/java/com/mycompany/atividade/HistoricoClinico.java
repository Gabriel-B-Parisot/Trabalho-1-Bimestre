package com.mycompany.atividade;

import java.util.ArrayList;
        
import java.util.ArrayList;
import java.util.List;

public class HistoricoClinico {
    private int idAnimal;
    private List<Consulta> consultas = new ArrayList<>();
    private List<Vacina> vacinas = new ArrayList<>();
    private List<Cirurgia> cirurgias = new ArrayList<>();
    private List<Exame> exames = new ArrayList<>();
    private List<String> tratamentos = new ArrayList<>();
    private boolean finalizado;

    public HistoricoClinico(int idAnimal) {
        this.idAnimal = idAnimal;
        this.finalizado = false;
    }

    public void adicionarConsulta(Consulta c) {
        if (finalizado) { System.out.println("Histórico finalizado. Bloqueado para edições."); return; }
        consultas.add(c);
    }

    public void adicionarVacina(Vacina v) {
        if (finalizado) { System.out.println("Histórico finalizado. Bloqueado para edições."); return; }
        vacinas.add(v);
    }

    public void adicionarCirurgia(Cirurgia c) {
        if (finalizado) { System.out.println("Histórico finalizado. Bloqueado para edições."); return; }
        cirurgias.add(c);
    }

    public void adicionarExame(Exame e) {
        if (finalizado) { System.out.println("Histórico finalizado. Bloqueado para edições."); return; }
        exames.add(e);
    }

    public void adicionarTratamento(String t) {
        if (finalizado) { System.out.println("Histórico finalizado. Bloqueado para edições."); return; }
        tratamentos.add(t);
    }

    public void finalizar() {
        this.finalizado = true;
    }

    public boolean isFinalizado() { return finalizado; }
    public List<Consulta> getConsultas() { return consultas; }
    public List<Vacina> getVacinas() { return vacinas; }

    public void exibir() {
        System.out.println("[HISTORICO] Consultas: " + consultas.size() + " | Vacinas: " + vacinas.size() + " | Cirurgias: " + cirurgias.size());
    }
}
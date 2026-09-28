package com.mycompany.atividade;

import java.util.ArrayList;
import java.util.List;

public class Cirurgia extends RegistroClinico {
    private int duracao;
    private String sala;
    private List<String> equipe = new ArrayList<>();
    private String anestesia;
    private List<String> medicamentos = new ArrayList<>();

    public Cirurgia(int id, String data, String descricao, Veterinario veterinario, String sala, int duracao) {
        super(id, data, descricao, veterinario);
        this.sala = sala;
        if (duracao < 120) throw new IllegalArgumentException("A duração minima para cirurgia é de 120 minutos.");
        this.duracao = duracao;
    }

    public String getSala() { return sala; }
    public int getDuracao() { return duracao; }
    
    public void setEquipe(List<String> equipe) { this.equipe = equipe; }
    public void adicionarMedicamento(String m) { medicamentos.add(m); }
    
    public boolean validarRecursos() {
        return !equipe.isEmpty() && !medicamentos.isEmpty();
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("  Sala: " + sala + " | Duração: " + duracao + " mins");
    }
}
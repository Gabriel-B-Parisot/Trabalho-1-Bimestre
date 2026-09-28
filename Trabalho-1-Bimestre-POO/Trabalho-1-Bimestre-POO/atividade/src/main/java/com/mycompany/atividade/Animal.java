package com.mycompany.atividade;

import java.time.LocalDate;
import java.time.Period;

public class Animal {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private String dataNascimento;
    private double peso;
    private Tutor tutor;
    private HistoricoClinico historico;

    public Animal(int id, String nome, String especie, String raca, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.tutor = tutor;
        this.historico = new HistoricoClinico(id);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public Tutor getTutor() { return tutor; }
    public HistoricoClinico getHistorico() { return historico; }
    public double getPeso() { return peso; }
    
    public void setPeso(double peso) {
        if (peso <= 0) throw new IllegalArgumentException("O peso deve ser maior que zero.");
        this.peso = peso;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int calcularIdade() {
        if (dataNascimento == null) return 0;
        LocalDate nascimento = LocalDate.parse(dataNascimento);
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    public void exibir() {
        System.out.println("[ANIMAL] " + nome + " | Espécie: " + especie + " | Tutor: " + tutor.getNome());
    }
}
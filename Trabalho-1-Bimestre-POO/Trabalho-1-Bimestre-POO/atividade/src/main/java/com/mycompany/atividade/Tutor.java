package com.mycompany.atividade;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Usuario {

    private String cpf;
    private String telefone;
    private String endereco;
    private List<Animal> animais = new ArrayList<>();

    public Tutor(int id, String nome, String email, String cpf, String telefone) {
        super(id, nome, email, "trocar123", "TUTOR");
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void adicionarAnimal(Animal a) {
        animais.add(a);
    }

    public void verHistoricoAnimal(int idAnimal) {
        for (Animal a : animais) {
            if (a.getId() == idAnimal) {
                a.getHistorico().exibir();
                return;
            }
        }
        System.out.println("animal " + idAnimal + " não encontrado");
    }

    public List<Fatura> verFaturas() {
        // sem um repositório central de fatura, fica vazio por enquanto
        return new ArrayList<>();
    }

    public Animal buscarAnimal(String nome) {
        for (Animal a : animais) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("  CPF: " + cpf + " | Telefone: " + telefone + " | Animais: " + animais.size());
    }
}

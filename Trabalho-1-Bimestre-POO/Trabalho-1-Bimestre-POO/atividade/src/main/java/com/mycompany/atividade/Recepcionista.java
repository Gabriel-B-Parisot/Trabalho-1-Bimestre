package com.mycompany.atividade;

import java.util.ArrayList;
import java.util.List;

public class Recepcionista extends Usuario {

    private String ramal;
    private String turno;

    private List<Tutor> tutoresCadastrados = new ArrayList<>();
    private List<Animal> animaisCadastrados = new ArrayList<>();
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Recepcionista(int id, String nome, String email, String ramal) {
        super(id, nome, email, "trocar123", "RECEP");
        this.ramal = ramal;
    }

    public String getRamal() {
        return ramal;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void cadastrarTutor(Tutor t) {
        tutoresCadastrados.add(t);
    }

    public void cadastrarAnimal(Animal a) {
        animaisCadastrados.add(a);
    }

    public boolean agendarConsulta(Agendamento ag) {
        boolean ok = ag.agendar();
        if (ok) {
            agendamentos.add(ag);
        }
        return ok;
    }

    public void cancelarConsulta(int idAgendamento) {
        for (Agendamento ag : agendamentos) {
            if (ag.getId() == idAgendamento) {
                ag.cancelar("cancelado pela recepção");
                return;
            }
        }
        System.out.println("agendamento " + idAgendamento + " não encontrado");
    }

    public Tutor buscarTutor(String termo) {
        for (Tutor t : tutoresCadastrados) {
            if (t.getNome().equalsIgnoreCase(termo) || t.getCpf().equals(termo)) {
                return t;
            }
        }
        return null;
    }

    public Animal buscarAnimal(String termo) {
        for (Animal a : animaisCadastrados) {
            if (a.getNome().equalsIgnoreCase(termo)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("  Ramal: " + ramal + " | Turno: " + turno);
    }
}

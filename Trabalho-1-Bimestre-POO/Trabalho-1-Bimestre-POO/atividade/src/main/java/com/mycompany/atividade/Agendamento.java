package com.mycompany.atividade;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Agendamento {
    private int id;
    private String dataHora; 
    private String tipo;
    private String status;
    private Animal animal;
    private Veterinario veterinario;
    private String sala;
    private Notificador notificador;
    private List<String> historico = new ArrayList<>();

    public Agendamento(int id, String dataHora, String tipo, Animal animal, Veterinario vet, Notificador notificador) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.animal = animal;
        this.veterinario = vet;
        this.notificador = notificador;
        this.status = "PENDENTE";
    }

    public boolean agendar() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dataAgendada = LocalDateTime.parse(this.dataHora, formatter);
            LocalDateTime agora = LocalDateTime.now();

            
            if (dataAgendada.isBefore(agora)) {
                System.out.println("Falha! Não é possível agendar para uma data ou hora no passado.");
                return false;
            }

            
            int hora = dataAgendada.getHour();
            if (hora < 8 || hora >= 18) {
                return false;
            }

            this.status = "AGENDADO";
            notificarTutor("enviarConfirmacao");
            return true;

        } catch (DateTimeParseException e) {
            System.out.println("Falha! Formato de data inválido.");
            return false;
        }
    }

    public void cancelar(String motivo) {
        this.status = "CANCELADO";
        historico.add("Cancelado: " + motivo);
        notificarTutor("enviarCancelamento");
    }

    public void reagendar(String novaData) {
        this.dataHora = novaData;
        if(validarHorario() && validarVeterinario()) {
            this.status = "AGENDADO";
            historico.add("Reagendado para " + novaData);
            notificarTutor("enviarReagendamento");
        }
    }

    public boolean validarHorario() {        
        try {
            int hora = Integer.parseInt(dataHora.substring(11, 13));
            if (hora < 8 || hora >= 18) {
                System.out.println("Erro: Agendamentos so podem ser feitos entre as 08h e 18h.");
                return false;
            }
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean validarVeterinario() {
        return veterinario.isDisponivel();
    }

    public boolean reservarRecursos() { return true; } // Simulação
    public String getStatus() { return status; }
    public List<String> getHistorico() { return historico; }
    public int getId() { return id; }

    public void notificarTutor(String acao) {
        if (notificador != null) {
            if (acao.equals("enviarConfirmacao")) notificador.enviarConfirmacao(this);
            else if (acao.equals("enviarCancelamento")) notificador.enviarCancelamento(this);
            else if (acao.equals("enviarReagendamento")) notificador.enviarReagendamento(this);
        }
    }

    public void exibir() {
        System.out.println("[AGENDAMENTO] Tipo: " + tipo + " | Status: " + status + " | Hora: " + dataHora);
    }
}
package com.mycompany.atividade;
import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Usuario {

    private String crmv;
    private String especialidade;
    private boolean disponivel;

    public Veterinario(int id, String nome, String email, String crmv, String especialidade) {        
        super(id, nome, email, "trocar123", "VET");
        this.crmv = crmv;
        this.especialidade = especialidade;
        this.disponivel = true;
    }

    public String getCrmv() {
        return crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void registrarLaudo(int idRegistro, String texto) {
        System.out.println(getNome() + " registrou laudo no registro " + idRegistro + ": " + texto);
    }

    public void emitirPrescricao(int idRegistro, String prescricao) {
        System.out.println(getNome() + " emitiu prescrição no registro " + idRegistro + ": " + prescricao);
    }

    public List<String> consultarAgenda() {        
        return new ArrayList<>();
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("  CRMV: " + crmv + " | Especialidade: " + especialidade + " | Disponível: " + disponivel);
    }
}

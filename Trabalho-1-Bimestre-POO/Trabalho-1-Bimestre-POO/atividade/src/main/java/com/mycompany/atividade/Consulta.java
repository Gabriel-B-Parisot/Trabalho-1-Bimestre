package com.mycompany.atividade;

public class Consulta extends RegistroClinico {
    private String motivo;
    private String prescricao;
    private String dataRetorno;

    public Consulta(int id, String data, String descricao, Veterinario veterinario, String motivo) {
        super(id, data, descricao, veterinario);
        this.motivo = motivo;
    }

    public String getMotivo() { return motivo; }
    public String getPrescricao() { return prescricao; }
    public void setPrescricao(String prescricao) { this.prescricao = prescricao; }
    public String getDataRetorno() { return dataRetorno; }
    public void setDataRetorno(String dataRetorno) { this.dataRetorno = dataRetorno; }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("  Motivo: " + motivo + " | Prescrição: " + (prescricao != null ? prescricao : "N/A"));
    }
}
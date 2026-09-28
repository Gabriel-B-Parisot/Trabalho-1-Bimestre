package com.mycompany.atividade;

public class RegistroClinico {

    private int id;
    private String data;
    private String descricao;
    private Veterinario veterinario;
    private String laudoAnexo;
    private boolean finalizado;

    public RegistroClinico(int id, String data, String descricao, Veterinario veterinario) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.veterinario = veterinario;
        this.finalizado = false;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void finalizar() {
        this.finalizado = true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void anexarLaudo(String path) {
        if (finalizado) {
            System.out.println("não dá pra mudar, registro já foi finalizado");
            return;
        }
        this.laudoAnexo = path;
    }

    public String getLaudo() {
        return laudoAnexo;
    }

    public void exibir() {
        System.out.println("[REGISTRO #" + id + "] " + data + " - " + descricao + " (Dr(a). " + veterinario.getNome() + ")");
    }
}

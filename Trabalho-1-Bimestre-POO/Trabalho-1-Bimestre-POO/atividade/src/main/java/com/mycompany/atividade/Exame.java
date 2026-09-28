package com.mycompany.atividade;

public class Exame extends RegistroClinico {
    private String tipo;
    private String resultado;
    private String imagemAnexo;
    private String laboratorio;

    public Exame(int id, String data, String descricao, Veterinario veterinario, String tipo) {
        super(id, data, descricao, veterinario);
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    
    public void anexarImagem(String path) { this.imagemAnexo = path; }
    public String getImagem() { return imagemAnexo; }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("  Exame Tipo: " + tipo + " | Resultado: " + (resultado != null ? resultado : "Pendente"));
    }
}
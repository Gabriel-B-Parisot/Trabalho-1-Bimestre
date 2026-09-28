package com.mycompany.atividade;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Vacina extends RegistroClinico {

    private String nomeVacina;
    private String dataAplicacao;
    private String dataReforco;
    private String lote;
    private String fabricante;

    public Vacina(int id, String data, String descricao, Veterinario veterinario, String nomeVacina) {
        super(id, data, descricao, veterinario);
        this.nomeVacina = nomeVacina;
        this.dataAplicacao = data;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public String getDataReforco() {
        return dataReforco;
    }

    public void setDataReforco(String dataReforco) {
        this.dataReforco = dataReforco;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public boolean precisaReforco() {
        if (dataReforco == null) return false;
        try {
            return LocalDate.parse(dataReforco).isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("  Vacina: " + nomeVacina + " | Lote: " + lote + " | Reforço: " + dataReforco);
    }
}

package com.mycompany.atividade;

public class Relatorio {

    private static int contador = 1;

    private int id;
    private int mes;
    private int ano;
    private int totalAtendimentos;
    private double faturamentoTotal;
    private double totalDespesas;
    private double taxaRetorno;
    private double tempMedioAtend;
    private String procedMaisRealizado;
    private boolean gerado;

    public Relatorio(int mes, int ano) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("mês inválido");
        }
        this.id = contador++;
        this.mes = mes;
        this.ano = ano;
        this.gerado = false;
    }

    public int getId() {
        return id;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public boolean isGerado() {
        return gerado;
    }

    public void setTotalAtendimentos(int totalAtendimentos) {
        this.totalAtendimentos = Math.max(totalAtendimentos, 0);
    }

    public int getTotalAtendimentos() {
        return totalAtendimentos;
    }

    public void setFaturamentoTotal(double faturamentoTotal) {
        this.faturamentoTotal = faturamentoTotal;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public void setTaxaRetorno(double taxaRetorno) {
        this.taxaRetorno = taxaRetorno;
    }

    public void setTempMedioAtend(double tempMedioAtend) {
        this.tempMedioAtend = tempMedioAtend;
    }

    public void setProcedMaisRealizado(String procedMaisRealizado) {
        this.procedMaisRealizado = procedMaisRealizado;
    }

    public void gerar() {
        this.gerado = true;
        System.out.println("relatório " + mes + "/" + ano + " gerado");
    }

    public String getEstatisticas() {
        return String.format("Atendimentos: %d | Faturamento: R$%.2f | Despesas: R$%.2f | Retorno: %.1f%% | Tempo médio: %.1f min",
                totalAtendimentos, faturamentoTotal, totalDespesas, taxaRetorno, tempMedioAtend);
    }

    public double calcularFaturamento() {
        return faturamentoTotal - totalDespesas;
    }

    public double calcularTaxaRetorno() {
        return taxaRetorno;
    }

    public double calcularTempMedio() {
        return tempMedioAtend;
    }

    public String getProcedMaisRealizado() {
        return procedMaisRealizado;
    }

    public void exportar() {
        System.out.println("relatório exportado - " + mes + "/" + ano);
    }

    public void exibir() {
        if (!gerado) {
            System.out.println("relatório ainda não foi gerado, chama gerar() primeiro");
            return;
        }
        System.out.println("[RELATORIO " + mes + "/" + ano + "] " + getEstatisticas());
    }
}

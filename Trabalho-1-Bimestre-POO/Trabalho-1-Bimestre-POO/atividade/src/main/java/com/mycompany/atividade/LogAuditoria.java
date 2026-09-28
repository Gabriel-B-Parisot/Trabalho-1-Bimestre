package com.mycompany.atividade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogAuditoria {

    private static int contador = 1;
    private static List<LogAuditoria> historico = new ArrayList<>();

    private int id;
    private String dataHora;
    private Usuario usuario;
    private String acao;
    private String entidadeAfetada;
    private int idEntidade;
    private String ipOrigem;

    public LogAuditoria(Usuario usuario, String acao, String entidadeAfetada) {
        this(usuario, acao, entidadeAfetada, -1);
    }

    public LogAuditoria(Usuario usuario, String acao, String entidadeAfetada, int idEntidade) {
        this.id = contador++;
        this.usuario = usuario;
        this.acao = acao;
        this.entidadeAfetada = entidadeAfetada;
        this.idEntidade = idEntidade;
        this.ipOrigem = "127.0.0.1";
        this.dataHora = LocalDateTime.now().toString();
    }

    public void registrar() {
        historico.add(this);
        exibir();
    }

    public String getAcao() {
        return acao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getEntidadeAfetada() {
        return entidadeAfetada;
    }

    public int getIdEntidade() {
        return idEntidade;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }

    public void setIpOrigem(String ipOrigem) {
        this.ipOrigem = ipOrigem;
    }

    public static List<LogAuditoria> buscarPorUsuario(Usuario u) {
        List<LogAuditoria> resultado = new ArrayList<>();
        for (LogAuditoria log : historico) {
            if (log.usuario == u) {
                resultado.add(log);
            }
        }
        return resultado;
    }

    public static List<LogAuditoria> buscarPorEntidade(String entidade) {
        List<LogAuditoria> resultado = new ArrayList<>();
        for (LogAuditoria log : historico) {
            if (log.entidadeAfetada.equalsIgnoreCase(entidade)) {
                resultado.add(log);
            }
        }
        return resultado;
    }

    public void exibir() {
        System.out.println("[LOG] " + dataHora + " | Usuario: " + usuario.getNome() + " | Ação: " + acao);
    }
}

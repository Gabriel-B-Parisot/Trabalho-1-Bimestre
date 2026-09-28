package com.mycompany.atividade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("=== SISTEMA PETCARE ===");
        
        Administrador admin = new Administrador(1, "Carlos Admin", "admin@petcare.com", "senha123", 1, "Diretoria");
        Veterinario vetJasinto = new Veterinario(2, "Jasinto", "ana@petcare.com", "CRMV-1234", "Clínica Geral");
        Tutor tutorCarlos = new Tutor(3, "Carlos", "joao@email.com", "111.222.333-44", "99999-0000");
        Notificador notificador = new Notificador("EMAIL", tutorCarlos.getEmail());
        
        Animal nina = new Animal(1, "Nina", "Cachorro", "Yorkshire terrier", tutorCarlos);
        tutorCarlos.adicionarAnimal(nina);
        
        HistoricoClinico historicoRex = nina.getHistorico();
        historicoRex.adicionarConsulta(new Consulta(1, "2026-10-01", "Rotina", vetJasinto, "Checkup"));
        historicoRex.adicionarConsulta(new Consulta(2, "2026-10-15", "Retorno", vetJasinto, "Acompanhamento"));
        historicoRex.adicionarVacina(new Vacina(1, "2026-10-01", "Vacina V10", vetJasinto, "V10"));
        
        Agendamento agendamentoBase = new Agendamento(1, "2026-10-20 14:00", "CONSULTA", nina, vetJasinto, notificador);
        agendamentoBase.agendar(); 
        
        ItemEstoque dipirona = new ItemEstoque(1, "Dipirona", 50, 10, "LOTE999");
        Fatura fatura = new Fatura(1, tutorCarlos, 150.00, "Consulta", notificador);
        LogAuditoria log = new LogAuditoria(admin, "AJUSTE ESTOQUE", "ItemEstoque");

        vetJasinto.exibir();
        nina.exibir();
        historicoRex.exibir();
        agendamentoBase.exibir();
        dipirona.exibir();
        fatura.exibir();
        log.registrar();

        System.out.println("\n[SISTEMA] Inicialização concluída. Carregando interface de usuário...\n");

        // =====================================================================
        // 1. FASE INTERATIVA LIVRE
        // =====================================================================
        Scanner scanner = new Scanner(System.in);
        List<Agendamento> listaAgendamentos = new ArrayList<>();
        int geradorId = 100; // Começa a gerar IDs a partir do 100
        boolean executando = true;

        while (executando) {
            System.out.println("\n=====================================");
            System.out.println("TERMINAL INTERATIVO PETCARE");
            System.out.println("=====================================");
            System.out.print("É cliente da clínica ou admin? (sim / nao / admin): ");
            
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("nao") || resposta.equals("não")) {
                System.out.println("Fechando o sistema. Obrigado pela visita!");
                executando = false;
            } 
            else if (resposta.equals("sim")) {
                boolean menuCliente = true;
                while (menuCliente) {
                    System.out.println("\n--- ÁREA DO CLIENTE ---");
                    System.out.println("1 - Agendar uma consulta");
                    System.out.println("2 - Verificar meus agendamentos");
                    System.out.println("3 - Voltar ao início");
                    System.out.print("Opção: ");
                    String opcaoCli = scanner.nextLine();

                    if (opcaoCli.equals("1")) {
                        System.out.print("Digite a data e hora desejada (Ex: 2026-10-15 14:00): ");
                        String dataHora = scanner.nextLine();
                        
                        Agendamento novoAgendamento = new Agendamento(geradorId++, dataHora, "CONSULTA", nina, vetJasinto, notificador);
                        
                        if (novoAgendamento.agendar()) {
                            listaAgendamentos.add(novoAgendamento);
                            System.out.println("Sucesso! Sua consulta foi marcada (ID: " + novoAgendamento.getId() + ").");
                        } else {
                            System.out.println("Falha! Horário fora de funcionamento (08h-18h).");
                        }
                    } else if (opcaoCli.equals("2")) {
                        if (listaAgendamentos.isEmpty()) {
                            System.out.println("Você não possui consultas agendadas.");
                        } else {
                            System.out.println("Seus agendamentos:");
                            for (Agendamento ag : listaAgendamentos) {
                                ag.exibir();
                            }
                        }
                    } else if (opcaoCli.equals("3")) {
                        menuCliente = false; 
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }
            } 
            else if (resposta.equals("admin")) {
                boolean menuAdmin = true;
                while (menuAdmin) {
                    System.out.println("\n--- ADMINISTRAÇÃO ---");
                    System.out.println("1 - Ver todos os agendamentos");
                    System.out.println("2 - Cancelar e excluir agendamento");
                    System.out.println("3 - Voltar ao início");
                    System.out.print("Opção: ");
                    String opcaoAdm = scanner.nextLine();

                    if (opcaoAdm.equals("1")) {
                        if (listaAgendamentos.isEmpty()) {
                            System.out.println("A agenda está livre de novas marcações.");
                        } else {
                            System.out.println("Agendamentos no sistema:");
                            for (Agendamento ag : listaAgendamentos) {
                                ag.exibir();
                            }
                        }
                    } else if (opcaoAdm.equals("2")) {
                        if (listaAgendamentos.isEmpty()) {
                            System.out.println("Não há agendamentos para cancelar.");
                        } else {
                            System.out.print("Digite o ID do agendamento que deseja excluir: ");
                            try {
                                int idExcluir = Integer.parseInt(scanner.nextLine());
                                Agendamento agendamentoEncontrado = null;
                                
                                for (Agendamento ag : listaAgendamentos) {
                                    if (ag.getId() == idExcluir) {
                                        agendamentoEncontrado = ag;
                                        break;
                                    }
                                }

                                if (agendamentoEncontrado != null) {
                                    agendamentoEncontrado.cancelar("Cancelado pela administração");
                                    admin.excluirRegistro(agendamentoEncontrado.getId(), "Agendamento");
                                    listaAgendamentos.remove(agendamentoEncontrado);
                                    System.out.println("Agendamento " + idExcluir + " excluído com sucesso.");
                                } else {
                                    System.out.println("Agendamento com ID " + idExcluir + " não encontrado.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Por favor, digite um número válido para o ID.");
                            }
                        }
                    } else if (opcaoAdm.equals("3")) {
                        menuAdmin = false; 
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }
            } 
            else {
                System.out.println("Resposta inválida. Por favor, digite 'sim', 'nao' ou 'admin'.");
            }
        }
        scanner.close();
    }
}
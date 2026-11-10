package View;

import javax.swing.*;
import java.awt.*;

import Controller.EventoController;
import Controller.ParticipanteController;
import Controller.InscricaoController;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(EventoController eventoController, ParticipanteController participanteController, InscricaoController inscricaoController) {

        setTitle("Sistema de Gerenciamento de Eventos");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Eventos",
            new TelaEvento(eventoController, inscricaoController));

        abas.addTab("Participantes",
            new TelaParticipante(participanteController));

        abas.addTab("Inscrições", new TelaInscricoes(eventoController, participanteController, inscricaoController));

        abas.addTab("Relatórios", new TelaRelatorios(eventoController, inscricaoController));

        add(abas, BorderLayout.CENTER);

        JLabel titulo = new JLabel("SISTEMA DE GERENCIAMENTO DE EVENTOS");

        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBackground(new Color(25, 45, 80));
        titulo.setOpaque(true);
        titulo.setPreferredSize(new Dimension(1000, 65));

        add(titulo, BorderLayout.NORTH);
    }
}

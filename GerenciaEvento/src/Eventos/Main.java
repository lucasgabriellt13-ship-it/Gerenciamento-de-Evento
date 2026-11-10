package Eventos;

import javax.swing.SwingUtilities;

import Controller.EventoController;
import Controller.ParticipanteController;
import Controller.InscricaoController;

import View.TelaPrincipal;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Criando os Controllers
            EventoController eventoController =
                new EventoController();

            ParticipanteController participanteController =
                new ParticipanteController();

            InscricaoController inscricaoController =
                new InscricaoController(
                    eventoController,
                    participanteController
                );

            // Abrindo a interface gráfica
            TelaPrincipal tela = new TelaPrincipal(
                eventoController,
                participanteController,
                inscricaoController
            );

            tela.setVisible(true);
        });
    }
}

package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Model.Evento;
import Model.Participante;
import Model.Inscricao;

import Controller.EventoController;
import Controller.ParticipanteController;
import Controller.InscricaoController;

public class TelaInscricoes extends JPanel {

    private EventoController eventoController;
    private ParticipanteController participanteController;
    private InscricaoController inscricaoController;

    private JComboBox<Evento> comboEventos;
    private JComboBox<Participante> comboParticipantes;

    private DefaultTableModel modelo;
    private JTable tabela;

    public TelaInscricoes(EventoController eventoController, ParticipanteController participanteController, InscricaoController inscricaoController) {

        this.eventoController = eventoController;
        this.participanteController = participanteController;
        this.inscricaoController = inscricaoController;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Gerenciamento de Inscrições");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(2, 2, 10, 10));

        comboEventos = new JComboBox<>();
        comboParticipantes = new JComboBox<>();

        comboEventos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice, boolean selecionado, boolean foco) {

                super.getListCellRendererComponent(lista, valor, indice, selecionado, foco);

                if (valor instanceof Evento) {
                    setText(((Evento) valor).getNome());
                }

                return this;
            }
        });

        comboParticipantes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice, boolean selecionado, boolean foco) {

                super.getListCellRendererComponent(lista, valor, indice, selecionado, foco);

                if (valor instanceof Participante) {
                    setText(((Participante) valor).getNome());
                }

                return this;
            }
        });

        formulario.add(new JLabel("Selecione o evento:"));
        formulario.add(comboEventos);

        formulario.add(new JLabel("Selecione o participante:"));
        formulario.add(comboParticipantes);

        JButton btnInscrever = new JButton("Realizar Inscrição");
        JButton btnCancelar = new JButton("Cancelar Inscrição");

        btnInscrever.addActionListener(e -> realizarInscricao());
        btnCancelar.addActionListener(e -> cancelarInscricao());

        JPanel botoes = new JPanel();

        botoes.add(btnInscrever);
        botoes.add(btnCancelar);

        JPanel superior = new JPanel(new BorderLayout(10, 10));

        superior.add(formulario, BorderLayout.CENTER);
        superior.add(botoes, BorderLayout.SOUTH);

        modelo = new DefaultTableModel(
            new String[]{"Código Evento", "Evento", "Código Participante", "Participante"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        JPanel centro = new JPanel(new BorderLayout(10, 10));

        centro.add(superior, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        atualizarCombos();
        atualizarTabela();
    }

    private void realizarInscricao() {

        Evento evento = (Evento) comboEventos.getSelectedItem();

        Participante participante =
            (Participante) comboParticipantes.getSelectedItem();

        if (evento == null || participante == null) {

            JOptionPane.showMessageDialog(this, "Cadastre um evento e um participante primeiro!");

            return;
        }

        String mensagem = inscricaoController.realizarIncricao(evento.getCodigo(), participante.getCodigo());

        JOptionPane.showMessageDialog(this, mensagem);

        atualizarTabela();
    }

    private void cancelarInscricao() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(this, "Selecione uma inscrição na tabela!");

            return;
        }

        int codigoEvento = (int) modelo.getValueAt(linha, 0);

        int codigoParticipante = (int) modelo.getValueAt(linha, 2);

        int resposta = JOptionPane.showConfirmDialog(this,"Deseja realmente cancelar esta inscrição?","Confirmar cancelamento", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {

            String mensagem = inscricaoController.cancelarInscricao(codigoEvento, codigoParticipante);

            JOptionPane.showMessageDialog(this, mensagem);

            atualizarTabela();
        }
    }

    private void atualizarCombos() {

        comboEventos.removeAllItems();
        comboParticipantes.removeAllItems();

        for (Evento evento : eventoController.listarEventos()) {
            comboEventos.addItem(evento);
        }

        for (Participante participante :
            participanteController.listarParticipantes()) {

            comboParticipantes.addItem(participante);
        }
    }

    public void atualizarTabela() {

        modelo.setRowCount(0);

        for (Inscricao inscricao :
            inscricaoController.listarTodasInscricoes()) {

            modelo.addRow(new Object[]{
                inscricao.getEvento().getCodigo(),
                inscricao.getEvento().getNome(),
                inscricao.getParticipante().getCodigo(),
                inscricao.getParticipante().getNome()
            });
        }

        atualizarCombos();
    }
}



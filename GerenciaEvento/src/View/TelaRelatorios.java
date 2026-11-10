package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Model.Evento;
import Model.Inscricao;

import Controller.EventoController;
import Controller.InscricaoController;

public class TelaRelatorios extends JPanel {

    private EventoController eventoController;
    private InscricaoController inscricaoController;

    private JComboBox<Evento> comboEventos;

    private JLabel lblInscritos;
    private JLabel lblVagas;
    private JLabel lblCapacidade;

    private DefaultTableModel modelo;

    public TelaRelatorios(
        EventoController eventoController,
        InscricaoController inscricaoController) {

        this.eventoController = eventoController;
        this.inscricaoController = inscricaoController;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Relatórios de Eventos");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);

        comboEventos = new JComboBox<>();

        comboEventos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList<?> lista, Object valor, int indice,
                boolean selecionado, boolean foco) {

                super.getListCellRendererComponent(lista, valor, indice, selecionado, foco);

                if (valor instanceof Evento) {setText(((Evento) valor).getNome());
             }

                return this;
            }
        });

        JButton btnGerar = new JButton("Gerar Relatório");

        btnGerar.addActionListener(e -> gerarRelatorio());

        JPanel selecao = new JPanel(new FlowLayout(FlowLayout.LEFT));

        selecao.add(new JLabel("Selecione o evento:"));
        selecao.add(comboEventos);
        selecao.add(btnGerar);

        lblInscritos = new JLabel("Inscritos: 0");
        lblVagas = new JLabel("Vagas disponíveis: 0");
        lblCapacidade = new JLabel("Capacidade máxima: 0");

        JPanel indicadores = new JPanel(new GridLayout(1, 3, 10, 10));

        indicadores.add(lblInscritos);
        indicadores.add(lblVagas);
        indicadores.add(lblCapacidade);

        modelo = new DefaultTableModel(new String[]{"Código", "Nome", "Contato"},0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        JPanel centro = new JPanel(new BorderLayout(10, 10));

        centro.add(selecao, BorderLayout.NORTH);
        centro.add(indicadores, BorderLayout.CENTER);
        centro.add(scroll, BorderLayout.SOUTH);

        add(centro, BorderLayout.CENTER);

        atualizarEventos();
    }

    private void atualizarEventos() {

        comboEventos.removeAllItems();

        for (Evento evento : eventoController.listarEventos()) {
            comboEventos.addItem(evento);
        }
    }

    private void gerarRelatorio() {

        Evento evento = (Evento) comboEventos.getSelectedItem();

        if (evento == null) {

            JOptionPane.showMessageDialog(this, "Nenhum evento cadastrado!");
          
            return;
        }

        int quantidade = inscricaoController.contarInscritos(evento.getCodigo());

        int vagas = inscricaoController.calcularVagasDisponiveis(evento.getCodigo());

        lblInscritos.setText("Inscritos: " + quantidade);

        lblVagas.setText("Vagas disponíveis: " + vagas);

        lblCapacidade.setText(
            "Capacidade máxima: " + evento.getCapacidadeMaxima()
        );

        modelo.setRowCount(0);

        for (Inscricao inscricao : inscricaoController.listarInscricoesPorEvento(evento.getCodigo())) {

            modelo.addRow(new Object[]{
                inscricao.getParticipante().getCodigo(),
                inscricao.getParticipante().getNome(),
                inscricao.getParticipante().getContato()
            });
        }
    }
}



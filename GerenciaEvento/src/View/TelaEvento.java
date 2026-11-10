package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

import Model.Evento;
import Controller.EventoController;
import Controller.InscricaoController;

public class TelaEvento extends JPanel {

    private EventoController eventoController;
    private InscricaoController inscricaoController;

    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtData;
    private JTextField txtLocal;
    private JTextField txtCapacidade;

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaEvento(EventoController eventoController, InscricaoController inscricaoController) {

        this.eventoController = eventoController;
        this.inscricaoController = inscricaoController;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de Eventos");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));

        txtCodigo = new JTextField();
        txtNome = new JTextField();
        txtData = new JTextField();
        txtLocal = new JTextField();
        txtCapacidade = new JTextField();

        formulario.add(new JLabel("Código:"));
        formulario.add(txtCodigo);

        formulario.add(new JLabel("Nome:"));
        formulario.add(txtNome);

        formulario.add(new JLabel("Data (AAAA-MM-DD):"));
        formulario.add(txtData);

        formulario.add(new JLabel("Local:"));
        formulario.add(txtLocal);

        formulario.add(new JLabel("Capacidade máxima:"));
        formulario.add(txtCapacidade);

        JButton btnCadastrar = new JButton("Cadastrar Evento");
        JButton btnAlterar = new JButton("Alterar Capacidade");

        btnCadastrar.addActionListener(e -> cadastrarEvento());
        btnAlterar.addActionListener(e -> alterarCapacidade());

        JPanel botoes = new JPanel();

        botoes.add(btnCadastrar);
        botoes.add(btnAlterar);

        JPanel superior = new JPanel(new BorderLayout(10, 10));

        superior.add(formulario, BorderLayout.CENTER);
        superior.add(botoes, BorderLayout.SOUTH);

        modelo = new DefaultTableModel(new String[]{"Código", "Nome", "Data", "Local", "Capacidade", "Inscritos"},0) {
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

        atualizarTabela();
    }

    private void cadastrarEvento() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            String nome = txtNome.getText();

            LocalDate data = LocalDate.parse(txtData.getText());

            String local = txtLocal.getText();

            int capacidade = Integer.parseInt(txtCapacidade.getText());

            Evento evento = new Evento(
                codigo, nome, data, local, capacidade
            );

            String mensagem =
                eventoController.cadastrarEvento(evento);

            JOptionPane.showMessageDialog(this, mensagem);

            if (mensagem.equals("Evento cadastrado com sucesso!")) {

                txtCodigo.setText("");
                txtNome.setText("");
                txtData.setText("");
                txtLocal.setText("");
                txtCapacidade.setText("");

                atualizarTabela();
            }

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(this, "Código e capacidade devem ser números!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(this, "Verifique os dados e a data informada (AAAA-MM-DD)!");
        }
    }

    private void alterarCapacidade() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            int capacidade = Integer.parseInt(txtCapacidade.getText());

            int inscritos = inscricaoController.contarInscritos(codigo);

            String mensagem = eventoController.alterarCapacidade(
                codigo, capacidade, inscritos
            );

            JOptionPane.showMessageDialog(this, mensagem);

            atualizarTabela();

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(this, "Informe um código e uma capacidade válidos!");
        }
    }

    public void atualizarTabela() {

        modelo.setRowCount(0);

        for (Evento evento : eventoController.listarEventos()) {

            int inscritos = inscricaoController.contarInscritos(evento.getCodigo());

            modelo.addRow(new Object[]{evento.getCodigo(), evento.getNome(), evento.getData(), evento.getLocal(), evento.getCapacidadeMaxima(), inscritos});
        }
    }
}

package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Model.Participante;
import Controller.ParticipanteController;

public class TelaParticipante extends JPanel {

    private ParticipanteController participanteController;

    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtContato;

    private DefaultTableModel modelo;

    public TelaParticipante(ParticipanteController participanteController) {

        this.participanteController = participanteController;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de Participantes");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(3, 2, 10, 10));

        txtCodigo = new JTextField();
        txtNome = new JTextField();
        txtContato = new JTextField();

        formulario.add(new JLabel("Código:"));
        formulario.add(txtCodigo);

        formulario.add(new JLabel("Nome:"));
        formulario.add(txtNome);

        formulario.add(new JLabel("Contato:"));
        formulario.add(txtContato);

        JButton btnCadastrar = new JButton("Cadastrar Participante");

        btnCadastrar.addActionListener(e -> cadastrarParticipante());

        JPanel superior = new JPanel(new BorderLayout(10, 10));

        superior.add(formulario, BorderLayout.CENTER);
        superior.add(btnCadastrar, BorderLayout.SOUTH);

        modelo = new DefaultTableModel(new String[]{"Código", "Nome", "Contato"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        JPanel centro = new JPanel(new BorderLayout(10, 10));

        centro.add(superior, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        atualizarTabela();
    }

    private void cadastrarParticipante() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            String nome = txtNome.getText();

            String contato = txtContato.getText();

            Participante participante = new Participante(
                codigo, nome, contato
            );

            String mensagem =
                participanteController.cadastrarParticipante(participante);

            JOptionPane.showMessageDialog(this, mensagem);

            if (mensagem.equals("Participante cadastrado com sucesso!")) {

                txtCodigo.setText("");
                txtNome.setText("");
                txtContato.setText("");

                atualizarTabela();
            }

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(this, "O código deve ser um número!");
        }
    }

    public void atualizarTabela() {

        modelo.setRowCount(0);

        for (Participante participante : participanteController.listarParticipantes()) {

            modelo.addRow(new Object[]{
                participante.getCodigo(),
                participante.getNome(),
                participante.getContato()
            });
        }
    }
}

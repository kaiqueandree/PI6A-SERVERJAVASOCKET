package br.com.pi.model;


import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.awt.*;
import br.com.pi.model.ServidorWeb;
import java.awt.Font;

public class TelaServidorWeb extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> Acoes;
	private JLabel lblTitulo, lblStatus, lblEventos, lblAcoes;
	private JButton btnIniciar, btnParar, btnReiniciar;
	private JPanel painel;

	Font fonte = new Font("Arial", Font.BOLD,20);
	Font fonte2 = new Font("Arial", Font.PLAIN,18);
	Font fonte3 = new Font("Arial", Font.PLAIN,15);
	
	public TelaServidorWeb(Connection conn) throws SQLException {
		
		// Super colocar o nome no topo da tela.
		super("Servidor Web");
		
		
		ServidorWeb serv = new ServidorWeb();
		
        
        // Label Titulo
        lblTitulo = new JLabel("Servidor Web");
        lblTitulo.setFont(fonte);
        lblTitulo.setBounds(30, 20, 150, 20);
    	
       // Label Status
        lblStatus = new JLabel("  Status: " + serv.isActive());
        lblStatus.setFont(fonte2);
        lblStatus.setBounds(30, 65, 150, 20);
    	
        
       // Label Eventos
        lblEventos = new JLabel("Últimos eventos: ");
        lblEventos.setFont(fonte2);
        lblEventos.setBounds(10, 120, 150, 20);
        
     // Criacao do Painel de Acoes
        painel = new JPanel();
        painel.setBounds(10,150,320,100);
        painel.setBackground(Color.WHITE);
        String actions = "";
        
        for(String action :  carregaAcoes(conn)){
           lblAcoes = new JLabel(action + "\n");
           lblAcoes.setBounds(0, 0, 100, 20);
           lblAcoes.setFont(fonte3);
           painel.add(lblAcoes);
          
        }
        
         
         
        
        // Botao Iniciar
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setFont(fonte2);
        btnIniciar.setBounds(15, 280, 100, 20);
        
        // Botao Parar
        btnParar = new JButton("Parar");
        btnParar.setFont(fonte2);
        btnParar.setBounds(120, 280, 80, 20);
        
       // Botao Reiniciar
        btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setFont(fonte2);
        btnReiniciar.setBounds(205, 280, 120, 20);
        
        
       
        // Criacao do Container.
        Container caixa = getContentPane();
        caixa.setLayout(new FlowLayout());
        
        // Adicionando os componentes no painel.
        caixa.add(lblTitulo);
        caixa.add(lblStatus);
        caixa.add(lblEventos);
        caixa.add(painel);
        caixa.add(btnIniciar);
        caixa.add(btnParar);
        caixa.add(btnReiniciar);
        
        // Tamanho e localizacao relativa.
        setSize(350,350);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		// Operacao de fechamento ao clicar no X e visibilidade do painel.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		 
	}
	
	
	// Carrega as ações corretas para cada linha simulada no banco de dados.
	public ArrayList<String> carregaAcoes(Connection conn) throws SQLException {
         
		
		 ServidorWeb servidor = new ServidorWeb(0, null, null);
		ArrayList<String> Acoes = new ArrayList<>();
		    servidor.carregaAcoes(conn);
			ArrayList<ServidorWeb> lista = servidor.listarAcoes(conn);
		    for (int i = 0; i < lista.size(); i++) {
		    Acoes.add("Servidor " + lista.get(i).getStatus() + " às " + converteDatas(lista.get(i).getDataAcesso()) + "\n");
		    }
		  //  System.out.println(Acoes);
		    return Acoes;
		    
	}
	
	// Metodo Responsabel por converter as datas para o padrao Brasileiro.
	public String converteDatas(Timestamp t) {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
         String str = fmt.format(t);
        return str;
	}
	

	
}

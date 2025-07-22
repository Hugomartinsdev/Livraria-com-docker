package dtilivraria;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conexao {
    private Connection conn;
	private Statement stm;
	public Conexao(String arquivo) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		this.conn = DriverManager.getConnection("jdbc:sqlite:" + arquivo);
		this.stm = this.conn.createStatement();
	}

	public void initDB() {
		try {
            this.stm.execute("create table if not exists Livro(id integer primary key,nome Varchar(100) Not Null,autor Varchar(100) Not Null ,genero Varchar(100) Not Null,ano_lancamento Integer Not Null,preco Interger Not Null, data_ultima_compra text,quantidade integer Not Null)");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserir(Dados dados) {
		try {
			this.stm = this.conn.createStatement();
			this.stm.executeUpdate("INSERT INTO Livro (nome,autor,genero,ano_lancamento,preco,data_ultima_compra,quantidade) VALUES ('"
				+ dados.getNome() + "','"
                + dados.getAutor() + "','"
				+ dados.getGenero() +"','"
				+ dados.getAnoLancamento() +"','"
				+ dados.getPreco() +"','"
				+ dados.getDataCompra() +"','"
				+ dados.getQuantidade() + "')");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public void alterar(Dados dados) {
		try {
			this.stm = this.conn.createStatement();
			this.stm.execute("update Livro set nome = '"+dados.getNome()+
			"', autor = '"+dados.getAutor()+
			"', genero = '"+dados.getGenero()+
			"', ano_lancamento = '"+dados.getAnoLancamento()+
			"', preco = '"+dados.getPreco()+
			"', data_ultima_compra = '"+dados.getDataCompra()+
			"', quantidade = '"+dados.getQuantidade()+
			"' where id = " +dados.getId());
            System.out.print(dados.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void apagar(String id) {
		try {
			this.stm = this.conn.createStatement();
			this.stm.executeUpdate("delete from Livro WHERE id = " + id);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Log> listarLogs() {
		List<Log> logs = new ArrayList<>();
	
		String sql = """
			SELECT log.id, l.nome AS nome_livro, log.quantidade_comprada, log.data_compra
			FROM log_compra log
			JOIN Livro l ON l.id = log.id_livro
			ORDER BY log.data_compra DESC
			""";
	
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
	
			while (rs.next()) {
				int id = rs.getInt("id");
				String nomeLivro = rs.getString("nome_livro");
				int quantidade = rs.getInt("quantidade_comprada");
				String data = rs.getString("data_compra");
	
				logs.add(new Log(id, nomeLivro, quantidade, data));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return logs;
	}

	public List<Dados> listarLivros() {
		List<Dados> date = new ArrayList<>();
	
		String sql = """
			SELECT *
			FROM Livro
			""";
	
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
	
			while (rs.next()) {
				String id = rs.getString("id");
				String nomeLivro = rs.getString("nome");
				String autor = rs.getString("autor");
				String genero = rs.getString("genero");
				String ano_lancamento = rs.getString("ano_lancamento");
				String preco = rs.getString("preco");
				String data = rs.getString("data_ultima_compra");
				String quantidade = rs.getString("quantidade");
	
				date.add(new Dados(nomeLivro, autor, genero, ano_lancamento, preco, data, id, quantidade));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return date;
	}

    public Dados pegar(String id){
        ResultSet rs;
        Dados dados = new Dados(null,null,null,null,null,null,null,null);
        try {
			rs = this.stm.executeQuery("SELECT * FROM Livro where id = " + id);
			while (rs.next()) {
                dados = new Dados(rs.getString("nome"), rs.getString("autor"), rs.getString("genero"),rs.getString("ano_lancamento"),rs.getString("preco"),rs.getString("data_ultima_compra"),rs.getString("id"),rs.getString("quantidade"));
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
        return dados;
    }

	public boolean retirarLivrosPorId(int idLivro, int quantidadeDesejada) {
		try {
	            this.stm.execute("create table if not exists log_compra(id INTEGER PRIMARY KEY AUTOINCREMENT, id_livro INTEGER NOT NULL, quantidade_comprada INTEGER NOT NULL, data_da_compra TEXT NOT NULL, FOREIGN KEY (id_livro) REFERENCES livros(id))");
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
    	String sqlConsulta = "SELECT nome, quantidade FROM Livro WHERE id = ?";
	    String sqlUpdateLivro = "UPDATE Livro SET quantidade = quantidade - ?, data_ultima_compra = ? WHERE id = ?";
    	String sqlInsertLog = "INSERT INTO log_compra (id_livro, quantidade_comprada, data_compra) VALUES (?, ?, ?)";

	    try (PreparedStatement consultaStmt = conn.prepareStatement(sqlConsulta)) {
    	    consultaStmt.setInt(1, idLivro);
        	ResultSet rs = consultaStmt.executeQuery();

	        if (rs.next()) {
    	        String nomeLivro = rs.getString("nome");
        	    int quantidadeDisponivel = rs.getInt("quantidade");

	            if (quantidadeDesejada <= quantidadeDisponivel) {
    	            System.out.println("\nLivro selecionado: " + nomeLivro);
        	        System.out.println("Quantidade disponível: " + quantidadeDisponivel);
            	    System.out.println("Deseja realmente retirar " + quantidadeDesejada + " unidade(s)? [s/n]");
                	Scanner sc = new Scanner(System.in);
	                String r = sc.nextLine();

    	            if (r.equalsIgnoreCase("s")) {
        	            LocalDate now = LocalDate.now();
            	        String data = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	                    try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdateLivro)) {
    	                    updateStmt.setInt(1, quantidadeDesejada);
        	                updateStmt.setString(2, data);
            	            updateStmt.setInt(3, idLivro);
                	        updateStmt.executeUpdate();
                    	}

                    	try (PreparedStatement logStmt = conn.prepareStatement(sqlInsertLog)) {
	                        logStmt.setInt(1, idLivro);
    	                    logStmt.setInt(2, quantidadeDesejada);
        	                logStmt.setString(3, data);
            	            logStmt.executeUpdate();
                	    }

	                    System.out.println("Compra registrada com sucesso em: " + data);
						sc.close();
    	                return true;
        	        } else {
            	        System.out.println("Retirada cancelada.");
						sc.close();
                	}
	            } else {
    	            System.out.println("Quantidade solicitada maior que o estoque disponível (" + quantidadeDisponivel + ").");
        	    }
	        } else {
    	        System.out.println("Livro com ID " + idLivro + " não encontrado.");
        		}
	    } catch (SQLException e) {
    	    e.printStackTrace();
    	}
    	return false;
	}	

}

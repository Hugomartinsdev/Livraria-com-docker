package dtilivraria;
import java.util.Scanner;

public class Dados {
	private String nome;
	private String autor;
    private String genero;
	private String anoLancamento;
	private String preco;
	private String dataCompra;
    private String id;
	private String quantidade;
    

	public Dados(String nome, String autor, String genero, String anoLancamento, String preco, String dataCompra, String id, String quantidade) {
		this.nome = nome;
		this.autor = autor;
        this.genero = genero;
		this.anoLancamento = anoLancamento;
		this.preco = preco;
		this.dataCompra = dataCompra;
		this.quantidade = quantidade;
        this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

    public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

    public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id; 
	}
	
    public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getDataCompra() {
		return dataCompra;
	}
	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public static boolean validarAno(String ano) {
        if (ano == null || !ano.matches("\\d{4}")) {
        return false;
    }

    // Converte para int e verifica se é maior que 999 (ou seja, mínimo 1000)
    int valor = Integer.parseInt(ano);
    return valor > 999;
    }

    public static boolean validarPreco(String preco) {
        if (preco == null || !preco.matches("\\d{1,4}")) {
        return false;
    }

    int valor = Integer.parseInt(preco);
    return valor > 0;
    }

	public static boolean validarQuantidade(String quantidade) {
        if (quantidade == null || !quantidade.matches("\\d{1,4}")) {
        return false;
    }

    int valor = Integer.parseInt(quantidade);
    return valor > 0;
    }

	public static boolean validarLetra(String texto) {
    return texto != null
        && texto.matches("[\\p{L} ]+")
        && !texto.trim().isEmpty()
        && texto.length() <= 100;
}

    public static String lerAnoValido(Scanner sc) {
        String entrada;
        while (true) {
            System.out.print("\nAno de lançamento (4 dígitos): ");
            entrada = sc.nextLine();
            if (validarAno(entrada)) {
                return entrada;
            } else {
                System.out.println("\nAno inválido! Digite 4 dígitos numéricos.\n");
            }
        }
    }

    public static String lerPrecoValido(Scanner sc) {
        String entrada;
        while (true) {
            System.out.print("\nPreço (até 4 dígitos numéricos): ");
            entrada = sc.nextLine();
            if (validarPreco(entrada)) {
                return entrada;
            } else {
                System.out.println("\nPreço inválido! Use apenas números com no máximo 4 dígitos e não deixe em branco\n");
            }
        }
    }

	public static String lerQuantidadeValida(Scanner sc) {
        String entrada;
        while (true) {
            System.out.print("\nQuantidade (até 4 dígitos numéricos): ");
            entrada = sc.nextLine();
            if (validarQuantidade(entrada)) {
                return entrada;
            } else {
                System.out.println("\nQuantidade inválido! Use apenas números com no máximo 4 dígitos e não deixe em branco\n");
            }
        }
    }

	public static String lerNomeValido (Scanner sc){
		String entrada;
		while (true) {
			System.out.print("\nTítulo do livro (Somente letras): ");
			entrada = sc.nextLine();
			if (validarLetra(entrada)) {
				return entrada;
			}else if(entrada.length() >100){
				System.out.println("\nPassou do limite de caracteres");
			}
			else{
				System.out.println("\nNome invalido use somente letras e não deixe em branco");
			}
		}
	}

	public static String lerAutorValido (Scanner sc){
		String entrada;
		while (true) {
			System.out.print("\nNome do autor (Somente letras): ");
			entrada = sc.nextLine();
			if (validarLetra(entrada)) {
				return entrada;
			}else{
				System.out.println("\nNome invalido use somente letras e não deixe em branco");
			}
		}
	}

	public static String lerGeneroValido (Scanner sc){
		String entrada;
		while (true) {
			System.out.print("\nGenero do livro (Somente letras): ");
			entrada = sc.nextLine();
			if (validarLetra(entrada)) {
				return entrada;
			}else{
				System.out.println("\nGenero invalido use somente letras e não deixe em branco");
			}
		}
	}

	public String showInfo(){
        return  
		 "NOME: " + this.nome + 
		 "\nAUTOR : " + this.autor +
		 "\nGÊNERO: " + this.genero + 
		 "\nANO DE LANÇAMENTO: "+ this.anoLancamento + 
		 "\nPREÇO: R$ "+ this.preco +",00" +
		 "\nDATA DA ULTIMA COMPRA: " + this.dataCompra + 
		 "\nQUANTIDADE: "+ this.quantidade +
         "\n-----------------------------";
		  
    }

	@Override
	public String toString(){
        return  
		 "ID: "+ this.id +
		 "\nNOME: " + this.nome + 
		 "\nAUTOR : " + this.autor +
		 "\nGÊNERO: " + this.genero + 
		 "\nANO DE LANÇAMENTO: "+ this.anoLancamento + 
		 "\nPREÇO: R$ "+ this.preco +",00" +
		 "\nDATA DA ULTIMA COMPRA: " + this.dataCompra + 
		 "\nQUANTIDADE: "+ this.quantidade +
         "\n-----------------------------";
		  
    }

}
package dtilivraria;
import java.util.List;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {
		try {
            Conexao dbCon = new Conexao("LivrariaDTI.db");
			dbCon.initDB(); 
            boolean menu = true;
            Scanner sc = new Scanner(System.in);
            while (menu == true) {
                System.out.println("\n=== MENU ===");
                System.out.println("1 Novo Cadastro de livro;");
                System.out.println("2 Listar todos os livros cadastrados");
                System.out.println("3 Procurar um livro por ID");
                System.out.println("4 Editar um livro");
                System.out.println("5 Remover cadastro de livro");
                System.out.println("6 Comprar um livro");
                System.out.println("7 Mostrar log de compras");
                System.out.println("8 Sair ");
                System.out.println("----------------------------");
                System.out.print("Escolha uma opcao: ");
                String opt = sc.nextLine();        
                switch (opt) {
                    case "1":
                    Dados dados = new Dados(null,null,null,null,null,null,null,null);
                        dados.setNome(Dados.lerNomeValido(sc));
                        dados.setAutor(Dados.lerAutorValido(sc));
                        dados.setGenero(Dados.lerGeneroValido(sc));
                        dados.setAnoLancamento(Dados.lerAnoValido(sc));
                        dados.setPreco(Dados.lerPrecoValido(sc));
                        dados.setQuantidade(Dados.lerQuantidadeValida(sc));
                        System.out.println("\n\n Deseja realmente salvar os dados \n ");
                        System.out.println("\n=== INFORMAÇÕES DO LIVRO ===");
                        System.out.println(dados.showInfo());
                        System.out.print("\n\n [s/n]: ");
                        String r = sc.nextLine();
                        if (r.toLowerCase().equals("s")){
                            dbCon.inserir(dados);
                            System.out.println("\n\t Livro cadastrado com Sucesso");
                        }
                        else{
                            dbCon.apagar(dados.getId());
                            System.out.println("\n\t Cancelamento do cadastro concluido");
                        }
                        break;

                    case "2":
                        listarLivros(dbCon);
                        break;
                    case "3":
                        String resp;
                        System.out.println("Qual é o ID do livro que procura?");
                        resp = sc.nextLine();
                        Dados dados2 = dbCon.pegar(resp);
                        if(dados2.getId() != null){
                            System.out.println("\n=== INFORMAÇÕES DO LIVRO ===");
                            System.out.println(dados2.toString());
                        }else{
                            System.out.println("\tCadastro não Localizado.");
                        }
                        break;
                    case "4":
                        String i,r2;
                        System.out.println("\n Editar um cadastro ");
                        System.out.print("Digite o id do cadastro para editar: ");
                        i = sc.nextLine();
                        Dados dados3 = dbCon.pegar(i);
                        if (dados3.getId() != null){
                            Dados dadosN = new Dados(null,null,null,null,null,null,null,null);
                            dadosN.setNome(Dados.lerNomeValido(sc));
                            dadosN.setAutor(Dados.lerAutorValido(sc));
                            dadosN.setGenero(Dados.lerGeneroValido(sc));
                            dadosN.setAnoLancamento(Dados.lerAnoValido(sc));
                            dadosN.setPreco(Dados.lerPrecoValido(sc));
                            dadosN.setQuantidade(Dados.lerQuantidadeValida(sc));
                            dadosN.setDataCompra(dados3.getDataCompra());
                            dadosN.setId(i);
                            System.out.println("\n Antes : \n");
                            System.out.println(dados3.toString());
                            System.out.println("\n Depois : \n");
                            System.out.println(dadosN.toString());
                            System.out.print("\n\n Deseja fazer essa alteração: [s/n]: ");
                            r2 = sc.nextLine();
                            if (r2.equals("s")){
                                dbCon.alterar(dadosN);
                                System.out.println("\n\tAlteração concluida");
                            }
                        }else{
                            System.out.println("\tCadastro não Localizado.");
                        }
                        break;
                    case "5":
                        System.out.println("Apagar um cadastro \n");
                        String i2,r3;
                        System.out.print("Digite o id do cadastro para apagar: ");
                        i2 = sc.nextLine();
                        Dados dados4 = dbCon.pegar(i2);
                        if (dados4.getId() != null){
                            System.out.print(dados4.toString());
                            System.out.print("\n Deseja realmente apagar? [s/n]: ");
                            r3 = sc.nextLine();
                            if (r3.toLowerCase().equals("s")){
                                dbCon.apagar(i2);
                                System.out.println("\n\tRemoção concluida");
                            }
                        } else {
                            System.out.println("Cadastro nao Localizado.");
                        }
                        break;
                    case "6":
                        System.out.print("ID do livro que deseja retirar: ");
                        int idLivro = Integer.parseInt(sc.nextLine());
                        System.out.print("Quantidade desejada: ");
                        int qtd = Integer.parseInt(sc.nextLine());
                        dbCon.retirarLivrosPorId(idLivro, qtd);
                        break;
                    case "7":
                        listarLogs(dbCon);
                        break;
                    case "8":
                        System.out.println("Sair ");
                        sc.close();
                        System.exit(0);
                        menu= false;
                    default:
                        System.out.println("Opcao Invalida \n\n ");
                        break;
                    }
            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void listarLogs(Conexao dbCon) {
        List<Log> logs = dbCon.listarLogs();
        System.out.println("\n=== HISTÓRICO DE COMPRAS ===");
        for (Log log : logs) {
            System.out.println(log);
        }
    }

    public static void listarLivros(Conexao dbCon) {
        List<Dados> dados = dbCon.listarLivros();
        System.out.println("\n=== ESTOQUE DE LIVROS ===");
        for (Dados datas : dados) {
            System.out.println(datas);
        }
    }

}
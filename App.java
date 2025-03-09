import java.sql.*;
import java.util.Scanner;

class Musica {
    private String titulo;
    private int duracao;
    private String artista;
    private String album;
    private String compositor;

    public Musica(String titulo, String artista, String album, String compositor, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.compositor = compositor;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getArtista() {
        return this.artista;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getCompositor() {
        return this.compositor;
    }

    public int getDuracao() {
        return this.duracao;
    }
}

public class App {
    // listar todas as músicas do banco
    public void listarMusicas(Statement statement) {
        try {
            System.out.println("\n==================== LISTA DE MÚSICAS ====================\n");
            ResultSet rs = statement.executeQuery("SELECT * FROM musicas");
            while (rs.next()) {
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Artista: " + rs.getString("artista"));
                System.out.println("Album: " + rs.getString("album"));
                System.out.println("Compositor: " + rs.getString("compositor"));
                System.out.println("Adicionada em: " + rs.getString("data"));
                System.out.println("Duração: " + rs.getInt("duracao") + " segundos");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("----------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // input de dados de uma música
    public Musica inserirMusica(Scanner sc) {
        Musica tmp;
        String titulo, artista, album, compositor;
        int duracao;

        sc.nextLine();

        do {
            System.out.print("\nTítulo: ");
            titulo = sc.nextLine();
            if (titulo.isEmpty() || titulo == "\n") {
                System.out.println("O título não pode ser deixado em branco. Por favor, insira um título válido.");
            }
        } while (titulo.isEmpty());

        do {
            System.out.print("\nArtista: ");
            artista = sc.nextLine();
            if (artista.isEmpty() || artista == "\n") {
                System.out.println("O artista não pode ser deixado em branco. Por favor, insira um artista válido.");
            }
        } while (artista.isEmpty());

        System.out.print("\nAlbum: ");
        album = sc.nextLine();

        do {
            System.out.print("\nDuração (em segundos): ");
            while (!sc.hasNextInt()) {
                System.out.println("Por favor, insira um número válido para a duração.");
                sc.next();
            }
            duracao = sc.nextInt();
            sc.nextLine();
            if (duracao <= 0) {
                System.out.println("A duração deve ser um número positivo. Por favor, insira uma duração válida.");
            }
        } while (duracao <= 0);

        System.out.print("\nEssa música tem compositor? (s/n): ");
        String resposta = sc.nextLine();
        if (resposta.equals("s")) {
            System.out.print("\nInsira o compositor: ");
            compositor = sc.nextLine();
            tmp = new Musica(titulo, artista, album, compositor, duracao);
        } else {
            tmp = new Musica(titulo, artista, album, "", duracao);
        }

        return tmp;
    }

    // cadastro de música no banco
    public void cadastrarMusica(Statement statement, Scanner sc) {
        Musica musica = inserirMusica(sc);
        try {
            statement.executeUpdate(
                    "INSERT INTO musicas (titulo, duracao, artista, album, compositor) VALUES ('"
                            + musica.getTitulo() + "', " + musica.getDuracao() + ", '" + musica.getArtista()
                            + "', '"
                            + musica.getAlbum() + "', '" + musica.getCompositor() + "')");
            System.out.println("\nMúsica cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // atualizar dados de uma música por id
    public void atualizarMusica(Statement statement, Scanner sc) {
        int id;

        System.out.print("\nInsira o ID da música que deseja atualizar: ");
        id = sc.nextInt();
        sc.nextLine();

        System.out.println("\nInsira os novos dados da música: ");
        Musica musica = inserirMusica(sc);

        try {
            statement.executeUpdate("UPDATE musicas SET titulo = '" + musica.getTitulo() + "', duracao = "
                    + musica.getDuracao() + ", artista = '" + musica.getArtista() + "', album = '"
                    + musica.getAlbum() + "', compositor = '" + musica.getCompositor() + "' WHERE id = " + id);
            System.out.println("\nMúsica atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // apagar música por id
    public void apagarMusica(Statement statement, Scanner sc) {
        int id;

        System.out.print("\nInsira o ID da música que deseja apagar: ");
        id = sc.nextInt();
        sc.nextLine();

        try {
            int rowsAffected = statement.executeUpdate("DELETE FROM musicas WHERE id = " + id);
            if (rowsAffected > 0) {
                System.out.println("\nMúsica apagada com sucesso!");
            } else {
                System.out.println("\nNenhuma música encontrada com o ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // buscar música por id
    public void buscarMusica(Statement statement, Scanner sc) {
        int id;

        System.out.print("\nInsira o ID da música que deseja buscar: ");
        id = sc.nextInt();
        sc.nextLine();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM musicas WHERE id = " + id);

            if (rs.next()) {
                System.out.println("\n==================== DETALHES DA MÚSICA ====================\n");
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Artista: " + rs.getString("artista"));
                System.out.println("Album: " + rs.getString("album"));
                System.out.println("Compositor: " + rs.getString("compositor"));
                System.out.println("Adicionada em: " + rs.getString("data"));
                System.out.println("Duração: " + rs.getInt("duracao") + " segundos");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("============================================================");
            } else {
                System.out.println("\nNenhuma música encontrada com o ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // seleção de opção do menu
    public int selecionarOpcao(Scanner sc) {
        int input = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println("\n==================== MENU ====================\n");
            System.out.println("1. Cadastrar música");
            System.out.println("2. Listar músicas");
            System.out.println("3. Buscar música por ID");
            System.out.println("4. Apagar música por ID");
            System.out.println("5. Atualizar música por ID");
            System.out.println("6. Sair");
            System.out.println("==============================================\n");
            System.out.print("Escolha uma opção: ");

            if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input >= 1 && input <= 6) {
                    valid = true;
                } else {
                    System.out.println("\nOpção inválida! Tente novamente.");
                }
            } else {
                System.out.println("\nEntrada inválida! Por favor, insira um número.");
                sc.next(); // descarta a entrada inválida
            }
        }

        return input;
    }

    public static void main(String[] args) {
        Connection connection = null;
        Scanner sc = new Scanner(System.in);
        App app = new App();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:musicas.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(20);

            System.out.println("====================== Biblioteca Virtual de Músicas =======================");
            System.out.println("\nSeja bem-vindo(a)!");

            int input;

            do {
                input = app.selecionarOpcao(sc);

                switch (input) {
                    case 1:
                        app.cadastrarMusica(statement, sc);
                        break;
                    case 2:
                        app.listarMusicas(statement);
                        break;
                    case 3:
                        app.buscarMusica(statement, sc);
                        break;
                    case 4:
                        app.apagarMusica(statement, sc);
                        break;
                    case 5:
                        app.atualizarMusica(statement, sc);
                        break;
                    case 6:
                        System.out.println("\n================== Até mais! ==================");
                        break;
                    default:
                        break;
                }
            } while (input != 6);

            sc.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                sc.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }
}
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
            System.out.println();
            System.out.println();
            System.out.println("Listando músicas...");
            System.out.println();
            ResultSet rs = statement.executeQuery("SELECT * FROM musicas");
            while (rs.next()) {
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Duração: " + rs.getInt("duracao"));
                System.out.println("Artista: " + rs.getString("artista"));
                System.out.println("Album: " + rs.getString("album"));
                System.out.println("Compositor: " + rs.getString("compositor"));
                System.out.println("Data: " + rs.getString("data"));
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println();
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

        System.out.println("Insira o título da música: ");
        titulo = sc.nextLine();

        System.out.println("Insira o artista: ");
        artista = sc.nextLine();

        System.out.println("Insira o album: ");
        album = sc.nextLine();

        System.out.println("Insira a duração da música: ");
        duracao = sc.nextInt();
        sc.nextLine();

        System.out.println("Essa música tem compositor? (s/n)");
        String resposta = sc.nextLine();
        if (resposta.equals("s")) {
            System.out.println("Insira o compositor: ");
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

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // todo atualizar dados de uma música por id

    // todo apagar música por id
    public void apagarMusica(Statement statement, Scanner sc) {
        int id;

        System.out.println("Insira o ID da música que deseja apagar: ");
        id = sc.nextInt();
        sc.nextLine();

        try {
            statement.executeUpdate("DELETE FROM musicas WHERE id = " + id);
            System.out.println("Música apagada com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    // buscar música por id
    public void buscarMusica(Statement statement, Scanner sc) {
        int id;

        System.out.println("Insira o ID da música que deseja buscar: ");
        id = sc.nextInt();
        sc.nextLine();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM musicas WHERE id = " + id);

            if (rs.next()) {
                System.out.println();
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Duração: " + rs.getInt("duracao"));
                System.out.println("Artista: " + rs.getString("artista"));
                System.out.println("Album: " + rs.getString("album"));
                System.out.println("Compositor: " + rs.getString("compositor"));
                System.out.println("Data: " + rs.getString("data"));
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println();
            } else {
                System.out.println("Nenhuma música encontrada com o ID: " + id);
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
            System.out.println("O que deseja fazer?");
            System.out.println("1. Cadastrar música");
            System.out.println("2. Listar músicas");
            System.out.println("3. Buscar música por ID");
            System.out.println("4. Apagar música por ID");
            System.out.println("5. Sair");
            sc.hasNext();

            if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (input >= 1 && input <= 4) {
                    valid = true;
                } else {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.next();
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

            System.out.println("Seja bem-vindo(a) a sua Biblioteca Virtual de Músicas!");

            // * código para criação da tabela
            // statement.executeUpdate("DROP TABLE IF EXISTS musicas");
            // statement.executeUpdate("CREATE TABLE musicas (id INTEGER PRIMARY KEY
            // AUTOINCREMENT, titulo STRING NOT NULL, duracao INTEGER NOT NULL, artista
            // STRING NOT NULL, album STRING, compositor STRING, data DATETIME DEFAULT
            // CURRENT_TIMESTAMP)");
            // statement.executeUpdate( "INSERT INTO musicas (titulo, duracao, artista,
            // album, compositor) VALUES('musica 1', 120, 'artista 1', 'album 1',
            // 'compositor 1')");

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
                        System.out.println("Até mais!");
                        break;
                    default:
                        break;
                }
            } while (input != 5);

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
                // Falhou também para fechar o arquivo
                System.err.println(e.getMessage());
            }
        }

    }
}

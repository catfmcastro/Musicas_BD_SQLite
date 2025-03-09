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
    public Musica inserirMusica() {
        Scanner sc = new Scanner(System.in);
        Musica tmp;

        System.out.println("Insira o título da música: ");
        String titulo = sc.nextLine();

        System.out.println("Insira o artista: ");
        String artista = sc.nextLine();

        System.out.println("Insira o album: ");
        String album = sc.nextLine();

        System.out.println("Insira a duração da música: ");
        int duracao = sc.nextInt();

        System.out.println("Essa música tem compositor? (s/n)");
        String resposta = sc.nextLine();
        if (resposta.equals("s")) {
            System.out.println("Insira o compositor: ");
            String compositor = sc.nextLine();
            tmp = new Musica(titulo, artista, album, compositor, duracao);
        } else {
            tmp = new Musica(titulo, artista, album, "", duracao);
        }


        sc.close();
        return tmp;
    }

    public void cadastrarMusica(Musica musica, Statement statement) {
        try {
            statement.executeUpdate(
                    "INSERT INTO musicas (titulo, duracao, artista, album, compositor) VALUES ('"
                            + musica.getTitulo() + "', " + musica.getDuracao() + ", '" + musica.getArtista() + "', '"
                            + musica.getAlbum() + "', '" + musica.getCompositor() + "')");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void listarMusicas (Statement statement) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM musicas");
            while (rs.next()) {
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Duração: " + rs.getInt("duracao"));
                System.out.println("Artista: " + rs.getString("artista"));
                System.out.println("Album: " + rs.getString("album"));
                System.out.println("Compositor: " + rs.getString("compositor"));
                System.out.println("Data: " + rs.getString("data"));
                System.out.println("Id: " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:musicas.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(20);

            System.out.println("Seja bem-vindo(a) a sua Biblioteca Virtual de Músicas!");

            // * código para criação da tabela
            // statement.executeUpdate("DROP TABLE IF EXISTS musicas");
            // statement.executeUpdate(
            // "CREATE TABLE musicas (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo STRING
            // NOT NULL, duracao INTEGER NOT NULL, artista STRING NOT NULL, album STRING,
            // compositor STRING, data DATETIME DEFAULT CURRENT_TIMESTAMP)");
            // statement.executeUpdate(
            // "INSERT INTO musicas (titulo, duracao, artista, album, compositor) VALUES
            // ('musica 1', 120, 'artista 1', 'album 1', 'compositor 1')");

            listarMusicas(statement);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Falhou também para fechar o arquivo
                System.err.println(e.getMessage());
            }
        }

    }
}

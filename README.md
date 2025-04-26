# Biblioteca Virtual de Músicas

A Biblioteca Virtual de Músicas é um banco de dados desenvolvido com Java e SQLite para o cadastro, consulta e armazenamento de informações sobre músicas.

## Recursos

O recurso escolhido foi Música. O banco possui apenas uma tabela “musicas” com 7 atributos. São eles:
- ID: identificador, número inteiro e chave primária.
- Título: título da música, string, atributo obrigatório.
- Duração: duração da música em segundos, número inteiro, atributo obrigatório.
- Artista: artista intérprete da música, string, atributo obrigatório.
- Álbum: álbum do qual a música faz parte, string, atributo opcional.
- Compositor: compositor da música, string, atributo opcional.
- Data: data de cadastro ou atualização da música no banco, datetime, atributo default (o usuário não pode fazer alterações).

## Tecnologias Utilizadas 

As tecnologias utilizadas para o desenvolvimento da aplicação foram:
- Linguagem Java
- Biblioteca SQLite JDBC
- Visual Studio Code

## Instruções de Execução da Aplicação

A dependência utilizada foi a biblioteca SQLite JDBC, que pode ser instalada e configurada segundo as instruções abaixo.
1. Fazer o download do SQLite JDBC por meio do repositório “sqlite-jdbc”, (clique aqui:
https://github.com/xerial/sqlite-jdbc/releases). A versão utilizada é 3.49.0.1.
2. Adicionar o arquivo .jar como biblioteca referenciada no projeto.

Uma vez que a biblioteca já foi configurada, é possível executar a aplicação. Para fazer isso por meio do Visual Studio Code, basta seguir as instruções abaixo:
1. Abrir o diretório “Musicas_BD_SQLite” pelo Visual Studio Code. 2. Instalar a extensão “Extension Pack for Java”, da Microsoft.
3. No canto inferior esquerdo da tela, na aba “Java Projects”, clicar no ícone + ao lado do
diretório “Referenced Libraries” e adicionar o .jar do SQLite JDBC. Isso permitirá que o SQLite seja utilizado na aplicação.
4. Compilar e executar o projeto.

Caso o projeto seja compilado diretamente pelo console, é muito importante que as versões do JDK e do JRE estejam atualizadas e sejam compatíveis para mesmas versões de classfiles, caso contrário a exceção java.lang.UnsuportedClassVersionError será lançada.

## Instruções de Uso da Aplicação A Biblioteca é executada no terminal. 

Para utilizar a aplicação, insira o número da opção escolhida e pressione a tecla ENTER. As funcionalidades presentes são:
- Listar todas as músicas existentes: essa opção exibe no terminal todas as instâncias atualmente armazenadas no banco de dados.
- Cadastrar uma nova música: ao selecionar essa opção, o usuário será instruído a inserir os atributos da música. Os atributos obrigatórios possuem uma camada de proteção que impede que eles sejam deixados em branco. A música cadastrada receberá um novo ID autoincrementado e uma data, correspondente a data e a hora em que ela foi adicionada ao banco.
- Buscar música existente por meio do ID: ao selecionar essa opção, o usuário será instruído a inserir o ID da música que deseja buscar. Se o ID for válido e existir no banco, as informações da música serão exibidas no terminal.
- Atualizar música existente por meio do ID: ao selecionar essa opção, o usuário deve inserir o ID da música desejada. De maneira similar ao cadastro de músicas, o usuário será instruído a inserir todos os atributos da música que será atualizada. O ID se manterá o mesmo, mas a data será atualizada automaticamente para a data e hora de atualização.
- Apagar música existente por meio do ID: ao selecionar essa opção, o usuário deve
- inserir o ID da música desejada. Se o ID for válido e existir no banco, a instância será apagada.
  
## Testes Unitários
Dois arquivos de testes (do tipo .txt, localizados no diretório “testes”) podem ser utilizados para testar as funcionalidades da aplicação. Para utilizá-los, basta inserir os valores linha a linha no terminal enquanto a aplicação está sendo executada.

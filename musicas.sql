-- CREATE TABLE
--     musicas (
--         id INTEGER PRIMARY KEY AUTOINCREMENT,
--         titulo STRING NOT NULL,
--         duracao INTEGER NOT NULL,
--         artista STRING NOT NULL,
--         album STRING,
--         compositor STRING,
--         data DATETIME DEFAULT CURRENT_TIMESTAMP
--     );
--
INSERT INTO
    musicas (titulo, duracao, artista, album, compositor)
VALUES
    (
        'Apesar de Você',
        235,
        'Chico Buarque',
        'Chico Buarque',
        'Chico Buarque'
    ),
    (
        'Everything In Its Right Place',
        251,
        'Radiohead',
        'Kid A',
        'Colin Greenwood, Ed O''Brien, Jonny Greenwood, Philip Selway, Thom Yorke'
    ),
    (
        'Paranoid Android',
        387,
        'Radiohead',
        'OK Computer',
        'Colin Greenwood, Ed O''Brien, Jonny Greenwood, Philip Selway, Thom Yorke'
    ),
    (
        'Canoa, Canoa',
        242,
        'Milton Nascimento',
        'Clube da Esquina',
        'Nelson Angelo, Fernando Brant'
    )
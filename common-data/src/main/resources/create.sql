
--插件数据表
CREATE TABLE IF NOT EXISTS PluginData (
    id INTEGER PRIMARY KEY,
    isIndex INTEGER NOT NULL
);

--岛屿表
CREATE TABLE IF NOT EXISTS IslandData (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    pos INTEGER NOT NULL,
    uuid TEXT NOT NULL,
    authorityMap BLOB NOT NULL
);
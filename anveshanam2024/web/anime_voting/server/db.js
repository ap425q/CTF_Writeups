const sqlite3 = require("sqlite3").verbose();
const db = new sqlite3.Database("anime.sqlite3");

const animeList = [
  { image: "https://i.imgur.com/7k5ZzLg.jpeg", name: "AOT", votes: 18 },
  { image: "https://i.imgur.com/oyHSigK.jpeg", name: "Death Note", votes: 19 },
  { image: "https://i.imgur.com/CIlosJd.jpeg", name: "One Piece", votes: 17 },
  { image: "https://i.imgur.com/IK4FGby.png", name: "Naruto", votes: 15 },
  { image: "https://i.imgur.com/CZoi6Ay.png", name: "Demon Slayer", votes: 16 },
  { image: "https://i.imgur.com/gfrCH0w.jpeg", name: "Tokyo Ghoul", votes: 12 },
  {
    image: "https://i.imgur.com/fKO5uyj.jpeg",
    name: "Hunter x Hunter",
    votes: 14,
  },
  {
    image: "https://i.imgur.com/TZEvTev.jpeg",
    name: "Jujutsu Kaisen",
    votes: 10,
  },
  { image: "https://i.imgur.com/WZ2WJu7.jpeg", name: "Code Geass", votes: 13 },
  { image: "https://i.imgur.com/7ZXsXLa.jpeg", name: "Blue Lock", votes: 9 },
  {
    image: "https://i.imgur.com/BK5IWqp.jpeg",
    name: "Dragon Ball Z",
    votes: 11,
  },
  {
    image: "https://i.imgur.com/6jw1tBK.jpeg",
    name: "Fullmetal Alchemist: Brotherhood",
    votes: 30,
  },
  {
    image: "https://i.imgur.com/acpEgJ2.jpeg",
    name: "Mob Psycho 100",
    votes: 8,
  },
];

const flagTableName = `flag${Math.random().toString(36).substring(2, 7)}`;

const dbRunAsync = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.run(sql, params, (err) => {
      if (err) {
        reject(err);
      } else {
        resolve();
      }
    });
  });
};

const dbGetAsync = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.get(sql, params, (err, row) => {
      if (err) {
        reject(err);
      } else {
        resolve(row);
      }
    });
  });
};

const initialize = async () => {
  try {
    await dbRunAsync("DROP TABLE IF EXISTS anime");
    await dbRunAsync("DROP TABLE IF EXISTS flag");

    await dbRunAsync(
      `CREATE TABLE anime (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        image TEXT,
        name TEXT,
        votes INTEGER
      )`
    );

    await dbRunAsync(
      `CREATE TABLE ${flagTableName} (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        flag TEXT
      )`
    );

    const animeStmt = db.prepare(
      "INSERT INTO anime (image, name, votes) VALUES (?, ?, ?)"
    );
    animeList.forEach((anime) => {
      animeStmt.run(anime.image, anime.name, anime.votes, (err) => {
        if (err) {
          throw err;
        }
      });
    });

    const flagStmt = db.prepare(
      `INSERT INTO ${flagTableName} (flag) VALUES (?)`
    );
    flagStmt.run(process.env.FLAG, (err) => {
      if (err) {
        throw err;
      }
    });

    await new Promise((resolve, reject) => {
      animeStmt.finalize((err) => {
        if (err) {
          reject(err);
        } else {
          resolve();
        }
      });
    });

    await new Promise((resolve, reject) => {
      flagStmt.finalize((err) => {
        if (err) {
          reject(err);
        } else {
          resolve();
        }
      });
    });

    console.log("Database Initialization Successful!");
  } catch (err) {
    console.error("Error initializing the database:", err.message);
  }
};

const getAnimeVotes = async (animeName) => {
  const row = await dbGetAsync("SELECT votes FROM anime WHERE name = ?", [
    animeName,
  ]);
  return row ? row.votes : null;
};

const incrementVotes = async (animeName) => {
  const currentVotes = await getAnimeVotes(animeName);

  if (currentVotes === null) {
    console.error(`Anime '${animeName}' does not exist in the database.`);
    return { success: false, message: "Anime does not exist" };
  }

  if (currentVotes >= 100) {
    console.error(`Anime '${animeName}' has reached the maximum vote count.`);
    return { success: false, message: "Maximum votes reached" };
  }

  await dbRunAsync("UPDATE anime SET votes = votes + 1 WHERE name = ?", [
    animeName,
  ]);

  return { success: true, message: `Vote incremented for anime: ${animeName}` };
};

const vote = async (animeName) => {
  try {
    const currentVotes = await getAnimeVotes(animeName);

    if (currentVotes === null) {
      const errorMessage = `Anime '${animeName}' does not exist in the database.`;
      return { success: false, status: 404, message: errorMessage };
    }

    if (currentVotes >= 100) {
      const errorMessage = `Anime '${animeName}' has reached the maximum vote count.`;
      return { success: false, status: 400, message: errorMessage };
    }

    await dbRunAsync("UPDATE anime SET votes = votes + 1 WHERE name = ?", [
      animeName,
    ]);

    const successMessage = `Vote incremented for anime: ${animeName}`;
    return { success: true, status: 200, message: successMessage };
  } catch (err) {
    console.error("Error during vote operation:", err.message);
    return { success: false, status: 500, message: "Internal Server Error" };
  }
};

module.exports = {
  db,
  initialize,
  vote,
};

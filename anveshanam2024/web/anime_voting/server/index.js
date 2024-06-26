const express = require("express");
const { initialize } = require("./db.js");
const path = require("path");

const animeRoutes = require("./routes/animeRoutes");
const voteRoutes = require("./routes/voteRoutes");

const app = express();
app.use(express.json());

const PORT = 5000;

app.use(animeRoutes);
app.use(voteRoutes);

// Serving Frontend!
app.use(express.static(path.join(__dirname, "client-build")));
app.get("*", (req, res) => {
  res.sendFile(path.join(__dirname, "client-build", "index.html"));
});

const startServer = async () => {
  await initialize();
  app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
};

startServer();

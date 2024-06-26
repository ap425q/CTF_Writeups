const express = require("express");
const { vote } = require("../db.js");

const router = express.Router();

router.post("/vote", async (req, res) => {
  const { animeName } = req.body;

  if (!animeName) {
    return res.status(400).json({ error: "Anime name is required" });
  }

  const result = await vote(animeName);

  if (!result.success) {
    return res.status(result.status).json({ error: result.message });
  }

  res.status(result.status).json({ message: result.message });
});

module.exports = router;

const express = require("express");
const { db } = require("../db.js");

const router = express.Router();

// Fix this ASAP!
router.get("/anime", (req, res) => {
  const { order } = req.query;

  if (!order) {
    return res.status(400).json({ error: "Order is required!" });
  }

  db.all(`SELECT * FROM anime ORDER BY ${order}`, (err, rows) => {
    if (err) {
      return res
        .status(500)
        .json({ error: "Internal Server Error", details: err.message });
    }

    res.status(200).json(rows);
  });
});




module.exports = router;

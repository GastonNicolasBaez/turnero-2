const express = require('express');
const cors = require('cors');
const app = express();
const port = 3000;

app.use(cors()); // Para permitir solicitudes de otros orígenes
app.use(express.json());

// Variables para el ticket actual y el box
let currentTicket = 1;
let currentBox = 1;

// Ruta para obtener el ticket actual y el box
app.get('/ticket', (req, res) => {
    res.json({ ticket: currentTicket, box: currentBox });
});

// Ruta para avanzar al siguiente ticket
app.post('/next', (req, res) => {
    currentTicket++;
    res.json({ ticket: currentTicket, box: currentBox });
});

// Ruta para modificar el ticket manualmente
app.post('/set-ticket', (req, res) => {
    const { ticket, box } = req.body;
    currentTicket = ticket || currentTicket;
    currentBox = box || currentBox;
    res.json({ message: "Ticket actualizado", ticket: currentTicket, box: currentBox });
});

// Iniciar el servidor
app.listen(port, () => {
    console.log(`Servidor escuchando en http://localhost:${port}`);
});

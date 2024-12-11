import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Button, Container, Paper} from "@mui/material";
import {useEffect, useState} from "react";

export default function Configuration() {
    const paperStyle = {padding: '50px 20px', width: 400, margin: "20px auto"};
    const [totalTickets, setTotalTickets] = useState('');
    const [maxTicketCapacity, setMaxTicketCapacity] = useState('');
    const [ticketReleaseRate, setTicketReleaseRate] = useState('');
    const [customerRetrievalRate, setCustomerRetrievalRate] = useState('');
    const [logs, setLogs] = useState([]);
    const [isRunning, setIsRunning] = useState(false);
    const [intervalId, setIntervalId] = useState(null);

    const handleClick = (e) => {
        e.preventDefault()
        const ticket = {
            totalTickets: parseInt(totalTickets),
            maxTicketCapacity: parseInt(maxTicketCapacity),
            ticketReleaseRate: parseFloat(ticketReleaseRate),
            customerRetrievalRate: parseFloat(customerRetrievalRate),
        };
        console.log("Sending ticket configuration: ", ticket);

        fetch("http://localhost:8080/api/configuration", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ticket),
        })
            .then(() => {
                alert("Configuration added successfully!");
            })
            .catch(() => {
                alert("Error submitting configuration!");
            });
    }
    useEffect(() => {
        if (isRunning) {
            const interval = setInterval(() => {
                fetchLogs();
            }, 1000);
            return () => clearInterval(interval);
        }
    }, [isRunning]);

    const fetchLogs = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/logs");
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setLogs(data);  // Update the logs state with the fetched logs
        } catch (error) {
            console.error("Error fetching logs:", error);
        }
    };

    const handleStart = () => {
        setIsRunning(true);
    };

    const handleStop = () => {
        setIsRunning(false);
        if (intervalId) {
            clearInterval(intervalId); // Clear the interval
            setIntervalId(null);
        }
    };


    return (
        <Container>
            <Paper elevation={3} style={paperStyle}>
                <Box
                    component="form"
                    sx={{'& > :not(style)': {m: 1}}}
                    noValidate
                    autoComplete="off"
                >
                    <h1 style={{color: "#1591ea", fontSize: 25, textAlign: "left"}}>Enter Total Number of
                        Tickets</h1>
                    <TextField id="outlined-basic" label="totalTickets" variant="outlined" fullWidth
                               value={totalTickets}
                               onChange={(e) => setTotalTickets(e.target.value)}/>

                    <h1 style={{color: "#1591ea", fontSize: 25, textAlign: "left"}}>Enter Maximum Ticket
                        Capacity</h1>
                    <TextField id="outlined-basic" label="maxTicketCapacity" variant="outlined" fullWidth
                               value={maxTicketCapacity}
                               onChange={(e) => setMaxTicketCapacity(e.target.value)}/>

                    <h1 style={{color: "#1591ea", fontSize: 25, textAlign: "left"}}>Enter Ticket Release Rate</h1>
                    <TextField id="outlined-basic" label="ticketReleaseRate" variant="outlined" fullWidth
                               value={ticketReleaseRate}
                               onChange={(e) => setTicketReleaseRate(e.target.value)}/>

                    <h1 style={{color: "#1591ea", fontSize: 25, textAlign: "left"}}>Enter Customer Retrieval
                        Rate</h1>
                    <TextField id="outlined-basic" label="customerRetrievalRate" variant="outlined" fullWidth
                               value={customerRetrievalRate}
                               onChange={(e) => setCustomerRetrievalRate(e.target.value)}/>
                    <br/>
                    <Button variant="contained" color="success" onClick={handleClick}>
                        Submit Configuration
                    </Button>
                </Box>
            </Paper>

            <Paper elevation={3} style={paperStyle}>
                <Box component="form" sx={{ '& > :not(style)': { m: 1 } }} noValidate autoComplete="off">
                    <h1 style={{ color: "#1591ea", fontSize: 25, textAlign: "center" }}>Logs</h1>
                    <div style={{ maxHeight: 500, overflow: 'auto' }}>
                        {logs.map((log, index) => (
                            <p key={index}>{log}</p>
                        ))}
                    </div>
                    <Button variant="contained" color="success" onClick={handleStart}>
                        Start
                    </Button>
                    <Button variant="contained" color="error" onClick={handleStop}>
                        Stop
                    </Button>
                </Box>
            </Paper>
        </Container>
    );
}

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
    const [intervalId] = useState(null);

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
            .then((response) => {
                // Check if the response is OK
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                // Parse the JSON response
                return response.json();
            })
            .then((data) => {
                // If the response is valid JSON, log it and alert the user
                alert('Configuration received successfully!');
                console.log(data);
            })
            .catch((error) => {
                // Log any errors
                // console.error('Error sending configuration:', error);
            });
    }

    useEffect(() => {
        let interval;

        if (isRunning) {
            interval = setInterval(() => {
                fetchLogs();
            }, 1000);
        } else {
            if (interval) {
                clearInterval(interval);  // Clear interval on stop
            }
        }
        return () => clearInterval(interval);  // Cleanup interval on unmount or stop
    }, [isRunning]);

    const fetchLogs = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/logs");
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log("Fetched logs:", data);  // For debugging, check the logs here
            setLogs(data);  // Update the logs state with the fetched logs
        } catch (error) {
            console.error("Error fetching logs:", error);
        }
    };

    const handleStart = () => {
        fetch("http://localhost:8080/api/start", {
            method: 'POST',
        })
            .then(response => {
                if (response.ok) {
                    alert("Application started successfully!");
                    setIsRunning(true);
                } else {
                    alert("Failed to start the application.");
                }
            })
            .catch(error => {
                alert("Error starting application: " + error.message);
            });
    };

    const handleStop = () => {
        fetch("http://localhost:8080/api/stop", {
            method: 'POST',
        })
            .then(response => {
                if (response.ok) {
                    alert("Application stopped successfully!");
                    setIsRunning(false);
                    clearInterval(intervalId);  // Clear the log polling interval
                }
                else {
                    alert("Failed to stop the application.");
                }
            })
            .catch(error => {
                alert("Error stopping application: " + error.message);
            });
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
                <Box
                    component="form"
                    sx={{ '& > :not(style)': { m: 1 } }}
                    noValidate
                    autoComplete="off"
                >
                    <Button variant="contained" color="success" onClick={handleStart}>
                        Start
                    </Button>
                    <Button variant="contained" color="error" onClick={handleStop}>
                        Stop
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
                </Box>
            </Paper>
        </Container>
    );
}

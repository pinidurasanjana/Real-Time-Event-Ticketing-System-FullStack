import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ConfigurationForm from './ConfigurationForm';
import TicketDisplay from './TicketDisplay';
import ControlPanel from './ControlPanel';
import LogDisplay from './LogDisplay';

const App = () => {
    const [availableTickets, setAvailableTickets] = useState(1000);
    const [logs, setLogs] = useState([]);
    const [pollingInterval, setPollingInterval] = useState(null);

    // Function to handle configuration form submission
    const handleSubmitConfiguration = (totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity) => {
        axios.post('http://localhost:8080/api/configuration', {
            totalTickets,
            ticketReleaseRate,
            customerRetrievalRate,
            maxTicketCapacity
        }).then(() => {
            setLogs([...logs, `Configuration updated: Tickets - ${totalTickets}, Release Rate - ${ticketReleaseRate}`]);
        }).catch(err => {
            console.error("Error updating configuration", err);
        });
    };

    // Start polling for ticket data
    const startPolling = () => {
        const intervalId = setInterval(() => {
            axios.get('http://localhost:8080/api/tickets')
                .then(response => {
                    setAvailableTickets(response.data);
                    setLogs((prevLogs) => [...prevLogs, 'Polling tickets data...']);
                })
                .catch(error => {
                    console.error('Error fetching ticket data:', error);
                });
        }, 5000); // Poll every 5 seconds

        setPollingInterval(intervalId);
    };

    // Stop polling
    const stopPolling = () => {
        clearInterval(pollingInterval);
        setLogs((prevLogs) => [...prevLogs, 'Polling stopped']);
    };

    useEffect(() => {
        return () => {
            if (pollingInterval) clearInterval(pollingInterval);
        };
    }, [pollingInterval]);

    return (
        <div>
            <h1>Ticket Management System</h1>
            <ConfigurationForm onSubmit={handleSubmitConfiguration} />
            <TicketDisplay availableTickets={availableTickets} />
            <ControlPanel onStart={startPolling} onStop={stopPolling} />
            <LogDisplay logs={logs} />
        </div>
    );
};

export default App;

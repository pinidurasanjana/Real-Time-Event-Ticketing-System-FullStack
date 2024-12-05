import React, { useState } from 'react';

const ConfigurationForm = ({ onSubmit }) => {
    const [totalTickets, setTotalTickets] = useState(1000);
    const [ticketReleaseRate, setTicketReleaseRate] = useState(10);
    const [customerRetrievalRate, setCustomerRetrievalRate] = useState(5);
    const [maxTicketCapacity, setMaxTicketCapacity] = useState(500);

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Total Tickets:</label>
                <input
                    type="number"
                    value={totalTickets}
                    onChange={(e) => setTotalTickets(e.target.value)}
                />
            </div>
            <div>
                <label>Ticket Release Rate:</label>
                <input
                    type="number"
                    value={ticketReleaseRate}
                    onChange={(e) => setTicketReleaseRate(e.target.value)}
                />
            </div>
            <div>
                <label>Customer Retrieval Rate:</label>
                <input
                    type="number"
                    value={customerRetrievalRate}
                    onChange={(e) => setCustomerRetrievalRate(e.target.value)}
                />
            </div>
            <div>
                <label>Max Ticket Capacity:</label>
                <input
                    type="number"
                    value={maxTicketCapacity}
                    onChange={(e) => setMaxTicketCapacity(e.target.value)}
                />
            </div>
            <button type="submit">Submit Configuration</button>
        </form>
    );
};

export default ConfigurationForm;

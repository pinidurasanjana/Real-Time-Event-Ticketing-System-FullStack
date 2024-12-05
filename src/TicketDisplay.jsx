import React from 'react';

const TicketDisplay = ({ availableTickets }) => {
    return (
        <div>
            <h2>Available Tickets: {availableTickets}</h2>
        </div>
    );
};

export default TicketDisplay;

import React from 'react';

const ControlPanel = ({ onStart, onStop }) => {
    return (
        <div>
            <button onClick={onStart}>Start</button>
            <button onClick={onStop}>Stop</button>
        </div>
    );
};

export default ControlPanel;

import React from 'react';

const EndPoint = ({ endPoint, onChange }) => {
  return (
    <div>
      <label htmlFor="endPoint">도착지: </label>
      <input
        type="text"
        id="endPoint"
        name="endPoint"
        value={endPoint}
        onChange={onChange}
      />
    </div>
  );
};

export default EndPoint;

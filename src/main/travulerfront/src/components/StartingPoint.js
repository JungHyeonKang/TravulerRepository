import React from 'react';

const StartingPoint = ({ startingPoint, onChange }) => {
  return (
    <div>
      <label htmlFor="startingPoint">출발지:</label>
      <input
        type="text"
        id="startingPoint"
        name="startingPoint"
        value={startingPoint}
        onChange={onChange}
      />
    </div>
  );
};

export default StartingPoint;

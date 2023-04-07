import React, { useState } from "react";
import StartingPoint from "./StartingPoint";
import EndPoint from "./EndPoint";
import Waypoint from "./Waypoint";
import "../css/TravelPlans.css";



const TravelPlans = ({ index, travelPlan, onChange, onDelete, showPlansDetail }) => {
  const [waypoints, setWaypoints] = useState(travelPlan.waypoints.split(","));

  const handleWaypointChange = (event, index) => {
    const newWaypoints = [...waypoints];
    newWaypoints[index] = event.target.value;
    setWaypoints(newWaypoints);
    onChange({
      ...travelPlan,
      waypoints: newWaypoints.join(","),
    });
  };

  const handleAddWaypoint = () => {
    const newWaypoints = [...waypoints, ""];
    setWaypoints(newWaypoints);
    onChange({
      ...travelPlan,
      waypoints: newWaypoints.join(","),
    });
  };

  const handleDeleteWaypoint = () => {
    const newWaypoints = waypoints.slice(0, -1);
    setWaypoints(newWaypoints);
    onChange({
      ...travelPlan,
      waypoints: newWaypoints.join(","),
    });
  };

  const handleDeleteTravelPlan = () => {
    onDelete(travelPlan.day);
  };

  const waypointComponents = waypoints.map((waypoint, index) => (
    <Waypoint
      key={`waypoint${index + 1}`}
      index={index}
      waypoint={waypoint}
      onChange={(event) => handleWaypointChange(event, index)}
      onDelete={handleDeleteWaypoint}
    />
  ));

  const travelPlanKey = `day${index + 1}`;

  return (
    <div key={travelPlanKey} id={travelPlanKey} className="day-box">
      <button className="day-detailViewButton" onClick={() => showPlansDetail(index)}>세부계획</button>
      <h3>{travelPlanKey}</h3>
      <button onClick={handleAddWaypoint}>경유지 추가</button>
      <button onClick={handleDeleteWaypoint}>경유지 삭제</button>
      <StartingPoint
        startingPoint={travelPlan.startingPoint}
        onChange={(event) =>
          onChange({ ...travelPlan, startingPoint: event.target.value })
        }
      />
      
      {waypointComponents}
      <EndPoint
        endPoint={travelPlan.endPoint}
        onChange={(event) =>
          onChange({ ...travelPlan, endPoint: event.target.value })
        }
      />
      <button onClick={handleDeleteTravelPlan}>삭제</button>
    </div>
  );
};

export default TravelPlans;

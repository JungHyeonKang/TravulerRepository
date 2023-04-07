import "../css/TravelPlans.css";
import Map from "./Map";

const PlansDetail = ({ travelPlans, plansDetailIndex }) => {
  const waypointsArr = travelPlans[plansDetailIndex].waypoints.split(",");

  return (
    <div className="day-detail">
      <h3 className="plan-title">
        {travelPlans[plansDetailIndex].day} 세부계획
      </h3>
      <br />
      <label htmlFor="startingPoint">출발지: </label>
      <input type="text" value={travelPlans[plansDetailIndex].startingPoint} />
      <br />
      <label htmlFor="departureDate">출발일: </label>
      <input type="date" />
      <br />
      <label htmlFor="departureTime">출발시간: </label>
      <input type="time" />
      <br />
      <label htmlFor="transportation">이동수단: </label>
      <select name="transportation">
        <option value="car">자동차</option>
        <option value="publicTransport">대중교통</option>
        <option value="airplane">비행기</option>
      </select>
      <br />
      <label htmlFor="memo">메모: </label>
      <input type="text" />
      <div>-----------------------------------</div>
      {waypointsArr.map((wp, index) => (
        <div key={index}>
          <br />
          <label htmlFor="startingPoint">경유지{index+1}: </label>
          <input type="text" key={index} value={wp} />
          <br />
          <label htmlFor="departureTime">도착시간: </label>
          <input type="time" />
          <br />
          <label htmlFor="purpose">목적: </label>
          <input type="text" />
          <br />
          <label htmlFor="transportation">다음 이동수단: </label>
          <select name="transportation">
            <option value="car">자동차</option>
            <option value="publicTransport">대중교통</option>
            <option value="airplane">비행기</option>
          </select>
          <br />
          <label htmlFor="departureTime">출발시간: </label>
          <input type="time" />
          <br />
          <label htmlFor="memo">메모: </label>
          <input type="text" />
        </div>
      ))}
      <div>-----------------------------------</div>
      <br />
      <label htmlFor="startingPoint">도착지: </label>
      <input type="text" value={travelPlans[plansDetailIndex].endPoint} />
      <br />
      <label htmlFor="departureDate">도착일: </label>
      <input type="date" />
      <br />
      <label htmlFor="departureTime">도착시간: </label>
      <input type="time" />
      <br />
      <label htmlFor="transportation">이동수단: </label>
      <select name="transportation">
        <option value="car">자동차</option>
        <option value="publicTransport">대중교통</option>
        <option value="airplane">비행기</option>
      </select>
      <br />
      <label htmlFor="memo">메모: </label>
      <input type="text" />
      <Map />
    </div>
  );
};

export default PlansDetail;

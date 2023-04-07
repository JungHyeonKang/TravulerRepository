import React, { useEffect } from "react";

function Map() {
  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new window.kakao.maps.LatLng(37.5665, 126.978),
      level: 8,
    };
    const map = new window.kakao.maps.Map(container, options);

    const startMarker = new window.kakao.maps.Marker({ map });
    const endMarker = new window.kakao.maps.Marker({ map });

    // 출발지, 경유지, 도착지, 이동수단, 출발시간을 입력.
    const start = "삼가역";
    const waypoint = "수원역";
    const end = "단우빌딩";
    const transType = "t";
    const startTime = "202303281300"; // YYYYMMDDHHmm 형식

    // 출발지, 경유지, 도착지 좌표를 변환.
    Promise.all([
      fetch(
        `https://dapi.kakao.com/v2/local/search/keyword.json?query=${start}`,
        {
          headers: {
            Authorization: "KakaoAK 714affae0391522fe47c0abb6754b9ae",
          },
        }
      ),
      fetch(
        `https://dapi.kakao.com/v2/local/search/keyword.json?query=${waypoint}`,
        {
          headers: {
            Authorization: "KakaoAK 714affae0391522fe47c0abb6754b9ae",
          },
        }
      ),
      fetch(
        `https://dapi.kakao.com/v2/local/search/keyword.json?query=${end}`,
        {
          headers: {
            Authorization: "KakaoAK 714affae0391522fe47c0abb6754b9ae",
          },
        }
      ),
    ])
      .then((responses) =>
        Promise.all(responses.map((response) => response.json()))
      )
      .then(([startData, waypointData, endData]) => {
        // 좌표를 생성.
        const startCoords = new window.kakao.maps.LatLng(
          startData.documents[0].y,
          startData.documents[0].x
        );
        const waypointCoords = new window.kakao.maps.LatLng(
          waypointData.documents[0].y,
          waypointData.documents[0].x
        );
        const endCoords = new window.kakao.maps.LatLng(
          endData.documents[0].y,
          endData.documents[0].x
        );

        console.log(startCoords);

        // 경로 정보를 담은 URL을 생성.
        const path = `${startCoords.getLng()},${startCoords.getLat()},${waypointCoords.getLng()},${waypointCoords.getLat()},${endCoords.getLng()},${endCoords.getLat()},${transType},${startTime}`;
        const url = `https://map.kakao.com/link/transit/path/${path}`;
        console.log(path);

        // 생성한 경로 정보를 새 탭에서 오픈.
        //window.open(url);

        // 출발지와 도착지 마커를 지도에 추가.
        startMarker.setPosition(startCoords);
        endMarker.setPosition(endCoords);
      })
      .catch((error) => console.error(error));
  }, []);

  return (
    <div
      id="map"
      style={{ width: "250px", height: "300px", float: "right" }}
    ></div>
  );
}

export default Map;



 
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import React, { useEffect, useRef, useState } from "react";

import ChatBot from './chatbot/ChatBot';
import MainPage from './mainPage/MainPage';
import Summary from './summary/Summary';
import FeedHome from './community/feed/route/FeedHome'
import SearchHome from './community/feed/route/SearchHome'
function App() {

  // 나의 여행 정보 SampleData
  const [columns, setColumns] = useState(["일정명", "시작일", "종료일", "경유지", " ", " "]);
  const [tPlanData, setTplanData] = useState([
      {planName:"A여행", st_dt:"2022-03-01", end_dt:"2022-03-25", root:"서울, 부산", shareStatus:"N"},
      {planName:"B여행", st_dt:"2022-03-04", end_dt:"2022-03-21", root:"인천, 일본, 하와이", shareStatus:"N"},
      {planName:"C여행", st_dt:"2022-05-01", end_dt:"2022-06-02", root:"인천, 유럽", shareStatus:"Y"}
  ]);

  // 최근 인기 여행지 SampleData
  const [columnsHit, setColumnsHit] = useState(["장소명", "계획등록 횟수"]);
  const [hitPlaceData, setHitPlaceData] = useState([
      {name:"그린컴", hitCnt:"5000건"},
      {name:"경기도", hitCnt:"652건"},
      {name:"제주도", hitCnt:"200건"}
  ]);
  
  return (
    <div className="App">
      <BrowserRouter>
        <MainPage/>
        <ChatBot/>
        <Routes>
          <Route exact path="/" element={<Summary columns={columns}
                                            tPlanData={tPlanData}
                                            columnsHit={columnsHit}
                                            hitPlaceData={hitPlaceData}
                                            />}></Route>
           <Route path="/app/feed" element={<FeedHome />}></Route>
            <Route path={`/app/search/:searchValue`} element={<SearchHome />}></Route>
                                        
          {/* <Route path="/plan" element={<Plan/>}></Route>
          <Route path="/community" element={<Community/>}></Route>
          <Route path="/mypage" element={<Mypage/>}></Route>
          <Route path="/logout" element={<Logout/>}></Route> */}
        </Routes>   
      </BrowserRouter>
    </div>
  );
}

export default App;

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import FeedHome from "./route/FeedHome";
import SearchHome from "./route/SearchHome"
function FeedRoute(){ // 일단 커뮤니티 전용 라우트 만듬. 나중에 다른 멤버 라우트와 합칠예정
    return (
        <Router>
            <Routes>
                {/* <Route path={`/feed`} element={<FeedHome />}></Route>
                <Route path={`/search/:searchValue`} element={<SearchHome />}></Route> */}
            </Routes>
        </Router>
    )

}
export default FeedRoute;
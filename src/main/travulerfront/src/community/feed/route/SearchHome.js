import axios from "axios";
import { useState , useEffect,useCallback } from "react";
import { Spinner } from "react-bootstrap";
import Feed from "../component/Feed";
import SearchBar from "../component/SearchBar";
import {getComments} from "../api"
import { useParams } from "react-router-dom";
import styles from "../styles/SearchHome.module.css";
import 'bootstrap/dist/css/bootstrap.min.css';


function SearchHome(){
    const [loading, setloading] = useState(true);
    const [feeds, setfeeds] = useState([]);
    const [page, setPage] = useState(0)
    const [isFetching, setFetching] = useState(false)
    const [hasNextPage, setNextPage] = useState(true)
    
    const {searchValue} =useParams()
    
    const fetchFeeds = useCallback(async () => {
    
    const { data } = await axios.get(`/feed/list`, {
        params: { page, size: 10, content:searchValue},
    })
    console.log("피드  : ",data);
    
    setfeeds(feeds.concat(data.content))
    setloading(false);
    setPage(data.pageable.pageNumber + 1)
    setNextPage(!data.last)
    setFetching(false)
  
    }, [page])

    useEffect(() => {
        const handleScroll = () => {
        
          const {scrollHeight,scrollTop,clientHeight} = document.documentElement;
          
          if(scrollTop + clientHeight  >=scrollHeight -5){
            console.log("터치");
            setFetching(true)
          }
     
        }
        setFetching(true)
        window.addEventListener('scroll', handleScroll)
        return () => window.removeEventListener('scroll', handleScroll)
      }, [])

        useEffect(() => {
                    
            if (isFetching && hasNextPage){
                
                fetchFeeds()
            } 
            else if (!hasNextPage){
                
                setFetching(false)
            } 
        }, [isFetching])
        
       
    return (
        <div>
            <div className={styles.createFormContainer}>
                <div className="SearchHeader">
                    <h1>검색어 : {searchValue}</h1>
                </div>
                {/* 서치 바 */}
                <div className="searchBarBox">
                    <SearchBar></SearchBar>
                </div>
            {/* 서치 바 끝 */}
            
          </div>
           

           
           <div>
            {loading ? <Spinner animation="grow" variant="dark"></Spinner>
            : feeds.map((feed,index)=><Feed key={feed.feedId}
            content={feed.content}
            feedUserId={feed.feedUserId}
            feedUsername={feed.feedUsername}
            feedFiles={feed.feedFiles}
            feedId={feed.feedId}
            getComments={getComments} 
            likeCount={feed.likeCount}
            feedLikeUsers={feed.feedLikeUsers}
            commentCount={feed.commentCount}
            />)
            
            }  
           {isFetching && <Spinner animation="grow" variant="dark"></Spinner>}
           </div>
           
        </div>
    )
}
export default SearchHome;
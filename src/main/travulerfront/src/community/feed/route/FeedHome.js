import axios from "axios";
import { useState , useEffect,useCallback } from "react";
import CreateFeedModal from "../component/CreateFeedModal";
import SearchBar from "../component/SearchBar";
import Feed from "../component/Feed";
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Spinner } from "react-bootstrap";
import {getComments} from "../api"
import Navbar from "../util/NavBars"
import styles from "../styles/FeedHome.module.css";
import plus from "../images/square-plus-regular.svg"
function FeedHome(){ // 커뮤니티 홈(메인페이지)
    const [loading, setloading] = useState(true);
    const [feeds, setfeeds] = useState([]);
    const [modalShow, setModalShow] = useState(false);
    const [page, setPage] = useState(0)
    const [isFetching, setFetching] = useState(false)
    const [hasNextPage, setNextPage] = useState(true)
    
    
    
    
    const fetchFeeds = useCallback(async () => {
      
 
    
    const { data } = await axios.get(`/feed/list`, {
        params: { page, size: 10},
    })
    console.log("피드22  : ",data);
    
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

       <div className={styles.feedHomeContainer}>
           
            <div className={styles.createFormContainer}>
             
              
              
                <div className={styles.divBox}>
                  <SearchBar></SearchBar>
                  <img className={styles.plus} src={plus} alt="" onClick={() => setModalShow(true)}></img>         
                </div>
                  
            
              <CreateFeedModal
                  show={modalShow}
                  onHide={() => setModalShow(false)}
                  fetchFeeds={fetchFeeds}
              />
          </div>
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
    )
}

export default FeedHome;
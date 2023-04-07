import { useState , useEffect,useCallback } from "react";
import BasicExample from "../util/Table"
import styles from "../styles/SearchBar.module.css";
import axios from "axios";
import Table from 'react-bootstrap/Table';
import { useDebounce } from 'react-use';
import hashTagImg from "../images/hashtag.svg"
import Button from 'react-bootstrap/Button';
function SearchBar(props){
    
    
    const [text, settext] = useState("");
    const [isLoading, setisLoading] = useState(false);
    const [isFetching, setisFetching] = useState(false);
    const [timer, setTimer] = useState(0); // 디바운싱 타이머
    const [searchHashTags, setsearchHashTags] = useState([]);
    
    
    
    const pressEnter = (e) =>{
        if(e.key ==='Enter'){
            window.location.href=`/search/${encodeURIComponent(text)}`
        }
    }

    const onChange = (e) =>{      
        settext(e.target.value)    
    }
    const moveToSearchHome = (e)=>{
       console.log(text);
       
       window.location.href=`/search/${encodeURIComponent(text)}`
    }
 
      const fetchHashTags = useCallback(async (searchValue) => {
        axios.get(`/hashTag/list/${searchValue}`,{
            params: { page:0, size: 3 },
        }).then(({data})=>{
            setsearchHashTags(data.content)
            setisFetching(false)
            
            })   
      },[text])
     
    
      
    const onKeyUp = (e)=>{
        setisFetching(true)
         
        let text = e.target.value
        
        if(text.startsWith('#')){
            if(text.length>1){
                let searchValue = text.substring(1)
                if (timer) {
                    clearTimeout(timer);
                  }
                  const newTimer = setTimeout(async () => {
                    try {
                      await fetchHashTags(searchValue);
                    } catch (e) {
                      console.error('error', e);
                    }
                  }, 300);
                  setTimer(newTimer);
                
            }
          
        }else{
            return;
        }                
         
    }

    const chooseHashTag = (e) =>{
      //console.log( e.target.className.match('tagTd') );
       if(e.target.className.match('tagTd') ===null) return;
       const hashtagSpan = e.target.querySelector('.tagspan');
       const hashtagValue = hashtagSpan.textContent;
      
        settext(hashtagValue) 
        setisFetching(true)
      }

     
    return (
        <div className={styles.searchBarContainer} onKeyDown={pressEnter}>
        <div className={styles.searchContainer}> 
          <input className={styles.searchInput} type="text" value={text} onChange={onChange} onKeyUp={onKeyUp} ></input>
          {/* <img className={styles.searchImg} src={search} onClick={moveToSearchHome}></img> */}
          <Button className={styles.searchBtn} onClick={moveToSearchHome}>검색</Button>
        </div>
        {searchHashTags.length > 0 
        ?
        
        <div className={styles.tagListContainer}>
           {isFetching ? null : 
           <Table className={styles.tagTable} striped bordered hover >
           <tbody className={styles.tagTbody}  onClick={chooseHashTag}>
          
              {searchHashTags.map(hashtag=>(
                 
                <tr className={styles.tagTr} >
                  <td className={styles.tagTd} >
                    <img className={styles.hashTagImg} src={hashTagImg} alt=""/>
                    <span className="tagspan">{hashtag.content}</span><br></br>
                    <div>
                    <span>게시물 {hashtag.feedCount}</span>
                    </div>
                  </td>
                </tr>
                
              )
              )}
             
           </tbody>
         </Table>
           }
           
        </div>
    : null}
    </div>
    )
}
export default SearchBar;
import styles from "../styles/Feed.module.css";
import FeedComment from "./FeedComment";
import axios from "axios";
import { useEffect,useState ,useCallback} from "react";
import { Link } from "react-router-dom" 
import profile from "../images/profile.png"
import heart from "../images/heart.svg"
import heart2 from "../images/heart2.svg"
import comment from "../images/comment.svg"
import Button from 'react-bootstrap/Button';
<script src="https://kit.fontawesome.com/6977b9706a.js" crossorigin="anonymous"></script>
function Feed({content,feedUsername,feedUserId,feedFiles,feedId,feedLikeUsers,commentCount}){ // 피드 컴포넌트

    const [comments, setcomments] = useState([]); // 댓글 불러오기
    const [inputShow, setInputShow] = useState(false); // 댓글 입력 창 생성
    const [commentShow, setcommentShow] = useState(false); 
    const [isValid, setisValid] = useState(false);
    const [commentFlag, setCommentFlag] = useState(false);
    const [likeUsers, setlikeUsers] = useState(feedLikeUsers);
    const [commentAllCount, setcommentAllCount] = useState(commentCount);
    
    const [state, setstate] = useState({
        feedUserId : 1, // 임시로 숫자바꿔서 회원 변경
        feedCommentText : "",
        feedId : feedId,
        parentId :null,
        userName : ""
    });
    
    useEffect(() => {
        
        if(inputShow && feedId !=null ){
            
            axios.get(`/comment/list/${feedId}`,{
                params: { page:0, size: 100 },
            }).then(({data})=>{
                
                setcomments(data.content)  
                console.log("댓글 : ", data)
                
               })
        }
       
        }, [inputShow,commentFlag]);
   
    const createComment = () =>{
        
        axios
        .post("/comment/register", state)
        .then(response=>{ 
            console.log("res" , response);
            
            setstate({feedUserId : state.feedUserId,feedCommentText : "",feedId : feedId,userName:""})
            setCommentFlag(!commentFlag)   
            setcommentAllCount(commentAllCount+1)
        }).catch(error=>{})   
               
    }
    const handleKeyDown = (e)=>{
        if(e.key === "Enter"){
            createComment(state)  
        }
     }
    const showComment = ()=>{
        setInputShow(true)
        setcommentShow(!commentShow)
    }

     //피드 좋아요
     const changeFeedLike = (feedUserId,feedId) =>{
        console.log("feedId",feedId);
        
        // let formData = new FormData();

        // formData.append("feedUserId",feedUserId)
        // formData.append("feedId",feedId)
        const data = {
            feedUserId : feedUserId,
            feedId : feedId
        }
        axios
      .post(`/feedLike/changeLike`, data)
      .then(
        (res) =>(
           res.data.map(i=>i.feedUserId)        
      )).then(
        res=>setlikeUsers(res)
        
      ) 
     }

    return (
       
        <div className={styles.FeedContainer}>


            <div className={styles.FeedBox}>
                <img className={styles.profileImg} src={profile} alt=""></img>
                <span>{feedUsername}</span>
                {/* <p>피드 PK :{feedId}</p> */}
                {/* <span>유저 ID :{feedUserId}</span> */}
                <div className={styles.feedContentContainer}>
                   
                     <pre  className={styles.feedContent}>{content.split(" ").map(word=>
                    word.startsWith('#') ? <span className={styles.hashTagSpan} onClick={ ()=>window.location.href=`/search/${encodeURIComponent(word)}`}>{word+ ' '}</span> : word + ' '
                    )}</pre>
                    
                </div>
               
                {/* 파일 */}
                {feedFiles.length > 0 
                ? <div className={styles.fileContainer}>
                     {feedFiles.map((feedFile) =>(                       
                        feedFile.contentType.includes('image') ? <img className={styles.FeedFile} src={"/display?fileName="+feedFile.fullName} ></img>: <video className={styles.FeedFile} src={"display?fileName="+feedFile.fullName} controls  />                     
                  ))}         
                </div> 
                : null}              
                {/* 파일 끝*/}

                {/* 좋아요 */}
                <div className={styles.likeButtonContainer}>
                     <div className={styles.likeButtonBox}>
                        {likeUsers.some(likeId=> likeId===state.feedUserId ) 
                        ? <div><img src={heart2} alt="" className={styles.likeButton} onClick={()=>changeFeedLike(state.feedUserId,state.feedId)}></img><span>{likeUsers.length}</span></div>
                        :<div><img src={heart} alt="" className={styles.likeButton} onClick={()=>changeFeedLike(state.feedUserId,state.feedId)}></img><span>{likeUsers.length}</span></div>}
                    </div>
                    <div className={styles.commentButtonBox}>           
                        <div><img className={styles.commentButton} src={comment} alt="" onClick={showComment}></img><span>{commentAllCount}</span></div>                        
                    </div>
                </div> 
                {/* 좋아요 끝 */}

                {/* 댓글 */}
                {commentShow 
                ?
                <div>
                    <div className={styles.commentContainer}>
                        
                        <div className={styles.commentListBox}>
                            {inputShow  ?
                            comments.map(comment=>    
                            <FeedComment
                            feedComment={comment}
                            state={state}
                            setstate={setstate}
                            ></FeedComment>
                            )
                            : ""}
                        </div>
                        
                                        
                    </div>
                
                    {/* 댓글 끝 */}

                    {/* 댓글 입력 */}
                    <div className={styles.commentform}>
                        {inputShow ? 
                            
                                <div className={styles.commentInputDiv} onKeyDown={handleKeyDown}>
                                    {state.userName !== "" 
                                    ? <span className={styles.commentInput2}  name="replyTofeedUserId" >{"@"+state.userName}</span>
                                    :null }
                                    
                                    
                                    <input className={styles.commentInput} name="feedCommentText" type="text" placeholder="댓글을 입력하세요." onChange={e =>{                  
                                        setstate({
                                            ...state,
                                            [e.target.name] : e.target.value
                                        })                             
                                    }}
                                    onKeyUp={e =>{
                                        e.target.value.length >0 
                                        ? setisValid(true)
                                        : setisValid(false)
                                    }} 
                                    value={state.feedCommentText}
                                    autoFocus>
                                    </input>                                    
                                    <Button className={styles.commentBtn} disabled={isValid ? false : true} onClick={()=>createComment()}>등록</Button>                                      
                                </div>
                                
                            
                            : ""}
                    </div> 
                    
                    {/* 댓글 입력 끝 */}
                </div>
                 :null }
            </div>
           
            {/* 피드 박스 끝 */}

        </div>
       
    )
    
}

export default Feed;

import { useState , useEffect} from "react";
import styles from "../styles/FeedReComment.module.css";
import profile from "../images/profile.png"
import heart from "../images/heart.svg"
import heart2 from "../images/heart2.svg"
import comment from "../images/comment.svg"
import axios from "axios";
function FeedReComment(props){
   const [replyShow, setReplyShow] = useState(false);
   const [commentLikeUsers, setcommentLikeUsers] = useState(props.feedComment.feedCommentLikeUsers);
    //댓글 좋아요
    const changeCommentLike = () =>{
    
        const data = {
            feedUserId : props.state.feedUserId,
            feedCommentId : props.feedComment.feedCommentId
        }
    
        axios
      .post(`/commentLike/changeCommentLike`, data)
      .then(
        (res) =>(
          res.data.map(i=>i.feedUserId)
          
          )
                   
      ).then(
        res=>setcommentLikeUsers(res)
        
      )  
      console.log(commentLikeUsers);
      
    }

    return (
        <div className={styles.commentBox}>
            
             <img className={styles.profileImg} src={profile} alt=""></img>
            {/* <span>작성자 : {props.feedComment.feedUserId}</span> */}
            <span className={styles.userNameSpan}>{props.feedComment.userName}</span><br></br>
            <span><span className={styles.parentUserNameSpan}>{"@"+props.feedComment.parentUserName+" "}</span>{ props.feedComment.comment}</span>
            <div>
                {/* <button>좋아요</button> */}
                {/* <button onClick={()=>props.setstate({
                     ...props.state,
                    replyTofeedUserId : props.feedComment.feedUserId,
                    parentId:props.feedComment.feedCommentId
                })} >대댓글 달기</button>       */}
                <div className={styles.commentButtonBox}>           
                    <div>
                    {commentLikeUsers.some(likeId=> likeId===props.state.feedUserId ) 
                        ? <div><img className={styles.likeBtn} onClick={changeCommentLike}  src={heart2} alt=""></img><span>{commentLikeUsers.length}</span></div>
                        :<div><img className={styles.likeBtn} onClick={changeCommentLike}  src={heart} alt=""></img><span>{commentLikeUsers.length}</span></div>}

                        <span className={styles.commentButton} src={comment} alt=""
                        onClick={()=>props.setstate({
                        ...props.state,
                        replyTofeedUserId : props.feedComment.feedUserId,
                        parentId:props.feedComment.feedCommentId,
                        userName:props.feedComment.userName
                        })}>댓글달기</span>
                      
                    </div>                        
                </div>    
            </div>
            <div >
                {/* {replyShow
                ? 
                <div className={styles.rerePlyBox}>
                {props.feedComment.children.map(childReply =>
                <FeedReComment 
                feedComment={childReply}
                state={props.state}
                setstate={props.setstate}
                ></FeedReComment>
                    
                )}
                </div>
            : ""} */}
             <div className={styles.rerePlyBox}>
                {props.feedComment.children.map(childReply =>
                <FeedReComment 
                feedComment={childReply}
                state={props.state}
                setstate={props.setstate}
                ></FeedReComment>
                    
                )}
                </div>
            </div>
        </div>
    )
}

export default FeedReComment;
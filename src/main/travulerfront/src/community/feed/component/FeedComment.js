
import { useState , useEffect} from "react";
import styles from "../styles/FeedComment.module.css";
import profile from "../images/profile.png"
import FeedReComment from "./FeedReComment"
import heart from "../images/heart.svg"
import heart2 from "../images/heart2.svg"
import comment from "../images/comment.svg"
import axios from "axios";
function FeedComment(props){
   const [replyShow, setReplyShow] = useState(false);
   const [replyDivShow, setReplyDivShow] = useState(false);

   const [commentLikeUsers, setcommentLikeUsers] = useState(props.feedComment.feedCommentLikeUsers);

   const showReply = ()=>{
    setReplyShow(true)
    setReplyDivShow(!replyDivShow)
   }

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
  //console.log(commentLikeUsers);
  
}
    return (

        <div className={styles.commentBox}>

             <img className={styles.profileImg} src={profile} alt=""></img>
            <span className={styles.userNameSpan}>{props.feedComment.userName}</span><br></br>
            <span className={styles.contentSpan}>{props.feedComment.comment}</span>
            
               
            <div className={styles.commentButtonBox}>           
            
                    {commentLikeUsers.some(likeId=> likeId===props.state.feedUserId ) 
                        ? <div><img className={styles.likeBtn} onClick={changeCommentLike}  src={heart2} alt=""></img><span>{commentLikeUsers.length}</span></div>
                        :<div><img className={styles.likeBtn} onClick={changeCommentLike}  src={heart} alt=""></img><span>{commentLikeUsers.length}</span></div>}
                    <img className={styles.commentButton} src={comment} alt=""
                    onClick={()=>props.setstate({
                    ...props.state,
                    replyTofeedUserId : props.feedComment.feedUserId,
                    parentId:props.feedComment.feedCommentId,
                    userName:props.feedComment.userName
                    })}></img>
                    <span>{props.feedComment.commentCount}</span>
                    
                                        
            </div>
            {replyDivShow 
            ?  
            <div className={styles.rerePlyContainer}>
                {replyShow
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
                : ""}
            </div>    
            : null}   

         

            {props.feedComment.children.length>0        
            ? <span className={styles.rerecommentButton} onClick={showReply}>-- 댓글보기</span>
            :  null  }

           
        </div>
    )
}

export default FeedComment;
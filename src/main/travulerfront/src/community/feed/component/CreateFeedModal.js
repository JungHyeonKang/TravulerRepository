import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState , useEffect, createRef ,useCallback} from "react";
import styles from "../styles/CreateFeedModal.module.css"
import axios from "axios";
import upload from "../images/upload.svg"
<script src="https://kit.fontawesome.com/6977b9706a.js" crossorigin="anonymous"></script>

function CreateFeedModal(props) { // 피드 등록 모달 컴포넌트
 
  const [hashtags, setHashtags] = useState([]);
  const [isFetching, setisFetching] = useState(false);
  const [timer, setTimer] = useState(0); // 디바운싱 타이머
  const [searchHashTags, setsearchHashTags] = useState([]);

  const [state, setState] = useState({
     feedUserId : 1,
     content:"",
     fileList : [],
    }
   
);
  // 이미지 미리보기를 위한 state
  const [showFiles, setShowFiles] = useState([]);


  const changeContent = (e) => {   // 텍스트 입력 감지 메서드
    
      setState({
        ...state,
        [e.target.name] : e.target.value
      })
    const inputText = e.target.value;
    const regex = /(?:^|\s)(?:#)([ㄱ-ㅎ가-힣A-Za-z0-9-_]+)/gm;
    const matches = inputText.match(regex);
    const hashtags = matches ? matches.map(match => match.trim()) : [];
    
    setHashtags(hashtags);
    
    
  }

  // const handleKeyDown = (e)=>{
  //    if(e.key === "Enter"){
  //     createFeed(state)
  //    }
  // }

  const createFeed = (state)=>{ // 피드 등록 메서드
    console.log("하이");
    
    console.log(state);
    console.log(hashtags);
    let test =state.content.replace(/\n/g, "<br>");
    
    
    let formData = new FormData(); //formdata에 필요한 파라메터 저장

    state.fileList.forEach(file=>formData.append("fileList",file))
    formData.append("feedUserId", state.feedUserId);
    formData.append("content", state.content);
    hashtags.forEach(tag=>formData.append("feedHashTags",tag))
    let axiosConfig = { // 파일 타입 보내기 위한 설정
      headers: {
        'Content-Type': 'multipart/form-data'
      }
  }
     const url =`/feed/register` // 피드 등록 API URL
      axios
      .post(url, formData,axiosConfig)
      .then(response=>{ // 등록 성공 후 초기화
        // setShowFiles([])
         setState({feedUserId : 1,content : "",fileList:[]})
        props.onHide(false)
        //window.location.reload();
      })
      .catch(error=>{
        
      })
  }
  
  const fileInput = createRef(null)
  const handleButtonClick = (e) => {
    fileInput.current.click();
  };

 
  
  // 이미지 업로드 메서드
  const handleAddImages = (event) => {
    const fileLists = event.target.files;
    let fileUrlLists = [...showFiles];
    const imageArray = []
    
    const fileArray = []
    for(let i = 0; i < fileLists.length; i++){
      
      fileArray.push(fileLists[i])
    }
  
   setShowFiles(fileArray);
    setState({
      ...state,
      fileList : fileArray
    })
   
    
  };

  // 해시태그  (미완성)
//   const fetchHashTags = useCallback(async (searchValue) => {
//     axios.get(`/hashTag/list/${searchValue}`,{
//         params: { page:0, size: 3 },
//     }).then(({data})=>{
//         setsearchHashTags(data.content)
//         setisFetching(false)
        
//         })   
//   },[state])
 

  
// const onKeyUp = (e)=>{
//     setisFetching(true)
     
//     let text = e.target.value
    
//     if(text.startsWith('#')){
//         if(text.length>1){
//             let searchValue = text.substring(1)
//             if (timer) {
//                 clearTimeout(timer);
//               }
//               const newTimer = setTimeout(async () => {
//                 try {
//                   await fetchHashTags(searchValue);
//                 } catch (e) {
//                   console.error('error', e);
//                 }
//               }, 300);
//               setTimer(newTimer);
            
//         }
      
//     }else{
//         return;
//     }                
     
// }
//   const chooseHashTag = (e) =>{
//     if(e.target.className !=='tagtd') return;
//     let tagContent = e.target.firstChild.innerText;
//     console.log(tagContent);
//     setState({
//       ...state,
//       content : tagContent
//     })
//   }

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title className={styles.title} id="contained-modal-title-vcenter">
         게시글 등록
        </Modal.Title>
      </Modal.Header>
      <Modal.Body className={styles.modalBody}>

        <div  className={styles.contentContainer} >
          <textarea className={styles.textInput} name="content" onChange={changeContent} value={state.content} autoComplete="off" ></textarea>
          <img src={upload} className={styles.uploadButton} onClick={()=>handleButtonClick()} ></img>
          <div className={styles.fileContainer}>
            {showFiles.map((file, id) => (  
            <div key={id}>
              {file.type.includes('image') && <img className={styles.imageFile} src={URL.createObjectURL(file)} width="350px" />}
              {file.type.includes('video') && <video className={styles.imageFile} src={URL.createObjectURL(file)} controls width="350px" />}                              
            </div>       
            ))}
          </div>
          
          {/* <Button onClick={()=>handleButtonClick()}>파일업로드</Button> */}
          <input type="file"
             ref={fileInput}
             multiple
             onChange={handleAddImages}
             style={{ display: "none" }}
             name="imageList" />
        </div>
      </Modal.Body> 
      <Modal.Footer>
        <Button onClick={()=>createFeed(state)}>게시글 등록</Button>
        <Button onClick={props.onHide}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default CreateFeedModal;

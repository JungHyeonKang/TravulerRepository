import Table from 'react-bootstrap/Table';

function BasicExample(props) {
 
  
  return (
    <Table style={{ width: "20%", margin: "0 auto"}} striped bordered hover>
     
      <tbody>
        {props.searchHashTags.map(hashtag=>(
          <tr>
            <td>
              <span>{hashtag.content}</span>
              <p>{hashtag.feedCount}</p>
            </td>
          </tr>
        )
        )}
       
       
      
      </tbody>
    </Table>
  );
}

export default BasicExample;
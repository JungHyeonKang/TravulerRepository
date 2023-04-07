import { useState } from "react";
import { Offcanvas } from "react-bootstrap";
import { Link } from "react-router-dom";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
function Navbar() {
  const [show, setShow] = useState(false);
  const [text, settext] = useState("");
   
    
  const pressEnter = (e) =>{
    
      if(e.key ==='Enter'){
        console.log(e.key);
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
  
  return (
    <>
      <div>
      <button
            className="navbar-toggler"
            type="button"
            onClick={() => setShow(true)}
          >
            검색
          </button>
     
      </div>
      <Offcanvas  show={show} onHide={() => setShow(false)}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>검색</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body onKeyDown={pressEnter}>
            <div onKeyDown={pressEnter}>
            <Form className="d-flex">
                <Form.Control
                type="search"
                placeholder="Search"
                className="me-2"
                aria-label="Search"
                value={text} onChange={onChange}
                />
                <Button variant="outline-success" onClick={moveToSearchHome}>Search</Button>
            </Form>
            </div>
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link to="/" className="nav-link" onClick={() => setShow(false)}>
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/about" className="nav-link" onClick={() => setShow(false)}>
                About
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/contact" className="nav-link" onClick={() => setShow(false)}>
                Contact
              </Link>
            </li>
          </ul>
        </Offcanvas.Body>
      </Offcanvas>
    </>
  );
}
export default Navbar;
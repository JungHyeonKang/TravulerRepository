import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Switch, Route, NavLink } from 'react-router-dom';
import './mainPage.css';

function MainPage() {

  return (
    <div className="MainPage">
      <nav>
        <ul>
          <li>
            <NavLink to="/" className="mainTitle">Travuler</NavLink>
          </li>
          <li>
            <NavLink to="/plan" activeclassname="active">PLANNER</NavLink>
          </li>
          <li>
            <NavLink to="/feed" activeclassname="active">COMMUNITY</NavLink>
          </li>
          <li>
            <NavLink to="/mypage" activeclassname="active">MYPAGE</NavLink>
          </li>
          <li>
            <NavLink to="/logout" activeclassname="active">LOGOUT</NavLink>
          </li>
        </ul>
      </nav>
    </div>
  );
}

export default MainPage;

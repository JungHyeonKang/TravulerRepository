import React, { useEffect, useRef, useState } from "react";
import * as StompJs from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import './chatbot.css';

const ChatBot = () => {

  const client = useRef({});
  const [chatMessages, setChatMessages] = useState([]);
  const [message, setMessage] = useState("");
  const [chatWrapActive, setChatWrapActive] = useState(false);

  const toggleChatWrap = () => {
    setChatWrapActive(!chatWrapActive);

    if(!chatWrapActive)
      connect();
    else
      disconnect();
  };

  const connect = () => {

    client.current = new StompJs.Client({
      webSocketFactory: () => new SockJS("/ws")
      ,connectHeaders: {
        "auth-token": "spring-chat-auth-token"
      }
      ,debug: function (str) {
        console.log(str);
      }
      ,reconnectDelay: 5000
      ,heartbeatIncoming: 4000
      ,heartbeatOutgoing: 4000
      ,onConnect: () => {
        subscribe();
      }
      ,onStompError: (frame) => {
        console.error(frame);
      }
    });

    client.current.activate();
  };

  const disconnect = () => {
    client.current.deactivate();
  };

  const subscribe = () => {
    client.current.subscribe(`/topic/public`, function (response) {
        setChatMessages((_chatMessages) => [..._chatMessages, {'sender' : 'TRAVULER', 'message' : response.body}]);
    });
  };

  const publish = (message) => {
    
    if (!client.current.connected) 
      return;

    setChatMessages((_chatMessages) => [..._chatMessages, {'sender' : 'USER', 'message' : message}]);

    client.current.publish({
      destination: "/app/sendMessage",
      body: JSON.stringify(message)
    });

    setMessage("");
  };

  const handleOnKeyPress = e => {
    if (e.key === 'Enter') {
      publish(message);
    }
  };

  return (
    <div className="ChatBot">
      <div>
        <button 
          type="button" 
          className={"circular-button"}
          onClick={() => {
            toggleChatWrap();
          }}></button>
      </div>
      <div className={`chat_wrap${chatWrapActive ? " active" : ""}`}>
          <div className="header">
              Travuler Chatbot
          </div>
          <button type="button" className="close-btn" onClick={() => toggleChatWrap()}>x</button>
          <div className="chat format">
            {chatMessages && chatMessages.length > 0 && (
              <ul>
                {chatMessages.map((_chatObj, index) => (
                  <li className={_chatObj.sender == 'TRAVULER' ? "chatbot" : "user"} key={index}>
                    <div className="sender">
                        <span>{_chatObj.sender}</span>
                    </div>
                    <div className="message">
                        <span>{_chatObj.message}</span>
                    </div>
                  </li>
                ))}
              </ul>
            )}
          </div>
          <div className="input-div">
            <textarea
              type={"text"}
              placeholder={"메시지를 입력하세요"}
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              onKeyDown={handleOnKeyPress}
            />
            <button onClick={() => publish(message)}>send</button>
          </div>
      </div>
    </div>
  );
};

export default ChatBot;
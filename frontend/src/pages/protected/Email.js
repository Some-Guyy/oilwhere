'use server'
import React, { useRef, useState } from "react";
import styled from "styled-components";
import { Resend } from "resend";

import EmailEditor from "react-email-editor"; // use react-email-editor instead

const resend = new Resend("re_123456789");

const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  height: 100%;
  padding-bottom: 40px;
`;

const Email = () => {
  const [preview, setPreview] = useState(false);

  const saveDesign = () => {
    const unlayer = emailEditorRef.current?.editor;

    unlayer?.saveDesign((design) => {
      console.log("saveDesign", design);
      alert("Design JSON has been logged in your developer console.");
    });
  };

  const onDesignLoad = (data) => {
    console.log("onDesignLoad", data);
  };

  const emailEditorRef = React.useRef(null);

  const exportHtml = () => {
    emailEditorRef.current?.exportHtml((data) => {
      const { design, html } = data;
      console.log("exportHtml", html);
    });
  };

  const sendEmail = async () => {
    let htmlData = "";
    emailEditorRef.current?.exportHtml((data) => {
      const { design, html } = data;
      htmlData = html;
    });

    const results = await fetch('/api/newsletter', {
        method: 'POST',
        body: JSON.stringify({
          email: 'jonathantohjj@gmail.com',
          body: htmlData
        })
      }).then(r => r.json());

      console.log(results);
  };

  // You can set it here
  const onLoad = (editor) => {
    emailEditorRef.current = editor;
  };

  // OR You can set it here
  const onReady = (editor) => {
    emailEditorRef.current = editor;
  };

  return (
    <Container>
      <div className="flex flex-row-reverse gap-3 pb-5">
        <button class="btn btn-primary" onClick={sendEmail}>
          Send Email
        </button>
        <button class="btn btn-outline btn-secondary" onClick={saveDesign}>
          Save Design
        </button>
        <button class="btn btn-outline btn-secondary" onClick={exportHtml}>
          Export HTML
        </button>
      </div>

      <React.StrictMode>
        <EmailEditor
          ref={emailEditorRef}
          onLoad={onLoad}
          onReady={onReady}
          options={{
            version: "latest",
            appearance: {
              theme: "modern_light",
            },
          }}
        />
      </React.StrictMode>
    </Container>
  );
};

export default Email;

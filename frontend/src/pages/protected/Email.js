"use server";
import React, { useRef, useState } from "react";
import styled from "styled-components";
import { Toaster, toast } from "sonner";
import EmailEditor from "react-email-editor"; // use react-email-editor instead

const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  height: 100%;
  padding-bottom: 40px;
`;

const Email = () => {
  const [emailAddresses, setEmailAddresses] = useState(""); // State to store user input
  const emailEditorRef = useRef(null);

  const saveDesign = () => {
    const unlayer = emailEditorRef.current?.editor;

    unlayer?.saveDesign((design) => {
      console.log("saveDesign", design);
      toast("Design JSON has been logged in your developer console.");
      alert("Design JSON has been logged in your developer console.");
    });
  };

  const exportHtml = () => {
    emailEditorRef.current?.exportHtml((data) => {
      const { html } = data;
      console.log("exportHtml", html);
      toast("Export HTML has been logged in your developer console.");
    });
  };

  const sendEmail = async () => {
    try {
      // Split and trim emails, filtering out invalid ones
      const emails = emailAddresses.split(",").map(email => email.trim()).filter(email => validateEmail(email));

      if (emails.length === 0) {
        toast("Please enter at least one valid email.");
        return;
      }

      let htmlData = await new Promise((resolve, reject) => {
        emailEditorRef.current?.exportHtml((data) => {
          const { html } = data;
          if (html) {
            resolve(html); // Resolve the promise with the HTML content
          } else {
            reject("Failed to export HTML");
          }
        });
      });

      // Send the email to each address individually
      for (const email of emails) {
        console.log(`Sending email to: ${email}`);
        await fetch("/api/newsletter/email", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name: "jonathantoh",
            email: email,
            subject: "That's a nice email",
            body: htmlData,
          }),
        }).then((r) => r.json());

        toast(`Email sent to ${email}`);
      }
    } catch (error) {
      console.error(error);
      toast("Failed to export or send email.");
    }
  };

  // Simple email validation function
  const validateEmail = (email) => {
    const re = /\S+@\S+\.\S+/;
    return re.test(email);
  };

  const onLoad = (editor) => {
    emailEditorRef.current = editor;
  };

  const onReady = (editor) => {
    emailEditorRef.current = editor;
  };

  return (
    <>
      <Toaster />

      <dialog id="my_modal_1" class="modal">
        <div class="modal-box">
          <h3 class="text-lg font-bold pb-3">Email to send to:</h3>
          <textarea
            class="textarea w-full"
            placeholder="Input emails separated by commas"
            value={emailAddresses}
            onChange={(e) => setEmailAddresses(e.target.value)}
          ></textarea>
          <div class="modal-action">
            <form method="dialog">
              <button class="btn btn-primary" onClick={sendEmail}>
                Send Email
              </button>
              <button class="btn">Close</button>
            </form>
          </div>
        </div>
      </dialog>

      <Container>
        <div className="flex flex-row-reverse gap-3 pb-5">
          <button
            class="btn btn-primary"
            onClick={() => document.getElementById("my_modal_1").showModal()}
          >
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
    </>
  );
};

export default Email;

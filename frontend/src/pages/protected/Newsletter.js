import React, { useRef, useState, useEffect } from "react";
import styled from "styled-components";
import { Toaster, toast } from "sonner";
import EmailEditor from "react-email-editor";
import { useLocation, useNavigate } from "react-router-dom";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  height: 100%;
  padding-bottom: 40px;
`;

const Newsletter = () => {
  const [emailAddresses, setEmailAddresses] = useState("");
  const [newsletterName, setNewsletterName] = useState("");

  const [isEditing, setIsEditing] = useState(false);
  const [templateData, setTemplateData] = useState(null);
  const emailEditorRef = useRef(null);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const { state } = location;

    if (state?.mode === "edit" && state?.templateData) {
      console.log(state);
      setTemplateData(state.templateData);
      setIsEditing(true);
      // Fix the typo in templateName and add null checking
      setNewsletterName(state.templateName || '');
    }
  }, [location]);

   // Add handle change function
   const handleNameChange = (e) => {
    setNewsletterName(e.target.value);
  };

  const saveDesign = async () => {
    // const unlayer = emailEditorRef.current?.editor;

    // unlayer?.saveDesign(async (design) => {
    try {
      let htmlData = await new Promise((resolve, reject) => {
        emailEditorRef.current?.exportHtml((data) => {
          const { html } = data;
          if (html) {
            resolve(html);
          } else {
            reject("Failed to export HTML");
          }
        });
      });

      // Add your save API call here
      const { state } = location;

      const response = await fetch(`/api/newsletter/${state.templateId}`, {
        method: isEditing ? "PUT" : "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          name: newsletterName,
          content: htmlData,
        }),
      });

      toast.success("Template saved successfully");
      // navigate("/app/newsletter-list"); // Navigate back to template list
    } catch (error) {
      toast.error("Failed to save template");
    }
    // });
  };

  const sendEmail = async () => {
    try {
      // Split and trim emails, filtering out invalid ones
      const emails = emailAddresses
        .split(",")
        .map((email) => email.trim())
        .filter((email) => validateEmail(email));

      if (emails.length === 0) {
        toast("Please enter at least one valid email.");
        return;
      }

      let htmlData = await new Promise((resolve, reject) => {
        emailEditorRef.current?.exportHtml((data) => {
          const { html } = data;
          if (html) {
            resolve(html);
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

  const validateEmail = (email) => {
    const re = /\S+@\S+\.\S+/;
    return re.test(email);
  };

  const onLoad = (editor) => {
    emailEditorRef.current = editor;
  };

  const onReady = (editor) => {
    emailEditorRef.current = editor;

    // If editing existing template, load the design
    if (isEditing && templateData) {
      // Convert the plain text content to Unlayer design JSON
      const design = {
        body: {
          rows: [
            {
              cells: [1],
              columns: [
                {
                  contents: [
                    {
                      type: "text",
                      values: {
                        text: templateData.content,
                      },
                    },
                  ],
                },
              ],
            },
          ],
        },
      };

      editor.loadDesign(design);
    }
  };

  return (
    <>
      <Toaster />

      <dialog id="my_modal_1" className="modal">
        <div className="modal-box">
          <h3 className="text-lg font-bold pb-3">Email to send to:</h3>
          <textarea
            className="textarea w-full"
            placeholder="Input emails separated by commas"
            value={emailAddresses}
            onChange={(e) => setEmailAddresses(e.target.value)}
          ></textarea>
          <div className="modal-action">
            <form method="dialog">
              <button className="btn btn-primary mr-2" onClick={sendEmail}>
                Send Email
              </button>
              <button className="btn">Close</button>
            </form>
          </div>
        </div>
      </dialog>

      <Container>
        <div class="flex justify-between">
          <input
            type="text"
            placeholder="Enter newsletter name"
            className="input input-bordered w-full max-w-xs"
            value={newsletterName}
            onChange={handleNameChange}
            required
          />
          <div className="flex flex-row-reverse gap-3 pb-5">
            <button
              className="btn btn-primary"
              onClick={() => document.getElementById("my_modal_1").showModal()}
            >
              Send Email
            </button>
            <button
              className="btn btn-outline btn-secondary"
              onClick={() => saveDesign()}
            >
              {isEditing ? "Update Template" : "Save Template"}
            </button>
            <button
              className="btn btn-outline"
              onClick={() => navigate("/app/newsletter-list")}
            >
              Cancel
            </button>
          </div>
        </div>

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
      </Container>
    </>
  );
};

export default Newsletter;

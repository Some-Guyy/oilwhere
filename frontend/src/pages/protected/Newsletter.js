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
  const [newsletterName, setNewsletterName] = useState("");
  const [segment, setSegment] = useState("");
  const [subject, setSubject] = useState("");

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
      setNewsletterName(state.templateName || "");
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

      console.log(response);

      toast.success("Template saved successfully");
      // navigate("/app/newsletter-list"); // Navigate back to template list
    } catch (error) {
      toast.error("Failed to save template");
    }
    // });
  };

  const sendEmail = async () => {
    try {
      if (!segment) {
        toast("Please select a segment level.");
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

      // Modify your API call to handle priority instead of email addresses
      await fetch("/api/customer/send-email", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          segment: segment,
          subject: subject,
          body: htmlData,
        }),
      }).then((r) => r.json());

      toast(`Email scheduled with ${segment} segment`);
      setSubject("");
      document.getElementById("my_modal_1").close();
    } catch (error) {
      console.error(error);
      toast("Failed to schedule email.");
    }
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
          <div className="space-y-6">
            <div>
              <label className="input-group input-group-vertical w-full">
                <span className="font-semibold">Subject</span>
                <input
                  type="text"
                  placeholder="Enter subject"
                  className="input input-bordered w-full mt-1"
                  value={subject}
                  onChange={(e) => setSubject(e.target.value)}
                />
              </label>
            </div>

            <div>
              <label className="input-group input-group-vertical w-full">
                <span className="font-semibold">Priority</span>
                <select
                  className="select select-bordered w-full mt-1"
                  onChange={(e) => setSegment(e.target.value)}
                >
                  <option disabled selected>
                    Select priority level
                  </option>
                  <option value="high">High</option>
                  <option value="medium">Medium</option>
                  <option value="low">Low</option>
                </select>
              </label>
            </div>
          </div>

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

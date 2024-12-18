import React, { useState, useEffect } from "react";
import { Pencil, Trash, Plus } from "lucide-react";
import { useNavigate } from "react-router-dom";
import { Toaster, toast } from "sonner";
import { useDispatch } from 'react-redux'
import { setPageTitle } from '../../features/common/headerSlice'

const NewsletterList = () => {
  const [templates, setTemplates] = useState([]);
  const [newsletterName, setNewsletterName] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const TOKEN = JSON.parse(localStorage.getItem("token"));
  const username = TOKEN.username;
  const role = TOKEN.role;
  const userId = TOKEN.userId;

  console.log("role", role);
  console.log("userId", userId);

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(setPageTitle({ title : "Newsletter List"}))

    fetchTemplates();
  }, []);

  const fetchTemplates = async () => {
    try {
      const response = await fetch("/api/newsletter/get-all");
      const data = await response.json();
      setTemplates(data);
      setIsLoading(false);
    } catch (err) {
      setError("Failed to fetch templates");
      setIsLoading(false);
    }
  };

  const handleEdit = (template) => {
    // Navigate to Newsletter component with template data
    navigate("/app/newsletter", {
      state: {
        mode: "edit",
        templateId: template.designId,
        templateName: template.name,
        templateData: template,
      },
    });
  };

  const handleCreate = async () => {
    // Navigate to Newsletter component for creation
    try {
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
                        text: "",
                      },
                    },
                  ],
                },
              ],
            },
          ],
        },
      };

      const response = await fetch("/api/newsletter/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: newsletterName,
          content: JSON.stringify(design),
          userId: userId
        }),
      });
      const data = await response.json();
      // setTemplates(data);
      setIsLoading(false);
      handleEdit(data);
      
    } catch (err) {
      setError("Failed to create template");
      setIsLoading(false);
    }
  };
  const handleDelete = async (template) => {
    try {
      // First confirm with the user
      const isConfirmed = window.confirm(
        `Are you sure you want to delete the template "${template.name}"? This action cannot be undone.`
      );
  
      if (!isConfirmed) {
        return;
      }
  
      // Construct API endpoint with proper protocol
      const baseUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';
      const endpoint = `${baseUrl}/api/newsletter/delete/${template.designId}`;
  
      const response = await fetch(endpoint, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          // Add any auth headers if needed
          // 'Authorization': `Bearer ${yourAuthToken}`,
        },
      });
  
      if (!response.ok) {
        // If the response wasn't in the 200-299 range
        const errorData = await response.json().catch(() => null);
        throw new Error(
          errorData?.message || 
          `Failed to delete template (HTTP ${response.status})`
        );
      }
  
      // Show success message
      toast.success('Template deleted successfully');
      navigate(0)
    } catch (error) {
      console.error('Delete template error:', error);
      toast.error(error.message || 'Failed to delete template');
    }
  };

  if (isLoading)
    return (
      <div className="flex justify-center items-center min-h-[200px]">
        <span className="loading loading-spinner loading-lg"></span>
      </div>
    );

  if (error)
    return (
      <div className="alert alert-error">
        <span>{error}</span>
      </div>
    );

  return (
    <>
      <dialog id="createDialog" className="modal">
        <div className="modal-box">
          <h3 className="text-lg font-bold pb-3">Newsletter template name</h3>
          <input
            className="textarea w-full"
            placeholder="E.g High, mid, low, CNY, Christmas..."
            value={newsletterName}
            onChange={(e) => setNewsletterName(e.target.value)}
          ></input>
          <div className="modal-action">
            <form method="dialog">
              <button className="btn btn-primary mr-2" onClick={handleCreate}>
                Create
              </button>
              <button className="btn">Close</button>
            </form>
          </div>
        </div>
      </dialog>

      <div className="p-4">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-2xl font-bold">Email Templates</h2>
          {role === "ADMIN" && (
          <button onClick={() => document.getElementById("createDialog").showModal()} className="btn btn-primary">
            <Plus className="w-4 h-4 mr-2" />
            Create Template
          </button>
          )}
        </div>

        <div className="overflow-x-auto">
          <table className="table w-full">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {templates.map((template) => (
                <tr key={template.designId}>
                  <td>{template.designId}</td>
                  <td>{template.name}</td>
                  <td className="flex gap-2">
                    <button
                      onClick={() => handleEdit(template)}
                      className="btn btn-sm btn-ghost"
                    >
                      <Pencil className="w-4 h-4" />
                    </button>
                    {role === "ADMIN" && (
                    <button
                      onClick={() => handleDelete(template)}
                      className="btn btn-sm btn-ghost text-error"
                    >
                      <Trash className="w-4 h-4" />
                    </button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default NewsletterList;

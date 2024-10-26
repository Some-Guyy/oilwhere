import { useState } from "react"
import { useDispatch } from "react-redux"
import InputText from '../../../components/Input/InputText'
import ErrorText from '../../../components/Typography/ErrorText'
import { showNotification } from "../../common/headerSlice"
import { addNewLead } from "../leadSlice"

const INITIAL_LEAD_OBJ = {
    username : "",
    password : "",
    role : ""
}

function AddLeadModalBody({closeModal}){
    const dispatch = useDispatch()
    const [loading, setLoading] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const [leadObj, setLeadObj] = useState(INITIAL_LEAD_OBJ)
    const roles = ["ADMIN","SALES","MARKETING"]
    const [isOpen, setIsOpen] = useState(false);


    const saveNewLead = () => {
        if(leadObj.username.trim() === "")return setErrorMessage("username is required!")
        else if(leadObj.password.trim() === "")return setErrorMessage("password is required!")
        else if(leadObj.role.trim() === "")return setErrorMessage("role is required!")
        else{
            CreateUser()
            closeModal()
        }
    }

    const updateFormValue = ({updateType, value}) => {
        setErrorMessage("")
        setLeadObj({...leadObj, [updateType] : value})
    }

    const updateRole = (value) => {
        setIsOpen(false); 
        setErrorMessage("")
        setLeadObj({...leadObj, role : value})
    }

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
      };

    const CreateUser = async () => {
        const apiUrl =  "http://localhost:8080/api/users/create"
        console.log(leadObj.username)
              try{
                const res = await fetch(apiUrl,{
                    method: 'POST',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        "role": leadObj.role.toUpperCase(),
                        "username": leadObj.username,
                        "password": leadObj.password,
                    })
                });
                const data = await res.json();
                let newLeadObj = {
                    "role": leadObj.role.toUpperCase(),
                    "username": leadObj.username,
                    "password": leadObj.password,
                    "userId" : data.userId
                }
                dispatch(addNewLead({newLeadObj}))
                dispatch(showNotification({message : "New Lead Added!", status : 1}))
              } catch(error) {
                  console.log("Error creating User", error);
        
              } finally {
                  console.log("We fetched EmployeeInfo");
              }
      };

    return(
        <>

            <InputText type="text" defaultValue={leadObj.username} updateType="username" containerStyle="mt-4" labelTitle="username" updateFormValue={updateFormValue}/>

            <InputText type="text" defaultValue={leadObj.password} updateType="password" containerStyle="mt-4" labelTitle="password" updateFormValue={updateFormValue}/>

            <div className="dropdown dropdown-bottom dropdown-end mr-4 mt-4 form-control w-full">
            <label className="label">
                <span className="label-text text-base-content ">role</span>
            </label>
            <label tabIndex="0" className="btn m-1" onClick={toggleDropdown}>{leadObj.role || "--- Choose role ---"}</label>
            {isOpen ? 
            <ul tabIndex="0" className="dropdown-content menu p-2 text-sm shadow bg-base-100 rounded-box w-full z-10">
                {
                    roles.map((role,i) =>{
                        return <li key={i}><a onClick={()=>updateRole(role)}>{role}</a></li>
                    })
                }
            </ul>
            :null}
            </div>


            <ErrorText styleClass="mt-16">{errorMessage}</ErrorText>
            <div className="modal-action">
                <button  className="btn btn-ghost" onClick={() => closeModal()}>Cancel</button>
                <button  className="btn btn-primary px-6" onClick={() => saveNewLead()}>Save</button>
            </div>
        </>
    )
}

export default AddLeadModalBody
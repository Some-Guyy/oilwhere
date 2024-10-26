import { useState } from "react"
import { useDispatch } from "react-redux"
import InputText from '../../../components/Input/InputText'
import ErrorText from '../../../components/Typography/ErrorText'
import { showNotification } from "../../common/headerSlice"
import { updateLead } from "../leadSlice"
import { openModal } from "../../common/modalSlice"
import { CONFIRMATION_MODAL_CLOSE_TYPES, MODAL_BODY_TYPES } from '../../../utils/globalConstantUtil'




function EditUserModalBody({extraObject,closeModal}){
    const dispatch = useDispatch()
    const [loading, setLoading] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const {data,index} = extraObject
    const roles = ["ADMIN","SALES","MARKETING"]
    const [isOpen, setIsOpen] = useState(false);

    const INITIAL_LEAD_OBJ = {
        username : data.username,
        role : data.role,
        password :  data.password
    }
    const userId = data.userId
    const [leadObj, setLeadObj] = useState(INITIAL_LEAD_OBJ)

    const deleteCurrentLead = (index) => {
        dispatch(openModal({title : "Confirmation", bodyType : MODAL_BODY_TYPES.CONFIRMATION,
        extraObject : { message : `Are you sure you want to delete this user?`, type : CONFIRMATION_MODAL_CLOSE_TYPES.LEAD_DELETE, index,userId}}))
    }

    const saveNewLead = () => {
        if(leadObj.username.trim() === "")return setErrorMessage("username is required!")
        else if(leadObj.role.trim() === "")return setErrorMessage("role is required!")
        else{
            UpdateUser()
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


    const UpdateUser = async () => {
        const apiUrl =  `http://localhost:8080/api/users/${data.userId}`
        console.log(leadObj.username)
              try{
                const res = await fetch(apiUrl,{
                    method: 'PUT',
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
                let updatedLeadObj = {
                    "userId" : data.userId,
                    "username": data.username,
                    "password": data.password,
                    "role": data.role.toUpperCase(),
                }
                dispatch(updateLead({userId,updatedLeadObj}))
                dispatch(showNotification({message : "User Updated!", status : 1}))
              } catch(error) {
                  console.log("Error Updating User", error);
        
              } finally {
                  console.log("Updated User");
              }
      };

    return(
        <>

            <InputText type="text" defaultValue={leadObj.username} updateType="username" containerStyle="mt-4" labelTitle="username" updateFormValue={updateFormValue}/>

            {/* <InputText type="text" defaultValue={leadObj.role} updateType="role" containerStyle="mt-4" labelTitle="role" updateFormValue={updateFormValue}/> */}

            <div className="dropdown dropdown-bottom dropdown-end mr-4 mt-4 form-control w-full">
            <label className="label">
                <span className="label-text text-base-content ">role</span>
            </label>
            <label tabIndex="0" className="btn m-1" onClick={toggleDropdown}>{leadObj.role}</label>
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
            <div className="modal-action flex justify-between items-center">
                <div className="flex-grow">
                    <button className="btn btn-primary bg-red-600 text-white px-6" onClick={() => deleteCurrentLead(index)}>Delete</button>
                </div>
                <div className="flex space-x-4">
                    <button className="btn btn-ghost" onClick={() => closeModal()}>Cancel</button>
                    <button className="btn btn-primary px-6" onClick={() => saveNewLead()}>Save</button>
                </div>
</div>
        </>
    )
}

export default EditUserModalBody
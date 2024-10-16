import {useDispatch, useSelector} from 'react-redux'
import axios from 'axios'
import { CONFIRMATION_MODAL_CLOSE_TYPES, MODAL_CLOSE_TYPES } from '../../../utils/globalConstantUtil'
import { deleteLead } from '../../leads/leadSlice'
import { showNotification } from '../headerSlice'

function ConfirmationModalBody({ extraObject, closeModal}){

    const dispatch = useDispatch()

    const { message, type, index, userId} = extraObject

    const DeleteUser = async () => {
        const apiUrl =  `http://localhost:8080/api/users/delete/${userId}`
              try{
                const res = await fetch(apiUrl,{
                    method: 'DELETE',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                });
                if (res.ok){
                    dispatch(deleteLead({index}))
                    dispatch(showNotification({message : "User Deleted!", status : 1}))
                }
              } catch(error) {
                  console.log("Error Deleting User", error);
        
              } finally {
                  console.log("We Deleted User");
              }
      };

    const proceedWithYes = async() => {
        if(type === CONFIRMATION_MODAL_CLOSE_TYPES.LEAD_DELETE){
            // positive response, call api or dispatch redux function
            DeleteUser()
        }
        closeModal()
    }

    return(
        <> 
        <p className=' text-xl mt-8 text-center'>
            {message}
        </p>

        <div className="modal-action mt-12">
                
                <button className="btn btn-outline   " onClick={() => closeModal()}>Cancel</button>

                <button className="btn btn-primary w-36" onClick={() => proceedWithYes()}>Yes</button> 

        </div>
        </>
    )
}

export default ConfirmationModalBody
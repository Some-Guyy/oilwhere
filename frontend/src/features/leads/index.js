import { useEffect } from "react"
import { useDispatch, useSelector } from "react-redux"
import TitleCard from "../../components/Cards/TitleCard"
import { openModal } from "../common/modalSlice"
import { getLeadsContent } from "./leadSlice"
import { CONFIRMATION_MODAL_CLOSE_TYPES, MODAL_BODY_TYPES } from '../../utils/globalConstantUtil'
import { PencilIcon } from '@heroicons/react/24/outline'
import { showNotification } from '../common/headerSlice'

const TopSideButtons = () => {

    const dispatch = useDispatch()

    const openAddNewLeadModal = () => {
        dispatch(openModal({title : "Add New Lead", bodyType : MODAL_BODY_TYPES.LEAD_ADD_NEW}))
    }

    return(
        <div className="inline-block float-right">
            <button className="btn px-6 btn-sm normal-case btn-primary" onClick={() => openAddNewLeadModal()}>Add New</button>
        </div>
    )
}

function Leads(){

    const {leads } = useSelector(state => state.lead)
    const dispatch = useDispatch()

    useEffect(()=>{
        const TOKEN = JSON.parse(localStorage.getItem("token"))
        const role = TOKEN.role
        if(role != "ADMIN"){
            window.location.href = '/app/welcome'
        }
    },[])

    useEffect(() => {
        dispatch(getLeadsContent())
    }, [])

    

    // console.log(leads)

    const EditCurrentUSer = (data,index) => {
        dispatch(openModal({title : "Edit User", bodyType : MODAL_BODY_TYPES.EDIT_USER,
        extraObject : {data,index}
        }))
    }

    return(
        <>
            
            <TitleCard title="Users" topMargin="mt-2" TopSideButtons={<TopSideButtons />}>

                {/* Leads List in table format loaded from slice after api call */}
            <div className="overflow-x-auto w-full">
                <table className="table w-full">
                    <thead>
                    <tr>
                        <th>UserID</th>
                        <th>Name</th>
                        <th>Role</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            leads.map((l, k) => {
                                return(
                                    <tr key={k}>
                                    <td>
                                        <div className="flex items-center space-x-3">
                                            <div>
                                                <div className="font-bold">{l.userId}</div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>{l.username}</td>
                                    <td>{l.role}</td>
                                    {l.role!="ADMIN"?<td><button className="btn btn-square btn-ghost" onClick={() => EditCurrentUSer(l,k)}><PencilIcon className="h-6 w-6 text-gray-500" /></button></td>:<td></td>}
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>
            </TitleCard>
        </>
    )
}


export default Leads
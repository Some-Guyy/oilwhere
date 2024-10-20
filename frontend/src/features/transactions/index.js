import moment from "moment"
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import { showNotification } from "../common/headerSlice"
import TitleCard from "../../components/Cards/TitleCard"
import { RECENT_TRANSACTIONS } from "../../utils/dummyData"
import FunnelIcon from '@heroicons/react/24/outline/FunnelIcon'
import XMarkIcon from '@heroicons/react/24/outline/XMarkIcon'
import SearchBar from "../../components/Input/SearchBar"
import { getTransactionsContent } from "./transactionsSlice"
import Datepicker from "react-tailwindcss-datepicker"; 

const TopSideButtons = ({removeFilter, applyFilter, applySearch}) => {

    const [filterParam, setFilterParam] = useState([])
    const [searchText, setSearchText] = useState("")
    const SaleTypefilters = ["Consignment", "Direct - B2B", "Direct - B2C", "Marketing", "Wholesaler","null"]
    const [isMounted, setIsMounted] = useState(false);
    // const [formatedDateValue,setformatedDateValue] = useState({ 
    //     startDate: null, 
    //     endDate: null 
    // }); 
    const [dateValue, setDateValue] = useState({ 
        startDate: null, 
        endDate: null 
    }); 
    
    const handleDatePickerValueChange = (newValue) => {
        console.log("newValue:", newValue); 
        setDateValue(newValue); 
        // const formattedStartDate = newValue.startDate.getFullYear() + '-' + String(newValue.startDate.getMonth() + 1).padStart(2, '0') + '-' + String(newValue.startDate.getDate()).padStart(2, '0');
        // const formattedEndDate = newValue.endDate.getFullYear() + '-' + String(newValue.endDate.getMonth() + 1).padStart(2, '0') + '-' + String(newValue.endDate.getDate()).padStart(2, '0');
        // setformatedDateValue({
        //     startDate: formattedStartDate,
        //     endDate: formattedEndDate,
        // });
    } 

    const showFiltersAndApply = (params) => {
        if (filterParam.includes(params)){
            setFilterParam(filterParam.filter(param => param !== params))
        }
        else{
            setFilterParam([...filterParam,params])
        }
    }

    useEffect(() => {
        if (isMounted) {
            applyFilter(filterParam,searchText,dateValue);
        } else {
            setIsMounted(true);
        }
    }, [filterParam,searchText,dateValue]);

    const removeAppliedFilter = () => {
        removeFilter()
        setFilterParam("")
        setSearchText("")
        setDateValue({
                startDate: null,
                endDate: null,
            });
    }

    return(
        <div className="inline-block float-right">
            <div className="inline-block" >
                <Datepicker 
                    containerClassName="w" 
                    value={dateValue} 
                    theme={"light"}
                    inputClassName="input input-bordered w-50" 
                    popoverDirection={"down"}
                    toggleClassName="invisible"
                    onChange={handleDatePickerValueChange} 
                    showShortcuts={true} 
                    primaryColor={"white"} 
                /> 
            </div>
            <SearchBar searchText={searchText} styleClass="mr-4" setSearchText={setSearchText} placeholderText="Search CId"/>
            {/* {filterParam.length>0 && <button onClick={() => removeAppliedFilter()} className="btn btn-xs mr-2 btn-active btn-ghost normal-case">{filterParam}<XMarkIcon className="w-4 ml-2"/></button>} */}
            <div className="dropdown dropdown-bottom dropdown-end">
                <label tabIndex={0} className="btn btn-sm btn-outline "><FunnelIcon className="w-5 mr-2"/>SaleType</label>
                <ul tabIndex={0} className="dropdown-content menu p-2 text-sm shadow bg-base-100 rounded-box w-40 z-10">
                    {
                        SaleTypefilters.map((l, k) => {
                            return  <li key={k}><a className={filterParam.includes(l) ? "bg-gray-700" : ""} onClick={() => showFiltersAndApply(l)}>{l}</a></li>
                        })
                    }
                    <div className="divider mt-0 mb-0"></div>
                    <li><a onClick={() => removeAppliedFilter()}>Remove Filter</a></li>
                </ul>
            </div>
        </div>

    )
}




function Transactions(){
    const {transactions } = useSelector(state => state.transaction)
    const dispatch = useDispatch()

    useEffect(() => {
        if(transactions.length == 0){
        dispatch(getTransactionsContent())
        }
    }, [])

    const [trans, setTrans] = useState(transactions)

    const removeFilter = () => {
        setTrans(transactions)
    }

    const applyFilter = (params,Id,date) => {
        console.log(date)
        let filteredTransactions = transactions
        if (params.length == 0 || Id == "" || date.startDate == null){
            setTrans(transactions)
        }
        if(Id != ""){
        filteredTransactions = filteredTransactions.filter((t) => {return t.customerId == Id})
        }
        if (params.length != 0){
            filteredTransactions = filteredTransactions.filter((t) => params.includes(t.saleType))
        }
        if(date.startDate != null){
            filteredTransactions = filteredTransactions.filter((t) => {
                const transactionDate = new Date(t.saleDate);
                return transactionDate >= date.startDate && transactionDate <= date.endDate 
            });
            
        }
        setTrans(filteredTransactions)
    }

    // Search according to name
    // const applySearch = (value) => {
    //     console.log(value)
    //     let filteredTransactions = transactions.filter((t) => {return t.customerId == value})
    //     setTrans(filteredTransactions)
    // }

    return(
        <>
            
            <TitleCard title="Recent Transactions" topMargin="mt-2" TopSideButtons={<TopSideButtons  applyFilter={applyFilter} removeFilter={removeFilter}/>}>

                {/* Team Member list in table format loaded constant */}
            <div className="overflow-x-auto w-full">
                <table className="table w-full">
                    <thead>
                    <tr>
                        <th>Purchase Id</th>
                        <th>Sale Date</th>
                        <th>Sale Type</th>
                        <th>Digital</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Prodcut Price</th>
                        <th>Customer Id</th>
                        <th>Zipcode</th>
                        <th>shipping Method</th>
                        <th>Variant</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            trans.map((l, k) => {
                                return(
                                    <tr key={k}>
                                    <td>{l.purchaseId}</td>
                                    <td>{l.saleDate}</td>
                                    {l.saleType == null?
                                    <td>null</td>:
                                    <td>{l.saleType}</td>}
                                    <td>{l.digital}</td>
                                    <td>{l.product}</td>
                                    <td>{l.quantity}</td>
                                    <td>${l.price}</td>
                                    <td>${l.productPrice}</td>
                                    <td>{l.customerId}</td>
                                    <td>{l.zipcode}</td>
                                    <td>{l.shippingMethod}</td>
                                    <td>{l.variant}</td>
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


export default Transactions
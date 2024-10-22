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
    const [Above,setAbove] = useState("")
    const [Below,setBelow] = useState("")
    // const [formatedDateValue,setformatedDateValue] = useState({ 
    //     startDate: null, 
    //     endDate: null 
    // }); 
    const [dateValue, setDateValue] = useState({ 
        startDate: null, 
        endDate: null 
    }); 
    
    const handleDatePickerValueChange = (newValue) => {
        // console.log("newValue:", newValue); 
        setDateValue(newValue); 
        // const formattedStartDate = newValue.startDate.getFullYear() + '-' + String(newValue.startDate.getMonth() + 1).padStart(2, '0') + '-' + String(newValue.startDate.getDate()).padStart(2, '0');
        // const formattedEndDate = newValue.endDate.getFullYear() + '-' + String(newValue.endDate.getMonth() + 1).padStart(2, '0') + '-' + String(newValue.endDate.getDate()).padStart(2, '0');
        // setformatedDateValue({
        //     startDate: formattedStartDate,
        //     endDate: formattedEndDate,
        // });
    } 

    const updateAboveInput = (num) => {
        setAbove(num)
    }

    const updateBelowInput = (num) => {
        setBelow(num)
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
            applyFilter(filterParam,searchText,dateValue,Above,Below);
        } else {
            setIsMounted(true);
        }
    }, [filterParam,searchText,dateValue,Above,Below]);

    const removeAppliedFilter = () => {
        removeFilter()
        setFilterParam("")
        setSearchText("")
        setDateValue({
                startDate: null,
                endDate: null,
            });
        setAbove("")
        setBelow("")
    }

    return(
        <div className="inline-block float-right">
            <button onClick={() => removeAppliedFilter()} className="btn btn-xs mr-2 btn-active btn-ghost normal-case">Reser filters<XMarkIcon className="w-4 ml-2"/></button>
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
            <div className="dropdown dropdown-bottom dropdown-end mr-4">
                <label tabIndex={0} className="btn btn-sm btn-outline "><FunnelIcon className="w-5 mr-2"/>SaleType</label>
                <ul tabIndex={0} className="dropdown-content menu p-2 text-sm shadow bg-base-100 rounded-box w-40 z-10">
                    {
                        SaleTypefilters.map((l, k) => {
                            return  <li key={k}><a className={filterParam.includes(l) ? "bg-gray-700" : ""} onClick={() => showFiltersAndApply(l)}>{l}</a></li>
                        })
                    }
                    <div className="divider mt-0 mb-0"></div>
                </ul>
            </div>
            <div className="dropdown dropdown-bottom dropdown-end">
                <label tabIndex={0} className="btn btn-sm btn-outline "><FunnelIcon className="w-5 mr-2"/>Value</label>
                <ul tabIndex={0} className="dropdown-content menu p-2 text-sm shadow bg-base-100 rounded-box w-40 z-10">
                            {/* First Input Field */}
                    <li className="mt-4">
                        <input 
                            type="number" 
                            placeholder="Above"
                            value={Above} 
                            className="input input-sm input-bordered w-full mb-2 [appearance:textfield] [&::-webkit-outer-spin-button]:appearance-none [&::-webkit-inner-spin-button]:appearance-none"
                            onChange={(e) => updateAboveInput(e.target.value)} 
                        />
                    </li>
                    
                    {/* Second Input Field */}
                    <li className="mt-2">
                        <input 
                            type="number" 
                            placeholder="Below" 
                            value={Below}
                            className="input input-sm input-bordered w-full mb-2 [appearance:textfield] [&::-webkit-outer-spin-button]:appearance-none [&::-webkit-inner-spin-button]:appearance-none" 
                            onChange={(e) => updateBelowInput(e.target.value)}
                        />
                    </li>
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

    const applyFilter = (params,Id,date,Above,Below) => {
        let filteredTransactions = transactions
        if (params.length == 0 || Id == "" || date.startDate == null || Above == "" || Below == ""){
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
        if (Above != ""){
            filteredTransactions = filteredTransactions.filter((t) => {
                const transactionPrice = t.productPrice;
                return transactionPrice >= parseInt(Above)
            });  
        }
        if (Below != ""){
            filteredTransactions = filteredTransactions.filter((t) => {
                const transactionPrice = t.productPrice;
                return transactionPrice <= parseInt(Below)
            });  
        }
        firstPage()
        setTrans(filteredTransactions)
    }

    const [itemsPerPage,setitemsPerPage] = useState(10);
    const [lowerlimit,setlowerlimit] = useState(1)
    const [upperlimit,setupperlimit] = useState(5)
    const [currentpage,setcurrentPage] = useState(1)

    const totalPages = Math.ceil(trans.length / itemsPerPage);

    const handlePageChange = (value) => {
        if (value > totalPages || value < 1){
            return
        }
        if (lowerlimit > 2 && upperlimit-2 < trans.length){
            setlowerlimit(value-2)
            setupperlimit(value+2)
        }
        setcurrentPage(value)
    }

    const firstPage = () => {
        setcurrentPage(1)
        setlowerlimit(1)
        setupperlimit(5)
    }

    const lastpage = () => {
        setcurrentPage(totalPages)
        setupperlimit(totalPages)
        if(totalPages-5 > 1){
            setlowerlimit(totalPages-5)
        }
        else{
            setlowerlimit(1)
        }
    }

    return(
        <>
            
            <TitleCard title="Recent Transactions" topMargin="mt-2" TopSideButtons={<TopSideButtons  applyFilter={applyFilter} removeFilter={removeFilter}/>}>
            <div className="flex justify-center space-x-1 text-white mb-2">

                <button title="First" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>firstPage()}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="13 18 9 12 13 6"></polyline>  
                        <polyline points="19 18 15 12 19 6"></polyline> 
                    </svg>
                </button>
                <button title="previous" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>handlePageChange(currentpage-1)}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                </button>
                {Array.from({ length: totalPages > 5 ? 5: totalPages }, (_, index) => {
                    const buttonNumber = index + lowerlimit;
                    return (
                    <button
                        key={buttonNumber}
                        data-value={buttonNumber}
                        onClick={(e)=>handlePageChange(e.currentTarget.dataset.value)}
                        className={currentpage==buttonNumber?"inline-flex items-center justify-center w-8 h-8 text-sm font-semibold border rounded shadow-md dark:border-gray-600  dark:border-violet-600":"inline-flex items-center justify-center w-8 h-8 text-sm font-semibold border rounded shadow-md dark:border-gray-600 "}
                    >
                        {buttonNumber}
                    </button> 
                    );
                })}

                <button title="next" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>handlePageChange(currentpage+1)}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="9 18 15 12 9 6"></polyline>
                    </svg>
                </button>
                <button title="last" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>lastpage()}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="11 6 15 12 11 18"></polyline> 
                        <polyline points="5 6 9 12 5 18"></polyline>  
                    </svg>
                </button>

            </div>

                {/* Team Member list in table format loaded constant */}
            {trans.length == 0?<span className="flex justify-center text-xl">no applicable transaction</span>:
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
                            trans.slice(itemsPerPage*(currentpage-1),itemsPerPage*currentpage).map((l, k) => {
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
            }
            <div className="flex justify-center space-x-1 text-white mt-2">

                <button title="First" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>firstPage()}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="13 18 9 12 13 6"></polyline>  
                        <polyline points="19 18 15 12 19 6"></polyline> 
                    </svg>
                </button>
                <button title="previous" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>handlePageChange(currentpage-1)}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                </button>
                {Array.from({ length: totalPages > 5 ? 5: totalPages }, (_, index) => {
                    const buttonNumber = index + lowerlimit;
                    return (
                    <button
                        key={buttonNumber}
                        data-value={buttonNumber}
                        onClick={(e)=>handlePageChange(e.currentTarget.dataset.value)}
                        className={currentpage==buttonNumber?"inline-flex items-center justify-center w-8 h-8 text-sm font-semibold border rounded shadow-md dark:border-gray-600  dark:border-violet-600":"inline-flex items-center justify-center w-8 h-8 text-sm font-semibold border rounded shadow-md dark:border-gray-600 "}
                    >
                        {buttonNumber}
                    </button> 
                    );
                })}

                <button title="next" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>handlePageChange(currentpage+1)}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="9 18 15 12 9 6"></polyline>
                    </svg>
                </button>
                <button title="last" type="button" className="inline-flex items-center justify-center w-8 h-8 py-0 border rounded-md shadow-md dark:border-gray-600 dark:border-gray-100" onClick={(e)=>lastpage()}>
                    <svg viewBox="0 0 24 24" stroke="currentColor" strokeWidth="2" fill="none" strokeLinecap="round" strokeLinejoin="round" className="w-4">
                        <polyline points="11 6 15 12 11 18"></polyline> 
                        <polyline points="5 6 9 12 5 18"></polyline>  
                    </svg>
                </button>

            </div>
            </TitleCard>
        </>
    )
}


export default Transactions
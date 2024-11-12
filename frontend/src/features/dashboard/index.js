import DashboardStats from './components/DashboardStats'
import AmountStats from './components/AmountStats'
import PageStats from './components/PageStats'

import UserGroupIcon  from '@heroicons/react/24/outline/UserGroupIcon'
import UsersIcon  from '@heroicons/react/24/outline/UsersIcon'
import CircleStackIcon  from '@heroicons/react/24/outline/CircleStackIcon'
import CreditCardIcon  from '@heroicons/react/24/outline/CreditCardIcon'
import UserChannels from './components/UserChannels'
import LineChart from './components/LineChart'
import BarChart from './components/BarChart'
import DashboardTopBar from './components/DashboardTopBar'
import { useDispatch, useSelector } from "react-redux"
import {showNotification} from '../common/headerSlice'
import DoughnutChart from './components/DoughnutChart'
import { useEffect, useState } from "react"
import { getTransactionsContent } from '../transactions/transactionsSlice'
import SearchBar from '../../components/Input/SearchBar'
import SuspenseContent from '../../containers/SuspenseContent'
import XMarkIcon from '@heroicons/react/24/outline/XMarkIcon'



function Dashboard(){
    // const [isLoading, setisLoading] = useState(false);


    const dispatch = useDispatch()
    const {transactions } = useSelector(state => state.transaction)

    const [trans, setTrans] = useState([]);
    useEffect(() => {
        if (transactions.length === 0) {
            dispatch(getTransactionsContent());
        } else {
            setTrans(transactions);
        }
    }, [dispatch, transactions]);

    // const {transactions } = useSelector(state => state.transaction)
    const isLoading = useSelector((state) => state.transaction.isLoading);

    const [isMounted, setIsMounted] = useState(false);
    const [searchText, setSearchText] = useState("")
    const [dateValue, setDateValue] = useState({ 
        startDate: null, 
        endDate: null 
    }); 


    useEffect(() => {
        if (isMounted) {
            applyFilter(searchText,dateValue);
        } else {
            setIsMounted(true);
        }
    }, [searchText,dateValue]);

    let totalValue = 0

    for(let i=0;i<trans.length;i++){
        totalValue += trans[i].productPrice
    }

    const statsData = !isLoading && trans.length > 0
    ? [
        {
            title: "Total Number of Sales",
            value: trans.length,
            icon: <UserGroupIcon className="w-8 h-8" />,
        },
        {
            title: "Total Value of Sales",
            value: "$" + totalValue.toFixed(2),
            icon: <CreditCardIcon className="w-8 h-8" />,
        },
        {
            title: "Average Order Value",
            value: "$" + (totalValue / trans.length).toFixed(2),
            icon: <CircleStackIcon className="w-8 h-8" />,
        }
    ]
    : [];


    const removeAppliedFilter = () => {
        setTrans(transactions)
        setSearchText("")
        setDateValue({
                startDate: null,
                endDate: null,
            });
    }

 

    const updateDashboardPeriod = (newRange) => {
        // Dashboard range changed, write code to refresh your values
        dispatch(showNotification({message : `Period updated to ${newRange.startDate} to ${newRange.endDate}`, status : 1}))
        setDateValue(newRange); 
    }

    const applyFilter = (Id,date) => {
        let filteredTransactions = transactions
        if (Id == "" || date.startDate == null){
            setTrans(transactions)
        }
        if(Id != ""){
        filteredTransactions = filteredTransactions.filter((t) => {return t.customerId == Id})
        }
        if(date.startDate != null){
            filteredTransactions = filteredTransactions.filter((t) => {
                const transactionDate = new Date(t.saleDate);
                return transactionDate >= date.startDate && transactionDate <= date.endDate 
            });  
        }
        setTrans(filteredTransactions)
    }

    if(isLoading){
        return  <SuspenseContent />
    }

    let labels = []
    const dateMapSales = {}
    const dateMapRevenue = {}
    const saleTypeMap = {}
    const productMap = {}

    if(!isLoading && trans.length > 0){
        const firstDate = new Date(trans[trans.length-1].saleDate)
        const lastDate = new Date(trans[0].saleDate)
        // Calculate the difference in years and months
        const startYear = firstDate.getFullYear()
        const endYear = lastDate.getFullYear()
        const yearDiff = endYear - startYear;
        const monthDiff = lastDate.getMonth() - firstDate.getMonth();
        // Total month difference
        const totalMonths = yearDiff * 12 + monthDiff;
        const monthNames = [
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
        ];
        

        if(totalMonths >12){
        trans.forEach(transaction => {
            if(transaction.product in productMap){
                productMap[transaction.product] += 1
            }
            else{
                productMap[transaction.product] = 1
            }
            if (transaction.saleType in saleTypeMap){
                saleTypeMap[transaction.saleType] += 1
            }
            else{
                saleTypeMap[transaction.saleType] = 1
            }

            let year = new Date(transaction.saleDate).getFullYear()
            if (year in dateMapRevenue){
            dateMapRevenue[year] += transaction.productPrice
            dateMapSales[year] += 1
            }
            else{
            dateMapRevenue[year] = transaction.productPrice
            dateMapSales[year] = 1
            }
        });

        }
        else{
        trans.reverse().forEach(transaction =>{
            if(transaction.product in productMap){
                productMap[transaction.product] += 1
            }
            else{
                productMap[transaction.product] = 1
            }
            if (transaction.saleType in saleTypeMap){
                saleTypeMap[transaction.saleType] += 1
            }
            else{
                saleTypeMap[transaction.saleType] = 1
            }
            let transDate = new Date(transaction.saleDate)
            let month = monthNames[transDate.getMonth()];
            let year = transDate.getFullYear();
            let keyName = (`${month} ${year}`)
            if (keyName in dateMapRevenue){
                dateMapRevenue[keyName] += transaction.productPrice
                dateMapSales[keyName] += 1
            }
            else{
                dateMapRevenue[keyName] = transaction.productPrice
                dateMapSales[keyName] = 1
            }
        })
        
        }
        labels = Object.keys(dateMapRevenue)
    }    

    return(
        <>
        {/** ------------------A---- Select Period Content ------------------------- */}
        <div >
            <div className="grid grid-cols-1 sm:grid-cols-3">
                <DashboardTopBar updateDashboardPeriod={updateDashboardPeriod}/>            
                <SearchBar searchText={searchText} styleClass="mt-2" setSearchText={setSearchText} placeholderText="Search CId"/>
            </div>
            <button onClick={() => removeAppliedFilter()} className="btn btn-xs mr-2 btn-active btn-ghost normal-case">Reset filters<XMarkIcon className="w-4 ml-2"/></button>
        </div>

        {trans.length==0?(<div>no such user</div>):(
        <>
        {/** ---------------------- Different stats content 1 ------------------------- */}
            <div className="grid lg:grid-cols-3 mt-2 md:grid-cols-2 grid-cols-1 gap-6">
                {
                    statsData.map((d, k) => {
                        return (
                            <DashboardStats key={k} {...d} colorIndex={k}/>
                        )
                    })
                }
            </div>


        {/** ---------------------- Different charts ------------------------- */}
            <div className="grid lg:grid-cols-2 mt-4 grid-cols-1 gap-6">
                {/* <LineChart data={trans}/> */}
                <BarChart labels={labels} dateMap={dateMapSales} title={"Number of Sales"} color={'rgb(53, 162, 235)'}/>
                <BarChart labels={labels} dateMap={dateMapRevenue} title={"Revenue"} color={'rgb(0, 128, 0)'}/>
            </div>
            
        {/** ---------------------- Different stats content 2 ------------------------- */}
        
            {/* <div className="grid lg:grid-cols-2 mt-10 grid-cols-1 gap-6">
                <AmountStats />
                <PageStats />
            </div> */}

        {/** ---------------------- User source channels table  ------------------------- */}
        
            <div className="grid lg:grid-cols-2 mt-4 grid-cols-1 gap-6">
                <UserChannels hashmap={productMap}/>
                <DoughnutChart hashmap={saleTypeMap} />
                {/* <DoughnutChart /> */}

            </div>
        </>
        )}
        </>
    )
}

export default Dashboard
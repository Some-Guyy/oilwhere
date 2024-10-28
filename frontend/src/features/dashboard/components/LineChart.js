import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Filler,
  Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import TitleCard from '../../../components/Cards/TitleCard';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Filler,
  Legend
);

function LineChart(trans){

  const firstDate = new Date(trans.data[trans.data.length-1].saleDate)
  const lastDate = new Date(trans.data[0].saleDate)
  // Calculate the difference in years and months
  const startYear = firstDate.getFullYear()
  const endYear = lastDate.getFullYear()
  const yearDiff = endYear - startYear;
  const monthDiff = lastDate.getMonth() - firstDate.getMonth();
  // Total month difference
  const totalMonths = yearDiff * 12 + monthDiff;
  console.log(totalMonths)
  const monthNames = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];
  const labels = []
  if(totalMonths >12){
    for (let year = startYear;year<endYear+1;year++){
      labels.push(year)
    }
  }
  else{
    while (firstDate <= lastDate) {
        const month = monthNames[firstDate.getMonth()];
        const year = firstDate.getFullYear();
        labels.push(`${month} ${year}`)
        firstDate.setMonth(firstDate.getMonth() + 1);
    }
  }
  console.log(labels)

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
    },
  };

  
  // const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

  const data = {
  labels,
  datasets: [
    {
      fill: true,
      label: 'MAU',
      data: labels.map(() => { return Math.random() * 100 + 500 }),
      borderColor: 'rgb(53, 162, 235)',
      backgroundColor: 'rgba(53, 162, 235, 0.5)',
    },
  ],
};
  

    return(
      <TitleCard title={"Montly Active Users (in K)"}>
          <Line data={data} options={options}/>
      </TitleCard>
    )
}


export default LineChart
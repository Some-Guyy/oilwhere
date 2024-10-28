import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';
import TitleCard from '../../../components/Cards/TitleCard';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

function BarChart({labels,dateMap,title,color}){

    const options = {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          }
        },
      };
      
      // const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
      
      const data = {
        labels,
        datasets: [
          {
            label: title,
            data: labels.map((j) =>  dateMap[j] ),
            backgroundColor: color,
          },
        ],
      };

    return(
      <TitleCard title={title}>
            <Bar options={options} data={data} />
      </TitleCard>

    )
}


export default BarChart
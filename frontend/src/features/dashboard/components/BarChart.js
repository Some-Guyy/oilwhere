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
        scales: {
          x: {
            ticks: {
              color: '#cfd4e2',  // Color for x-axis labels
            }
          },
          y: {
            ticks: {
              color: '#cfd4e2',  // Color for y-axis labels
            }
          }
        },
        plugins: {
          tooltip: {
            footerColor: 'white'
          },
          legend: {
            position: 'top',
            labels: {
              color: '#cfd4e2',
          },
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
            color: '#cfd4e2',
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
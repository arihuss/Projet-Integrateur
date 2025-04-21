
const ctx = document.getElementById('donutChart');
const invites = donutData.invites;
const benevoles = donutData.benevoles;

let data, backgroundColor, labels;

if (invites === 0 && benevoles === 0) {
  
  data = [1];  
  backgroundColor = ['#CCCCCC']; 
  labels = ['Aucune donnée'];
} else {
  data = [invites, benevoles];
  backgroundColor = ['#FFD86C', '#FF99CC'];
  labels = ['Invités', 'Bénévoles'];
}

new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: labels,
    datasets: [{
      label: 'Statistiques',
      data: data,
      backgroundColor: backgroundColor,
      borderWidth: 0
    }]
  },
  options: {
    cutout: '80%',
    plugins: {
      legend: { display: false }
    }
  }
});

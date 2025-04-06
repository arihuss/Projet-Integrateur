const ctx = document.getElementById('donutChart');

new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ['Invités', 'Bénévoles'],
    datasets: [{
      label: 'Statistiques',
      data: [donutData.invites, donutData.benevoles],
      backgroundColor: ['#FFD86C', '#FF99CC'],
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

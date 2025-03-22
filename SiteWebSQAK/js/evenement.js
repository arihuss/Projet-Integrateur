
const ctx = document.getElementById('donutChart');

  new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ['Invités', 'Bénévoles'],
      datasets: [{
        label: 'Statistiques',
        data: [125, 15],
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
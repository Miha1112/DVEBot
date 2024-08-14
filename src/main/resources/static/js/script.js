
document.addEventListener("DOMContentLoaded", () => {
    const tableBody = document.querySelector("table tbody");

    // Функція для завантаження даних з API
    async function fetchUsers() {
        try {
            const response = await fetch('/api/users');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const users = await response.json();
            displayUsers(users);
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
    }

    // Функція для відображення користувачів у таблиці
    function displayUsers(users) {
        tableBody.innerHTML = ''; // Очистити попередній вміст таблиці
        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${user.name || 'N/A'}</td>
                <td>${user.firstAnswer || 'N/A'}</td>
                <td>${user.secondAnswer || 'N/A'}</td>
                <td>${user.secondAnswer1 || 'N/A'}</td>
                <td>${user.secondAnswer2 || 'N/A'}</td>
                <td>${user.secondAnswer3 || 'N/A'}</td>
                <td>${user.secondAnswer4 || 'N/A'}</td>
                <td>${user.secondAnswer5 || 'N/A'}</td>
                <td>${user.secondAnswer6 || 'N/A'}</td>
                <td>${user.secondAnswer7 || 'N/A'}</td>
                <td>${user.secondAnswer8 || 'N/A'}</td>
                <td>${user.secondAnswer9 || 'N/A'}</td>
                <td>${user.secondAnswer10 || 'N/A'}</td>
                <td>${user.secondAnswer11 || 'N/A'}</td>
                <td>${user.secondAnswer12 || 'N/A'}</td>
                <td>${user.secondAnswer13 || 'N/A'}</td>
                <td>${user.secondAnswer14 || 'N/A'}</td>
                <td>${user.secondAnswer15 || 'N/A'}</td>
                <td>${user.secondAnswer16 || 'N/A'}</td>
                <td>${user.secondAnswer17 || 'N/A'}</td>
                <td>${user.secondAnswer18 || 'N/A'}</td>
                
            `;
            tableBody.appendChild(row);
        });
    }

    // Завантажити користувачів при завантаженні сторінки
    fetchUsers();
});


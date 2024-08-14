
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
            `;
            tableBody.appendChild(row);
        });
    }

    // Завантажити користувачів при завантаженні сторінки
    fetchUsers();
});


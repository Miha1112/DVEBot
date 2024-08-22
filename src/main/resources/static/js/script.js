document.addEventListener("DOMContentLoaded", () => {
    const questionsList = ["","Чи подобається Вам ваша робота?","Чи хотіли б Ви перейти на іншу роботу?",
        "Оцініть, будь ласка, за п'ятибальною шкалою ступінь розвитку наведених нижче якостей у вашого безпосереднього керівника: 5 – якість розвинена дуже сильно; 1 – якість не розвинене.",
        "Хто із членів колективу користується найбільшою повагою у колег? Напишіть два-три прізвища.",
        "Припустимо, що з якихось причин Ви тимчасово не працюєте: чи повернулися Ви на своє нинішнє місце роботи?",
        "Виділіть, будь ласка, з яким із наведених тверджень Ви найбільше згодні.",
        "Чи вважаєте Ви, що було б добре, якби члени вашого колективу жили близько один від одного?",
        "Зверніть увагу на наведену нижче шкалу. Цифра 1 характеризує колектив, який вам дуже подобається, а 9 – колектив, який вам дуже не подобається. У яку стрічку Ви помістили свій колектив?",
        "Як Вам здається, могли б Ви дати досить повну характеристику ділових та особистих якостей більшості членів колективу",
        "Чи могли б Ви з упевненістю сказати про більшість членів вашого колективу з ким вони охоче спілкуються з ділових питань?",
        "Яка атмосфера зазвичай переважає у Вашому колективі? На наведеній нижче шкалі цифра 1 відповідає хворій, нетовариській атмосфері, а 9 - навпаки, атмосфері взаєморозуміння, взаємної поваги. У яку із стрічки Ви помістили б свій колектив?",
        "Як Ви думаєте, якби Ви вийшли на пенсію або довго не працювали з якоїсь причини, чи прагнули Ви зустрічатися з членами вашого колективу?",
        "Вкажіть, будь ласка, якою мірою Ви задоволені різними умовами роботи?",
        "Наскільки добре, на вашу думку, організовано вашу роботу?",
        "Як Ви вважаєте, чи має ваш керівник реальний вплив на справи колективу?",
        "Ваша стать","Ваш вік","Освіта","Стаж роботи в компанії","Оберіть бренд в якому ви працюєте"]


    const userList = document.querySelector("#user-list tbody");
    const userAnswers = document.querySelector("#user-answers tbody");
    const userAnswersContainer = document.getElementById("user-answers");
    const userListContainer = document.getElementById("user-list");
    const backButton = document.getElementById("back-button");
    const messageButton = document.getElementById("message-button");
    const sendMessageContainer = document.getElementById("send-message");
    const messageForm = document.getElementById("message-form");
    const usernameField = document.getElementById("username");
    const messageField = document.getElementById("message");
    const userTelegramIdElement = document.getElementById("user-telegram-id");

    async function fetchUsers() {
        try {
            const response = await fetch('/api');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const users = await response.json();
            displayUserNames(users);
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
    }

    function displayUserNames(users) {
        userList.innerHTML = ''; // Очистити попередній вміст таблиці

        users.forEach(user => {
            const row = document.createElement('tr');

            // Колонка з ім'ям користувача
            const nameCell = document.createElement('td');
            nameCell.textContent = user.name || 'N/A';
            nameCell.style.cursor = 'pointer';
            nameCell.addEventListener('click', () => displayUserAnswers(user));
            row.appendChild(nameCell);

            // Колонка з Telegram ID користувача
            const telegramIdCell = document.createElement('td');
            telegramIdCell.textContent = user.telegramId || 'N/A';
            row.appendChild(telegramIdCell);

            userList.appendChild(row);
        });
    }


    function displayUserAnswers(user) {
        userAnswers.innerHTML = ''; // Очистити попередній вміст таблиці

        // Встановити Telegram ID
        const userTelegramIdElement = document.getElementById('user-telegram-id');
        userTelegramIdElement.textContent = user.telegramId || 'N/A';

        // Заповнити таблицю відповідями користувача
        for (let i = 1; i <= 20; i++) {
            const answerKey = `secondAnswer${i}`;
            const row = document.createElement('tr');
            row.innerHTML = `
            <td>${questionsList[i]}</td>
            <td>${user[answerKey] || 'N/A'}</td>
        `;
            userAnswers.appendChild(row);
        }

        userListContainer.style.display = 'none';
        userAnswersContainer.style.display = 'block';
        usernameField.value = user.name; // Заповнити поле імені користувача
    }
    backButton.addEventListener('click', () => {
        userListContainer.style.display = 'block';
        userAnswersContainer.style.display = 'none';
    });

    messageButton.addEventListener('click', () => {
        sendMessageContainer.style.display = 'block';
    });

    messageForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const data = {
            telegramId: userTelegramIdElement.textContent, // Використовуємо Telegram ID з відображеного поля
            message: messageField.value
        };

        try {
            const response = await fetch('/api/send-message', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                alert('Повідомлення відправлено!');
                sendMessageContainer.style.display = 'none';
                userListContainer.style.display = 'block';
            } else {
                alert('Сталася помилка при відправці повідомлення.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Сталася помилка при відправці повідомлення.');
        }finally {
            backButton.click();
        }
    });

    fetchUsers();
});
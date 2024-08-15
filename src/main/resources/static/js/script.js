document.addEventListener("DOMContentLoaded", () => {
    const userList = document.querySelector("#user-list tbody");
    const userAnswers = document.querySelector("#user-answers tbody");
    const userAnswersContainer = document.getElementById("user-answers");
    const userListContainer = document.getElementById("user-list");
    const backButton = document.getElementById("back-button");
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

    // Функція для завантаження даних з API
    async function fetchUsers() {
        try {
            const response = await fetch('/api/users');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const users = await response.json();
            displayUserNames(users);
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
    }

    // Функція для відображення імен користувачів у таблиці
    function displayUserNames(users) {
        userList.innerHTML = ''; // Очистити попередній вміст таблиці
        users.forEach(user => {
            const row = document.createElement('tr');
            const nameCell = document.createElement('td');
            nameCell.textContent = user.name || 'N/A';
            nameCell.style.cursor = 'pointer';
            nameCell.addEventListener('click', () => displayUserAnswers(user));
            row.appendChild(nameCell);
            userList.appendChild(row);
        });
    }

    // Функція для відображення відповідей користувача
    function displayUserAnswers(user) {
        userAnswers.innerHTML = ''; // Очистити попередній вміст таблиці

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
    }

    // Функція для повернення до списку користувачів
    backButton.addEventListener('click', () => {
        userListContainer.style.display = 'block';
        userAnswersContainer.style.display = 'none';
    });

    // Завантажити користувачів при завантаженні сторінки
    fetchUsers();
});
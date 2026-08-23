// ============================================================
// LOGIN
// Страница авторизации
// ============================================================


const Login = {

    root: null,

    username: null,

    password: null,

    button: null,

    error: null,


    // ========================================================
    // ИНИЦИАЛИЗАЦИЯ
    // ========================================================

    init() {

        this.root =
            document.getElementById("login");


        this.create();


        this.bind();


        this.username.focus();
    },


    // ========================================================
    // СОЗДАНИЕ ИНТЕРФЕЙСА
    // ========================================================

    create() {

        const form =
            document.createElement("form");

        form.className =
            "login-form";


        // ----------------------------------------------------
        // ЗАГОЛОВОК
        // ----------------------------------------------------

        const title =
            document.createElement("h1");

        title.className =
            "login-title";

        title.textContent =
            "Вход";


        // ----------------------------------------------------
        // ПОЛЬЗОВАТЕЛЬ
        // ----------------------------------------------------

        this.username =
            document.createElement("input");

        this.username.className =
            "login-input";

        this.username.type =
            "text";

        this.username.placeholder =
            "Пользователь";

        this.username.autocomplete =
            "username";


        // ----------------------------------------------------
        // ПАРОЛЬ
        // ----------------------------------------------------

        this.password =
            document.createElement("input");

        this.password.className =
            "login-input";

        this.password.type =
            "password";

        this.password.placeholder =
            "Пароль";

        this.password.autocomplete =
            "current-password";


        // ----------------------------------------------------
        // КНОПКА
        // ----------------------------------------------------

        this.button =
            document.createElement("button");

        this.button.className =
            "login-button";

        this.button.type =
            "submit";

        this.button.textContent =
            "Войти";


        // ----------------------------------------------------
        // ОШИБКА
        // ----------------------------------------------------

        this.error =
            document.createElement("div");

        this.error.className =
            "login-error";


        // ----------------------------------------------------
        // СБОРКА
        // ----------------------------------------------------

        form.appendChild(title);

        form.appendChild(
            this.username
        );

        form.appendChild(
            this.password
        );

        form.appendChild(
            this.button
        );

        form.appendChild(
            this.error
        );


        this.root.appendChild(form);
    },


    // ========================================================
    // ОБРАБОТЧИКИ
    // ========================================================

    bind() {

        const form =
            this.root.querySelector(
                ".login-form"
            );


        form.addEventListener(
            "submit",
            event => {

                event.preventDefault();

                this.login();
            }
        );
    },


    // ========================================================
    // АВТОРИЗАЦИЯ
    // ========================================================

    login() {

        const username =
            this.username.value.trim();


        const password =
            this.password.value;


        this.error.textContent =
            "";


        if (!username) {

            this.showError(
                "Введите пользователя"
            );

            return;
        }


        if (!password) {

            this.showError(
                "Введите пароль"
            );

            return;
        }


        // ====================================================
        // ВРЕМЕННАЯ ЗАГЛУШКА
        // ====================================================

        console.log(
            "Пользователь:",
            username
        );


        // ----------------------------------------------------
        // Временно считаем авторизацию успешной
        // ----------------------------------------------------

        const user = {

            username: username

        };


        sessionStorage.setItem(
            "current_user",
            JSON.stringify(user)
        );


        // ----------------------------------------------------
        // Переходим на основную страницу
        // ----------------------------------------------------

        window.location.href =
            "app.html";
    },


    // ========================================================
    // ОШИБКА
    // ========================================================

    showError(message) {

        this.error.textContent =
            message;
    }

};


// ============================================================
// ЗАПУСК
// ============================================================

document.addEventListener(
    "DOMContentLoaded",
    () => Login.init()
);
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

    async login() {

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


        try {

            const response =
                await fetch(
                    "https://app.komekcom.kz/auth/login",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        credentials: "include",

                        body: JSON.stringify({
                            login: username,
                            password: password
                        })
                    }
                );


            const result =
                await response.json();


            if (!response.ok) {

                this.showError(
                    result.error ||
                    "Ошибка авторизации"
                );

                return;
            }


            if (
                result.authenticated !== true
            ) {

                this.showError(
                    "Авторизация не выполнена"
                );

                return;
            }


            if (!result.redirect) {

                this.showError(
                    "Адрес перехода не получен"
                );

                return;
            }


            // ------------------------------------------------
            // ЛОКАЛЬНЫЙ ПЕРЕХОД
            // ------------------------------------------------

            window.location.href =
                result.redirect;

        } catch (error) {

            console.error(
                "Auth Service error:",
                error
            );


            this.showError(
                "Ошибка: " +
                error.message
            );
        }
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
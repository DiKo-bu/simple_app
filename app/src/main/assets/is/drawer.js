// ============================================================
// DRAWER
// ============================================================

import { createElement } from "./dom.js";
import { UI } from "./ui.js";


// ============================================================
// ИНИЦИАЛИЗАЦИЯ
// ============================================================

export function initializeDrawer() {

    createDrawer();


    UI.hamburger.addEventListener(
        "click",
        toggleDrawer
    );


    UI.drawerOverlay.addEventListener(
        "click",
        closeDrawer
    );
}


// ============================================================
// СОЗДАНИЕ DRAWER
// ============================================================

function createDrawer() {

    // ---------------------------------------------------------
    // ЗАГОЛОВОК
    // ---------------------------------------------------------

    const header =
        createElement(
            "div",
            "drawer-header",
            "Меню"
        );


    // ---------------------------------------------------------
    // КНОПКА «СМЕНИТЬ ПОЛЬЗОВАТЕЛЯ»
    // ---------------------------------------------------------

    const changeUserButton =
        createElement(
            "button",
            "drawer-item",
            "Сменить пользователя"
        );


    changeUserButton.type =
        "button";


    changeUserButton.addEventListener(
        "click",
        changeUser
    );


    // ---------------------------------------------------------
    // КНОПКА «ВЫХОД»
    // ---------------------------------------------------------

    const logoutButton =
        createElement(
            "button",
            "drawer-item",
            "Выход"
        );


    logoutButton.type =
        "button";


    logoutButton.addEventListener(
        "click",
        logout
    );


    // ---------------------------------------------------------
    // ДОБАВЛЯЕМ ЭЛЕМЕНТЫ В DRAWER
    // ---------------------------------------------------------

    UI.drawer.appendChild(
        header
    );


    UI.drawer.appendChild(
        changeUserButton
    );


    UI.drawer.appendChild(
        logoutButton
    );
}


// ============================================================
// СМЕНИТЬ ПОЛЬЗОВАТЕЛЯ
// ============================================================

function changeUser() {

    // Заглушка

    console.log(
        "Смена пользователя"
    );
}


// ============================================================
// ВЫХОД
// ============================================================

function logout() {

    // Заглушка

    console.log(
        "Выход"
    );
}


// ============================================================
// ОТКРЫТЬ / ЗАКРЫТЬ
// ============================================================

function toggleDrawer() {

    const isOpen =
        UI.drawer.classList.contains(
            "open"
        );


    if (isOpen) {

        closeDrawer();

    } else {

        openDrawer();
    }
}


// ============================================================
// ОТКРЫТЬ
// ============================================================

function openDrawer() {

    UI.drawer.classList.add(
        "open"
    );


    UI.drawerOverlay.classList.add(
        "visible"
    );
}


// ============================================================
// ЗАКРЫТЬ
// ============================================================

function closeDrawer() {

    UI.drawer.classList.remove(
        "open"
    );


    UI.drawerOverlay.classList.remove(
        "visible"
    );
}
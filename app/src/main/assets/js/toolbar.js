// ============================================================
// TOOLBAR
// ============================================================

import { createElement } from "./dom.js";

import { UI } from "./ui.js";

import { AppState } from "./state.js";

import { Text } from "./translations.js";


// ============================================================
// СОЗДАНИЕ TOOLBAR
// ============================================================

export function initializeToolbar() {

    createHamburger();

    createUploadButton();

    createSendButton();

    createSpacer();

    createLanguageButton();

    updateToolbar();
}


// ============================================================
// ГАМБУРГЕР
// ============================================================

function createHamburger() {

    UI.hamburger =
        createElement(
            "button",
            "hamburger",
            "☰"
        );

    UI.toolbar.appendChild(
        UI.hamburger
    );
}


// ============================================================
// ЗАГРУЗИТЬ
// ============================================================

function createUploadButton() {

    UI.uploadButton =
        createElement(
            "button",
            "toolbar-button",
            "Загрузить"
        );

    UI.uploadButton.type =
        "button";

    UI.uploadButton.addEventListener(
        "click",
        upload
    );

    UI.toolbar.appendChild(
        UI.uploadButton
    );
}


// ============================================================
// ОТПРАВИТЬ
// ============================================================

function createSendButton() {

    UI.sendButton =
        createElement(
            "button",
            "toolbar-button",
            "Отправить"
        );

    UI.sendButton.type =
        "button";

    UI.sendButton.addEventListener(
        "click",
        send
    );

    UI.toolbar.appendChild(
        UI.sendButton
    );
}


// ============================================================
// ПРОСТРАНСТВО
// ============================================================

function createSpacer() {

    const spacer =
        createElement(
            "div",
            "toolbar-spacer"
        );

    UI.toolbar.appendChild(
        spacer
    );
}


// ============================================================
// ЯЗЫК
// ============================================================

function createLanguageButton() {

    UI.languageButton =
        createElement(
            "button",
            "toolbar-button language-button"
        );

    UI.languageButton.type =
        "button";

    UI.languageButton.addEventListener(
        "click",
        switchLanguage
    );

    UI.toolbar.appendChild(
        UI.languageButton
    );
}


// ============================================================
// СОСТОЯНИЕ КНОПОК
// ============================================================

export function updateToolbar() {

    const firstTab =
        AppState.activeTab === 0;

    const secondTab =
        AppState.activeTab === 1;


    UI.uploadButton.disabled =
        !firstTab;

    UI.sendButton.disabled =
        !secondTab;
}


// ============================================================
// ЗАГРУЗКА
// ============================================================

function upload() {

    console.log(
        "Загрузка"
    );
}


// ============================================================
// ОТПРАВКА
// ============================================================

function send() {

    if (AppState.activeTab !== 1) {

        return;
    }

    console.log(
        "Отправка"
    );
}


// ============================================================
// ПЕРЕКЛЮЧЕНИЕ ЯЗЫКА
// ============================================================

function switchLanguage() {

    AppState.language =
        AppState.language === "ru"
            ? "kk"
            : "ru";

    updateTexts();
}


// ============================================================
// ТЕКСТЫ
// ============================================================

export function updateTexts() {

    const text =
        Text[AppState.language];


    UI.uploadButton.textContent =
        text.upload;

    UI.sendButton.textContent =
        text.send;

    UI.tabButtons[0].textContent =
        text.tab1;

    UI.tabButtons[1].textContent =
        text.tab2;

    UI.languageButton.textContent =
        AppState.language === "ru"
            ? "kk"
            : "ru";
}
import { createElement } from "./dom.js";


export const UI = {

    app: null,

    toolbar: null,

    hamburger: null,

    uploadButton: null,

    sendButton: null,

    languageButton: null,

    main: null,

    tabs: null,

    tabButtons: [],

    tabContent: null,

    pages: [],

    drawer: null,

    drawerOverlay: null

};


export function createApplication() {

    UI.app =
        createElement(
            "div",
            "app"
        );


    document.body.appendChild(
        UI.app
    );


    createToolbarBase();

    createMainBase();

    createDrawerBase();


    return UI;
}


function createToolbarBase() {

    UI.toolbar =
        createElement(
            "header",
            "toolbar"
        );


    UI.app.appendChild(
        UI.toolbar
    );
}


function createMainBase() {

    UI.main =
        createElement(
            "main",
            "main"
        );


    UI.tabs =
        createElement(
            "nav",
            "tabs"
        );


    UI.tabContent =
        createElement(
            "section",
            "tab-content"
        );


    UI.main.appendChild(
        UI.tabs
    );

    UI.main.appendChild(
        UI.tabContent
    );


    UI.app.appendChild(
        UI.main
    );
}


function createDrawerBase() {

    UI.drawerOverlay =
        createElement(
            "div",
            "drawer-overlay"
        );


    UI.drawer =
        createElement(
            "aside",
            "drawer"
        );


    document.body.appendChild(
        UI.drawerOverlay
    );


    document.body.appendChild(
        UI.drawer
    );
}
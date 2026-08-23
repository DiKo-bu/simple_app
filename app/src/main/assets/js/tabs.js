import { createElement } from "./dom.js";
import { UI } from "./ui.js";
import { AppState } from "./state.js";
import { updateToolbar } from "./toolbar.js";


export function initializeTabs() {

    createTabs();

    createPages();

    activateTab(0);
}


// ============================================================
// ВКЛАДКИ
// ============================================================

function createTabs() {

    for (let index = 0; index < 2; index++) {

        const tab =
            createElement(
                "button",
                "tab"
            );


        tab.dataset.index =
            index;


        tab.addEventListener(
            "click",
            () => activateTab(index)
        );


        UI.tabButtons.push(
            tab
        );


        UI.tabs.appendChild(
            tab
        );
    }
}


// ============================================================
// СТРАНИЦЫ
// ============================================================

function createPages() {

    for (let index = 0; index < 2; index++) {

        const page =
            createElement(
                "div",
                "content-page"
            );


        UI.pages.push(
            page
        );


        UI.tabContent.appendChild(
            page
        );
    }
}


// ============================================================
// АКТИВАЦИЯ
// ============================================================

export function activateTab(index) {

    AppState.activeTab =
        index;


    UI.tabButtons.forEach(
        (button, buttonIndex) => {

            button.classList.toggle(
                "active",
                buttonIndex === index
            );
        }
    );


    UI.pages.forEach(
        (page, pageIndex) => {

            page.classList.toggle(
                "active",
                pageIndex === index
            );
        }
    );


    updateToolbar();
}
// ============================================================
// УПРАВЛЕНИЕ КАРТОЧКАМИ
// ============================================================

import { createElement } from "./dom.js";

import { WorkCard } from "./WorkCard.js";


export class WorkManager {

    constructor(container) {

        this.container =
            container;

        this.cards = [];

        this.createControls();
    }


    // =========================================================
    // КНОПКИ + И −
    // =========================================================

    createControls() {

        this.controls =
            createElement(
                "div",
                "work-controls"
            );


        this.addButton =
            createElement(
                "button",
                "work-control-button",
                "+"
            );


        this.removeButton =
            createElement(
                "button",
                "work-control-button",
                "−"
            );


        this.addButton.type =
            "button";

        this.removeButton.type =
            "button";


        this.addButton.addEventListener(
            "click",
            () => this.addCard()
        );


        this.removeButton.addEventListener(
            "click",
            () => this.removeCard()
        );


        this.controls.appendChild(
            this.addButton
        );


        this.controls.appendChild(
            this.removeButton
        );


        this.container.appendChild(
            this.controls
        );
    }


    // =========================================================
    // ДОБАВИТЬ КАРТОЧКУ
    // =========================================================

    addCard() {

        const card =
            new WorkCard();


        this.cards.push(
            card
        );


        this.container.appendChild(
            card.getElement()
        );
    }


    // =========================================================
    // УДАЛИТЬ ПОСЛЕДНЮЮ КАРТОЧКУ
    // =========================================================

    removeCard() {

        if (this.cards.length === 0) {

            return;
        }


        const card =
            this.cards.pop();


        card.remove();
    }
}
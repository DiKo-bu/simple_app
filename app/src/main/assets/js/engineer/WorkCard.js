// ============================================================
// КАРТОЧКА ВЫПОЛНЕННОЙ РАБОТЫ
// ============================================================

import { createElement } from "./dom.js";


export class WorkCard {

    constructor() {

        this.element =
            null;

        this.create();
    }


    // =========================================================
    // СОЗДАНИЕ
    // =========================================================

    create() {

        const card =
            createElement(
                "article",
                "work-card"
            );


        // -----------------------------------------------------
        // ЗАГОЛОВОК
        // -----------------------------------------------------

        const title =
            createElement(
                "div",
                "work-card-title",
                "Выполненная работа"
            );


        card.appendChild(
            title
        );


        // -----------------------------------------------------
        // ДАТА
        // -----------------------------------------------------

        const dateField =
            this.createField("Дата");


        const date =
            document.createElement(
                "input"
            );


        date.type =
            "date";

        date.className =
            "work-card-input";


        dateField.appendChild(
            date
        );

        card.appendChild(
            dateField
        );


        // -----------------------------------------------------
        // РАБОТА
        // -----------------------------------------------------

        const workField =
            this.createField("Работа");


        const work =
            document.createElement(
                "input"
            );


        work.type =
            "text";

        work.className =
            "work-card-input";

        work.placeholder =
            "Введите выполненную работу";


        workField.appendChild(
            work
        );

        card.appendChild(
            workField
        );


        // -----------------------------------------------------
        // ИСПОЛНИТЕЛЬ
        // -----------------------------------------------------

        const workerField =
            this.createField("Исполнитель");


        const worker =
            document.createElement(
                "input"
            );


        worker.type =
            "text";

        worker.className =
            "work-card-input";

        worker.placeholder =
            "Введите исполнителя";


        workerField.appendChild(
            worker
        );

        card.appendChild(
            workerField
        );


        // -----------------------------------------------------
        // ОПИСАНИЕ
        // -----------------------------------------------------

        const descriptionField =
            this.createField("Описание");


        const description =
            document.createElement(
                "textarea"
            );


        description.className =
            "work-card-textarea";

        description.placeholder =
            "Введите описание работы";


        descriptionField.appendChild(
            description
        );

        card.appendChild(
            descriptionField
        );


        // -----------------------------------------------------
        // СТАТУС
        // -----------------------------------------------------

        const statusField =
            this.createField("Статус");


        const status =
            document.createElement(
                "select"
            );


        status.className =
            "work-card-select";


        this.addOption(
            status,
            "Выполнено",
            "completed"
        );

        this.addOption(
            status,
            "В работе",
            "in_progress"
        );

        this.addOption(
            status,
            "Отменено",
            "cancelled"
        );


        statusField.appendChild(
            status
        );

        card.appendChild(
            statusField
        );


        // -----------------------------------------------------
        // СОХРАНИТЬ
        // -----------------------------------------------------

        const save =
            createElement(
                "button",
                "work-card-button",
                "Сохранить"
            );


        save.type =
            "button";


        save.addEventListener(
            "click",
            () => {

                console.log({
                    date: date.value,
                    work: work.value,
                    worker: worker.value,
                    description: description.value,
                    status: status.value
                });

            }
        );


        // -----------------------------------------------------
        // КНОПКИ
        // -----------------------------------------------------

        const buttons =
            createElement(
                "div",
                "work-card-buttons"
            );


        buttons.appendChild(
            save
        );


        card.appendChild(
            buttons
        );


        this.element =
            card;
    }


    // =========================================================
    // ПОЛЕ
    // =========================================================

    createField(labelText) {

        const field =
            createElement(
                "div",
                "work-card-field"
            );


        const label =
            createElement(
                "label",
                "work-card-label",
                labelText
            );


        field.appendChild(
            label
        );


        return field;
    }


    // =========================================================
    // OPTION
    // =========================================================

    addOption(
        select,
        text,
        value
    ) {

        const option =
            document.createElement(
                "option"
            );


        option.textContent =
            text;

        option.value =
            value;


        select.appendChild(
            option
        );
    }


    // =========================================================
    // DOM
    // =========================================================

    getElement() {

        return this.element;
    }


    // =========================================================
    // УДАЛЕНИЕ
    // =========================================================

    remove() {

        if (this.element) {

            this.element.remove();
        }
    }
}
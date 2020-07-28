document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      if(this.currentStep == 5) {
        const bags = document.querySelector("#quantity");
        const category = document.querySelectorAll("#checkbox label input");
        const categoryName = document.querySelectorAll("#checkbox label .description");

        const last = category.length;
        let text = ''
        for (let i = 0; i < last; i++) {
          if (category[i].checked == true) {
            text += categoryName[i].innerText + ', '
          }
        }
        const line = document.createElement("li");
        line.innerText = bags.value + ' worki: ' + text
        document.querySelector("#bag .summary--text").appendChild(line);

        const institution = document.querySelectorAll("#radio label input");
        const institutionName = document.querySelectorAll("#radio label .description .title");
        const line2 = document.createElement("li");
        const last2 = institution.length;
        for (let i = 0; i < last2; i++) {
          if (institution[i].checked == true) {
            line2.innerText = institutionName[i].innerText
          }
        }
        document.querySelector("#hand .summary--text").appendChild(line2);
        const street = document.querySelector("#street");
        const line3 = document.createElement("li");
        line3.innerText = street.value;
        document.querySelector("#address ul").appendChild(line3);
        const city = document.querySelector("#city");
        const line4 = document.createElement("li");
        line4.innerText = city.value;
        document.querySelector("#address ul").appendChild(line4);
        const zipCode = document.querySelector("#zipCode");
        const line5 = document.createElement("li");
        line5.innerText = zipCode.value;
        document.querySelector("#address ul").appendChild(line5);
        const phone = document.querySelector("#phone");
        const line6 = document.createElement("li");
        line6.innerText = phone.value;
        document.querySelector("#address ul").appendChild(line6);
        const pickUpDate = document.querySelector("#pickUpDate");
        const line7 = document.createElement("li");
        line7.innerText = pickUpDate.value;
        document.querySelector("#term ul").appendChild(line7);
        const pickUpTime = document.querySelector("#pickUpTime");
        const line8 = document.createElement("li");
        line8.innerText = pickUpTime.value;
        document.querySelector("#term ul").appendChild(line8);
        const pickUpComment = document.querySelector("#pickUpComment");
        const line9 = document.createElement("li");
        line9.innerText = pickUpComment.value;
        document.querySelector("#term ul").appendChild(line9);
      }


    }
  }

  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }

});

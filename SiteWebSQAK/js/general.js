function toggleDarkMode() {
    const isDarkMode = document.documentElement.classList.toggle("dark-mode");
    localStorage.setItem("darkMode", isDarkMode ? "enabled" : "disabled");
    updateButtonText();
}

function updateButtonText() {
    let modeButton = document.getElementById("mode");
    if (document.documentElement.classList.contains("dark-mode")) {
        modeButton.innerHTML = "Dark Mode";
    } else {
        modeButton.innerHTML = "Light Mode";
    }
}

function applySavedMode() {
    const savedMode = localStorage.getItem("darkMode");
    if (savedMode === "enabled") {
        document.documentElement.classList.add("dark-mode");
    } else if (savedMode === "disabled") {
        document.documentElement.classList.remove("dark-mode");
    } else {
        if (window.matchMedia("(prefers-color-scheme:dark)").matches) {
            document.documentElement.classList.add("dark-mode");
        }
    }
    updateButtonText();
}

applySavedMode();

document.addEventListener("DOMContentLoaded", function () {
    const navTxt = document.querySelectorAll(".nav-txt"); // Fix class selector (added ".")

    function applyActiveClass() {
        let activeNavId = localStorage.getItem("activeNav");
        if (activeNavId) {
            navTxt.forEach(txt => txt.classList.remove("active"));
            let activeNav = document.getElementById(activeNavId);
            if (activeNav) activeNav.classList.add("active");
        }
    }

    navTxt.forEach(txt => {
        txt.addEventListener("click", function () {
            localStorage.setItem("activeNav", this.id); // Store clicked nav ID
        });
    });

    applyActiveClass(); // Apply active class when page loads
});

function toggleDarkMode() {
    const isDarkMode = document.documentElement.classList.toggle("dark-mode");
    localStorage.setItem("darkMode", isDarkMode ? "enabled" : "disabled");
    updateButtonText();
}

function updateButtonText() {
    let modeButton = document.getElementById("mode");
    if (modeButton) {
        if (document.documentElement.classList.contains("dark-mode")) {
            modeButton.innerHTML = "Dark Mode";
        } else {
            modeButton.innerHTML = "Light Mode";
        }
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

function confirmSupprimer(event) {
    let confirmation = window.confirm("Êtes-vous sûr de vouloir supprimer votre compte ?");
    if (!confirmation) {
        event.preventDefault();
        return false;
    }
    return true;
}


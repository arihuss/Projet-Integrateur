function toggleDarkMode(){
    document.documentElement.classList.toggle("dark-mode");
    let modeButton = document.getElementById("mode");
    if (document.documentElement.classList.contains("dark-mode")){
        modeButton.innerHTML="Dark Mode";
    }else{
        modeButton.innerHTML="Light Mode";
    }
}
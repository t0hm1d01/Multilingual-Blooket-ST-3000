
const LanguageData = {

    English: {
        greeting: "hello",
        description: "Welcome to our app!",
    },


    Spanish: {
        greeting: "Hola",
        description: "Bienvenido a nuestra aplicación!",
    },


    German: {
        greeting: "Hallo",
        description: "Willkommen in unserer App!",
    },


    French: {
        greeting: "bonjour",
        description: "Bienvenue sur notre application!",
    },
};


const langLinks = document.querySelectorAll('.lang-menu ul li a');

function changeLanguage(languageCode){
    const content = LanguageData[languageCode];
    document.getElementById('greetings').textContent = content.greeting;
    document.getElementById('description').textContent = content.description;
    const selectedLang = document.querySelector('.selected-lang');
    selectedLang.textContent = languageCode.toUpperCase();
}


langLinks.forEach(link =>{
    link.addEventListener('click', (e) => {
        e.preventDefault();
        console.log('Clicked language:', e.target.className)
        const languageCode = e.target.className;
        changeLanguage(languageCode);
    });
    
});

const music = document.getElementById('music');
const volumeSlider = document.querySelector('.volumeSlider');

volumeSlider.addEventListener('input', function() {
    music.volume = this.value;
});

document.body.addEventListener('click', function() {
    music.muted = false;
    music.play();
});
 
